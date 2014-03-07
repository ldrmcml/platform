package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.model.User;
import com.jtang.dao.UserManagerDao;

public class UserManagerImpl extends JdbcDaoSupport implements UserManagerDao{
	
	class UserRowMapper implements RowMapper<User> {
	        //
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            User m = new User();
	            m.setId(rs.getString("Id"));
	            m.setName(rs.getString("Name"));
	            m.setPasswd(rs.getString("Password"));
	            m.setPositionId(rs.getInt("PositionId"));
	            m.setSex(rs.getInt("Sex"));
	            m.setEnterpriseId(rs.getString("enterpriseId"));
	            m.setStorageList(rs.getString("storageList"));
	            m.setDefaultStorageId(rs.getInt("defaultStorage"));
	            return m;
	        }
	};

	public void add(User user) {
		// TODO Auto-generated method stub
		this.getJdbcTemplate().update("insert into user(Id,Password,Name,Sex,PositionId) values(?,?,?,?,?)",
				user.getId(),user.getPasswd(),user.getName(),user.getSex(),user.getPositionId());
		
	}

	public List<User> query(String sql, Object[] args,int [] argTypes) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args,argTypes,new UserRowMapper());
	}

}
