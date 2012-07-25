package tw.elliot.dao.springjpa;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import tw.elliot.util.StringUtils;


public class ExampleSpecification<T> implements Specification<T> {
	private static final Logger logger = LoggerFactory.getLogger(ExampleSpecification.class);
	protected static final Map<Class<?>, List<ExpressionParam<?>>> classCache = Collections.synchronizedMap(new WeakHashMap<Class<?>, List<ExpressionParam<?>>>());
	
	EntityManager entityManager;
	T example;
	
	public ExampleSpecification(final EntityManager entityManager, final T example) {
		this.entityManager = entityManager;
		this.example = example;
	}
	
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		EntityType<T> entity = entityManager.getMetamodel().entity((Class<T>)example.getClass());
		List<ExpressionParam<?>> params = parseReadMethod(entity);
		for (ExpressionParam<?> param : params) {
			try {
				Object value = param.getReadMethod().invoke(example);
				if (null != value && StringUtils.isNotEmpty(value.toString())) {
					predicates.add(cb.equal(root.get((SingularAttribute<T, ?>)param.getAttribute()), value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return predicates.isEmpty()?cb.conjunction() : cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	protected List<ExpressionParam<?>> parseReadMethod(EntityType<T> entityType) {
		Class<T> clazz = (Class<T>) entityType.getClass();
		if (classCache.containsKey(clazz)) {
			return classCache.get(clazz);
		}
		logger.info("First Parsing Read Method for Class[{}]", clazz);
		
		List<ExpressionParam<?>> methods = new ArrayList<ExpressionParam<?>>();
		classCache.put(clazz, methods);
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(example.getClass());
		
		Set<SingularAttribute<T, ?>> atts = entityType.getDeclaredSingularAttributes();
		for (SingularAttribute<T,?> sat : atts) {
			if (PersistentAttributeType.MANY_TO_ONE == sat.getPersistentAttributeType() 
					|| PersistentAttributeType.ONE_TO_ONE == sat.getPersistentAttributeType()) {
				continue;
			}
			String name = sat.getName();
			Method readMethod = null;
			for (PropertyDescriptor pd : pds) {
				if (pd.getName().equals(name)) {
					readMethod = pd.getReadMethod();
					break;
				}
			}
			
			logger.debug("Property {} - Method {}", name, readMethod);
			if (null != readMethod) {
				methods.add(new ExpressionParam<T>(name, readMethod, sat));
			}
		}
		
		return methods;
	}
}
