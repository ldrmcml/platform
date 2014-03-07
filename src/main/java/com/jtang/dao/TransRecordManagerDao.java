package com.jtang.dao;

import java.util.List;

import com.jtang.model.TransRecord;

/**
 * 
 * @author chenminglong
 *
 */
public interface TransRecordManagerDao {
	

	/**
	 * 获取数据库中所有的运输记录
	 * @return
	 */
	public List<TransRecord> query(String sql, Object[] args);
	
	/**
	 * 增加一条运输记录到数据库中，增加成功返回1，否则返回0
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更改运输记录
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一条运输记录
	 * 
	 */
	public int delete(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 查询返回数字
	 */
	public int queryRetInt(String sql);

}
