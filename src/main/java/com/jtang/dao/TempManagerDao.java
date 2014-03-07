/**
 * 
 */
package com.jtang.dao;

import java.util.List;
import java.util.Map;

import com.jtang.model.Temperature;


/**
 * @author Administartor
 *
 */
public interface TempManagerDao {
	/**
	 * 获取数据库中所有的传感器温度数据
	 * @return
	 */
	public List<Temperature> query(String sql, Object[] args,int[] argTypes);
	
	/**
	 * 增加一条记录到数据库
	 */
	public int add(String sql,Object[] args,int[] argTypes);
	
	/**
	 * 更新数据库中的传感器温度数据
	 */
	public int update(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 删除一项
	 * 
	 */
	public int delete(String sql,Object[] args,int [] argTypes);
	
	/**
	 * 当执行类似于 select count(*)的sql语句时，调用此方法
	 * @param sql
	 * @param args
	 * @param argTypes
	 * @return
	 */
	public List<Map<String,Object>> queryForList(String sql,Object[] args,int [] argTypes);
}
