package cn.itcast.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IBaseDao;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseDao(){
		//通过类的反射，来看这个类里有什么内容,但子类里没泛型T
		Class<?> childClass = this.getClass();
		//找它的父类，拿到type, type是一个超级接口，它有实现类Class
		Type type = childClass.getGenericSuperclass();
		//ParameterizedType 表示参数化类型，如 Collection<String>。 
		ParameterizedType pType = (ParameterizedType)type;
		//返回表示此类型实际类型参数的 Type 对象的数组。 e.g. List<T>, Map<K,V>
		Type[] types = pType.getActualTypeArguments();
		//得到泛型中的T的类型，原因我们泛型里只有一个T参数，所这个数组的长度只有1个
		entityClass = (Class<T>)types[0];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 查询所有部门
	 */
	public List<T> getList(T t1, T t2, Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		//DC不进入IF条件 =》 from T
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 查询所有部门
	 */
	public List<T> getListByPage(T t1, T t2, Object param, int firstResult, int maxResult) {
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		//DC不进入IF条件 =》 from T
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResult);
	}
	
	@Override
	public Long getCount(T t1, T t2, Object param){
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		//count()
		dc.setProjection(Projections.rowCount());
		return (Long)this.getHibernateTemplate().findByCriteria(dc).get(0);
	}
	

	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	public void delete(Long uuid){
		T t = this.getHibernateTemplate().get(entityClass, uuid);
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public T get(Long uuid) {
		return this.getHibernateTemplate().get(entityClass, uuid);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	
	/**
	 * 创建查询条件
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(T t1, T t2, Object param){
		
		return null;
	}
	/*
	public void setEntityClass(Class<T> entityClass){
		this.entityClass = entityClass;
	}*/
}
