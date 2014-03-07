package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.RawmaterialManagerDao;
import com.jtang.model.Rawmaterial;

/**
 * @author chenminglong
 *
 */
/*
 * SQL语句缺失
 */
public class RawmaterialManagerImpl extends JdbcDaoSupport implements RawmaterialManagerDao{

	class RawmaterialRowMapper implements RowMapper<Rawmaterial> {
		//将查询到的结果集转换为Rawmaterial对象
        public Rawmaterial mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Rawmaterial m = new Rawmaterial();
        	m.setId(rs.getInt("Id"));
        	m.setName(rs.getString("Name"));
            m.setManufacturer(rs.getString("Manufacturer"));
            m.setProductionPlace(rs.getString("ProductionPlace"));
            m.setProductionDate(rs.getDate("ProductionDate"));
            return m;
        }
    };
	
	public List<Rawmaterial> query(String sql, Object[] args) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args, new RawmaterialRowMapper());
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
