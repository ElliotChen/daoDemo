package tw.elliot.dao.springjpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class ExamplePRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements ExampleRepository<T, ID> {
	private final EntityManager entityManager;
	public ExamplePRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	public ExamplePRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}


	public List<T> findByExample(T example) {
		ExampleSpecification<T> es = new ExampleSpecification<T>(this.entityManager, example);
		return this.findAll(es);
	}

}
