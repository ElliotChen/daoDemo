package tw.elliot.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;

import tw.elliot.domain.User;
import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;

public class AbstractBaseDao<T, Oid extends Serializable> {
	@Autowired
	protected EntityManager entityManager;
	
	protected Class<T> domainClass;

	@SuppressWarnings("unchecked")
	public AbstractBaseDao() {
		this.domainClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T findByOid(Oid oid) {
		return this.entityManager.find(domainClass, oid);
	}

	public void create(T entity) {
		this.entityManager.persist(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public void update(T entity){
		this.entityManager.merge(entity);
	}

	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		
	}

	public void merge(T entity) {
		// TODO Auto-generated method stub
		
	}
	public List<T> listAll() {
		CriteriaQuery<T> cq = this.buildCriteriaQuery();
		TypedQuery<T> query = this.entityManager.createQuery(cq);
		return query.getResultList();
	}

	public List<T> listByExample(T example) {
		CriteriaQuery<T> cq = this.buildCriteriaQuery();
		TypedQuery<T> query = this.entityManager.createQuery(cq);
		return query.getResultList();
	}

	public List<T> listByExample(final T example, Condition[] conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders) {
		return null;
	}
	
	public List<T> listByExample(T example, List<Condition> conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders) {
		// TODO Auto-generated method stub
		return null;
	}
	public Page<T> listByPage(Page<T> page) {
		return page;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<T> getDomainClass() {
		return domainClass;
	}

	private CriteriaQuery<T> buildCriteriaQuery() {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(domainClass);
		//Root<T> root = 
		cq.from(this.domainClass);
		return cq;
	}
}
