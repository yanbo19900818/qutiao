package com.qutiao.dao.common;

public interface IBaseDao<T> {
	/**
	 * 获得所有对象
	 * 
	 * @param pageNum
	 *            请求的页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	public Page searchAll(int pageNum, int pageSize);

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public T searchById(long id);

	/**
	 * 保存对象放回主键
	 * 
	 * @param instance
	 * @return
	 */
	public long saves(T instance);

	/**
	 * 修改数据
	 * 
	 * @param instance
	 * @return
	 */
	public boolean modify(T instance);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean delete(long id);

}
