package com.chaoliu1995.english.base;

import java.util.List;

public interface BaseService<T> {

	/**
	 * 根据id,查询单个
	 * @param id
	 * @return
	 */
	T getById(Integer id);
	
	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * @param model
	 * @return
	 */
	List<T> listByCol(T model);
	
	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 * @param model
	 * @return
	 */
	int getCountByCol(T model);
	
	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * @param model
	 * @return
	 */
	T getOne(T model);
	
	/**
     * 插入单个对象
     * @param model
     * @return
     */
	int insert(T model);
	
	/**
     * 根据主键更新对象,更新对象所有字段
     * @param model
     * @return
     */
	int update(T model);
	
	/**
     * 根据主键删除
     * @param o
     * @return
     */
	int delete(Integer id);
	
	/**
     * 根据对象信息删除
     * 例如,传入 对象 User, id=1,name=a , 则删除 id=1,name=a的记录
     * 如果传入对象 User,name=a, 则删除 name=a的记录
     * @param o
     * @return
     */
	int delete(T model);
	
	/**
	 * 查询所有记录
	* @return
	 */
	List<T> listAll();
}
