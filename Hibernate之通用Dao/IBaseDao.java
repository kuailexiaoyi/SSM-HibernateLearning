package cn.itcast.erp.dao;

import java.util.List;

public interface IBaseDao<T> {
	
	/**
	 * 查询优化
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	List<T> getList(T t1, T t2, Object param);
	
	/**
	 * 分页查询
	 * @param t1
	 * @param t2
	 * @param param
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	List<T> getListByPage(T t1, T t2, Object param, int firstResult, int maxResult);
	
	/**
	 * 计算符合条件的总计录数
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	Long getCount(T t1, T t2, Object param);
	
	/**
	 * 新增
	 * @param t
	 */
	void add(T t);
	
	/**
	 * 删除
	 * @param uuid
	 */
	void delete(Long uuid);
	
	/**
	 * 根据编号查询对象
	 */
	T get(Long uuid);
	
	/**
	 * 更新
	 */
	void update(T t);
}
