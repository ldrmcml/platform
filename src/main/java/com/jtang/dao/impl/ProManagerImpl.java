package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.ProManagerDao;
import com.jtang.model.Product;


public class ProManagerImpl extends JdbcDaoSupport implements ProManagerDao{

	class ProductRowMapper implements RowMapper<Product> {
        //
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Product m = new Product();
        	m.setCount(rs.getInt("proCount"));
        	m.setId(rs.getInt("proId"));
        	m.setName(rs.getString("proName"));
        	m.setUnit(rs.getInt("proUnit"));
        	m.setMsg(rs.getString("proMsg"));
        	m.setStorageId(rs.getString("storageId"));
            return m;
        }
	};

	public List<Product> query(String sql, Object[] args,int [] argTypes) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args,argTypes,new ProductRowMapper());
	}

	public int update(String sql, Object[] args,int[] argTypes) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().update(sql, args,argTypes);
	}

	public int insert(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().update(sql, args,argTypes);
	}

}
