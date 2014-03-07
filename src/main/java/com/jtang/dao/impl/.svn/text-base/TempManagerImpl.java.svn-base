/**
 * 
 */
package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.TempManagerDao;
import com.jtang.dao.impl.SensorManagerImpl.SensorRowMapper;
import com.jtang.model.Temperature;

/**
 * @author Administartor
 *
 */
public class TempManagerImpl extends JdbcDaoSupport implements TempManagerDao {
	
	
	
	class TempRowMapper implements RowMapper<Temperature> {
        //将查询到的结果集转换为Sensor对象
        public Temperature mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Temperature t = new Temperature();
        	t.setSensorExtAddr(rs.getString("sensorExtAddr"));
        	t.setRecordTime(rs.getString("recordTime"));
        	t.setTemperature(rs.getFloat("temperature"));        	
            return t;
        }
	};

	/* (non-Javadoc)
	 * @see com.jtang.dao.TempManagerDao#query(java.lang.String, java.lang.Object[])
	 */
	public List<Temperature> query(String sql, Object[] args,int [] argTypes) {
		// TODO Auto-generated method stub
		
		return this.getJdbcTemplate().query(sql, args,argTypes, new TempRowMapper());
	}

	/* (non-Javadoc)
	 * @see com.jtang.dao.TempManagerDao#add(java.lang.String, java.lang.Object[], int[])
	 */
	public int add(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}

	/* (non-Javadoc)
	 * @see com.jtang.dao.TempManagerDao#update(java.lang.String, java.lang.Object[], int[])
	 */
	public int update(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}

	/* (non-Javadoc)
	 * @see com.jtang.dao.TempManagerDao#delete(java.lang.String, java.lang.Object[], int[])
	 */
	public int delete(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}
	
	int executeUpdate(String sql,Object[] args,int[] argTypes){
		if(argTypes == null){
			return this.getJdbcTemplate().update(sql, args);
		}else{
			return this.getJdbcTemplate().update(sql, args,argTypes);
		}
	}

	public List<Map<String,Object>> queryForList(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> resultList =  this.getJdbcTemplate().queryForList(sql, args, argTypes);
		return resultList;
	}

}
