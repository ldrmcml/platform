/**
 * 
 */
package com.jtang.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.jtang.model.Storage;

/**
 * @author Administartor
 *
 */
public interface StorageManagerDao {
	/**
	 * 
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @return 根据指定的sql语句返回查询的链表
	 */
	public List<Storage> query(String sql ,Object[] args,int[] argTypes);
	
	/**
	 * 增加一个仓库到数据库中，增加成功返回1，否则返回0
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更改仓库的信息
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一个仓库
	 * 
	 */
	public int delete(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 
	 */
	public List<Storage> queryUseMapSql(String sql,MapSqlParameterSource parameters);
}
