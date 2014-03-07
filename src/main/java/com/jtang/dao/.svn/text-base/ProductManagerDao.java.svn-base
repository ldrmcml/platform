package com.jtang.dao;

import java.util.List;

import com.jtang.model.Pro_Trace;

/**
 * 
 * @author chenminglong
 *
 */
public interface ProductManagerDao {
	

	/**
	 * 获取数据库中所有的产品
	 * @return
	 */
	public List<Pro_Trace> query(String sql, Object[] args);
	
	/**
	 * 增加一种产品到数据库中，增加成功返回1，否则返回0
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更改产品的信息
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一种产品
	 * 
	 */
	public int delete(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 查询返回数字
	 */
	public int queryRetInt(String sql);

}
