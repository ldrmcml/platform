package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.TransRecordManagerDao;
import com.jtang.model.TransRecord;

/**
 * @author chenminglong
 *
 */
/*
 * SQL语句缺失
 */
public class TransRecordManagerImpl extends JdbcDaoSupport implements TransRecordManagerDao{

	class TransRecordRowMapper implements RowMapper<TransRecord> {
		//将查询到的结果集转换为TransRecord对象
        public TransRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        	TransRecord m = new TransRecord();
        	m.setId(rs.getInt("Id"));
        	m.setStartPlace(rs.getString("StartPlace"));
            m.setEndPlace(rs.getString("EndPlace"));
            m.setStartTime(rs.getDate("StartTime"));
            m.setEndTime(rs.getDate("EndTime"));
            m.setTemperature(rs.getFloat("Temperature"));
            return m;
        }
    };
	
	public List<TransRecord> query(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args, new TransRecordRowMapper());
	}

	public int update(String sql, Object[] args,int [] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}
	
	public int add(String sql, Object[] args,int [] argTypes) {
		// TODO Auto-generated method stub
		//return the number of rows affected
		return executeUpdate(sql, args,argTypes);

	}

	int executeUpdate(String sql,Object[] args,int[] argTypes){
		if(argTypes == null){
			return this.getJdbcTemplate().update(sql, args);
		}else{
			return this.getJdbcTemplate().update(sql, args,argTypes);
		}
	}

	public int delete(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}
	
	@SuppressWarnings("deprecation")
	public int queryRetInt(String sql){
		int num=this.getJdbcTemplate().queryForInt(sql);
		return num;
	}
	

}
