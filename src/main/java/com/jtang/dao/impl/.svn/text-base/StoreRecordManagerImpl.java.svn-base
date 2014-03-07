package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.StoreRecordManagerDao;
import com.jtang.model.StoreRecord;

/**
 * @author chenminglong
 *
 */
/*
 * SQL语句缺失
 */
public class StoreRecordManagerImpl extends JdbcDaoSupport implements StoreRecordManagerDao{

	class StoreRecordRowMapper implements RowMapper<StoreRecord> {
		//将查询到的结果集转换为Product对象
        public StoreRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        	StoreRecord m = new StoreRecord();
        	m.setId(rs.getInt("Id"));
        	m.setStorageId(rs.getInt("StorageId"));
        	m.setStartTime(rs.getDate("StartTime"));
        	m.setEndTime(rs.getDate("EndTime"));
        	m.setTemperature(rs.getFloat("Temperature"));
            return m;
        }
    };
	
	public List<StoreRecord> query(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args, new StoreRecordRowMapper());
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
