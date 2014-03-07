package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.ProductManagerDao;
import com.jtang.model.Pro_Trace;

/**
 * @author chenminglong
 *
 */
/*
 * SQL语句缺失
 */
public class ProductManagerImpl extends JdbcDaoSupport implements ProductManagerDao{

	class ProductRowMapper implements RowMapper<Pro_Trace> {
		//将查询到的结果集转换为Product对象
        public Pro_Trace mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Pro_Trace m = new Pro_Trace();
        	m.setId(rs.getInt("Id"));
        	m.setName(rs.getString("Name"));
            m.setBarcode(rs.getString("Barcode"));
            m.setProductionDate(rs.getDate("ProductionDate"));
            m.setStorageRecordId(rs.getInt("StorageRecordId"));
            m.setTransportationRecordId(rs.getInt("TransportationRecordId"));
            m.setRawmaterialRecordId(rs.getInt("RawmaterialRecordId"));
            m.setRecorderId(rs.getInt("RecorderId"));
            m.setRecordDate(rs.getDate("RecordDate"));
            return m;
        }
    };
	
	public List<Pro_Trace> query(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args, new ProductRowMapper());
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
