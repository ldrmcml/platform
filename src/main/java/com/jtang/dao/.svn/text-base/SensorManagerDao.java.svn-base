package com.jtang.dao;

import java.util.List;

import com.jtang.model.Sensor;

/**
 * 
 * @author zhouxinyu
 *
 */
public interface SensorManagerDao {
	

	/**
	 * 获取数据库中所有的传感器
	 * @return
	 */
	public List<Sensor> query(String sql, Object[] args,int []types);
	
	/**
	 * 增加一个传感器到数据库中，增加成功返回1，否则返回0
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更改传感器的信息
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一个传感器
	 * 
	 */
	public int delete(String sql,Object[] args,int [] argTypes);

}
