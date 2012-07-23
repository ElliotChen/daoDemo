package tw.elliot.dao;

import java.io.Serializable;
import java.util.List;

import tw.elliot.domain.support.Condition;
import tw.elliot.domain.support.LikeMode;
import tw.elliot.domain.support.Page;

public interface BaseDao<T, Oid extends Serializable> {
	T findByOid(Oid oid);

	void create(T entity);

	void delete(T entity);

	void update(T entity);
	
	void saveOrUpdate(T entity);
	
	void merge(T entity);

	List<T> listAll();

	List<T> listByExample(final T example);

	List<T> listByExample(final T example, List<Condition> conditions,
			LikeMode mode, String[] ascOrders, String[] descOrders);
	
	Page<T> listByPage(final Page<T> page);
}