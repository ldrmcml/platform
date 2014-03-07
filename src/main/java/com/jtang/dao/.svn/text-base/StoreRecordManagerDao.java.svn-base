package com.jtang.dao;

import java.util.List;

import com.jtang.model.StoreRecord;

/**
 * 
 * @author chenminglong
 *
 */
public interface StoreRecordManagerDao {
	

	/**
	 * 获取数据库中所有的储存记录
	 * @return
	 */
	public List<StoreRecord> query(String sql, Object[] args);
	
	/**
	 * 增加一条储存记录到数据库中，增加成功返回1，否则返回0
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更改储存记录的信息
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一条储存记录
	 * 
	 */
	public int delete(String sql,Object[] args,int [] argTypes);

	/**
	 * 查询返回数字
	 */
	public int queryRetInt(String sql);

}
