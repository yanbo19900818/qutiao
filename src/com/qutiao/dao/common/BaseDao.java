package com.qutiao.dao.common;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BaseDao<T> implements IBaseDao<T> {
	@Autowired
	@Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;

	@SuppressWarnings("rawtypes")
	public String genericTypeName() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		Class clazz = (Class) params[0];
		String nameString = clazz.getName();
		return nameString;
	}

	@SuppressWarnings("rawtypes")
	public Class genericType() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		Class clazz = (Class) params[0];
		return clazz;
	}

	public T searchById(long id) {
		return searchObjectByHQL("from " + genericTypeName() + " where id="
				+ id);
	}

	public Page searchAll(int pageNum, int pageSize) {
		Page page = searchByHQL("from " + genericTypeName(), pageNum, pageSize);
		return page;
	}

	public long saves(T instance) {
		Session session = sessionFactory.getCurrentSession();
		long id = (Long) session.save(instance);
		return id;
	}

	public boolean modify(T instance) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(instance);
		return true;
	}

	public boolean delete(long id) {
		excuteHQL("delete " + genericTypeName() + " where id=" + id);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<T> searchBySQL(String sql) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql).addEntity(genericType());
		List<T> list = query.list();
		return list;
	}

	public boolean executeSQL(String sql) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		return true;
	}

	/**
	 * 根据HQL执行更新语句
	 * 
	 * @param Hql
	 * @return
	 */
	public boolean excuteHQL(String hql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	/**
	 * 根据HQL语句查询
	 * 
	 * @param Hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> searchByHQL(String hql) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<T> list = query.list();
		return list;
	}

	/**
	 * 根据HQL语句分页查询
	 * 
	 * @param Hql
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page searchByHQL(String hql, int pageSize, int pageNum) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<T> result = query.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize).list();
		int totalNum = totalNumber(hql);

		Page page = PageCreator.CreatePage(result, pageNum, totalNum, pageSize);
		return page;
	}

	/**
	 * 根据hql获得记录数
	 * 
	 * @param hql
	 * @return
	 */
	private int totalNumber(String hql) {
		hql = "select count(*) " + hql;
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		int count = Integer.parseInt((Long) q.uniqueResult() + "");

		return count;
	}

	public T searchObjectByHQL(String hql) {
		List<T> list = searchByHQL(hql);
		if (list.size() <= 0)
			return null;
		return list.get(0);
	}
}
