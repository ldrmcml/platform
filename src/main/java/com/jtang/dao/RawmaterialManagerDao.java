package com.jtang.dao;

import java.util.List;

import com.jtang.model.Rawmaterial;

/**
 * 
 * @author chenminglong
 *
 */
public interface RawmaterialManagerDao {
	

	/**
	 * 获取数据库中所有的原材料
	 * @return
	 */
	public List<Rawmaterial> query(String sql, Object[] args);
	
	/**
	 * 增加一种原材料到数据库中，增加成功返回1，否则返回0
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更改原材料的信息
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一种原材料
	 */
	public int delete(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 查询返回数字
	 */
	public int queryRetInt(String sql);

}
