package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.InOutManagerDao;
import com.jtang.model.InOut;


public class InOutManagerImpl extends JdbcDaoSupport implements InOutManagerDao{

	class InOutRowMapper implements RowMapper<InOut> {
        
        public InOut mapRow(ResultSet rs, int rowNum) throws SQLException {
        	InOut m = new InOut();
            m.setCardNum(rs.getString("cardNum"));
            m.setAction(rs.getInt("action"));
            m.setPersonId(rs.getString("personId"));
            m.setBindCount(rs.getInt("bindCount"));
            m.setTime(rs.getString("time"));
            m.setBarCode(rs.getString("barCode"));
            m.setProId(rs.getInt("proId"));
            m.setStorageId(rs.getString("storageId"));
            return m;
        }
	};
	
	//¹ØÁªproName
	class InOutFullRowMapper implements RowMapper<InOut>{
		
		public InOut mapRow(ResultSet rs, int rowNum) throws SQLException {
        	InOut m = new InOut();
            m.setCardNum(rs.getString("cardNum"));
            m.setAction(rs.getInt("action"));
            m.setPersonId(rs.getString("personId"));
            m.setBindCount(rs.getInt("bindCount"));
            m.setTime(rs.getString("time"));
            m.setBarCode(rs.getString("barCode"));
            m.setProId(rs.getInt("proId"));
            m.setProName(rs.getString("proName"));
            m.setStorageId(rs.getString("storageId"));
            return m;
        }
	}

	
	public int update(String sql,Object[] args){
		return this.getJdbcTemplate().update(sql,args);
	}
	
	

	public List<InOut> query(String sql, Object[] args,int types[]) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args,types,new InOutRowMapper());
	
	}
	
	public List<InOut> queryWithPro(String sql,Object[] args,int types[]){
		return this.getJdbcTemplate().query(sql, args,types,new InOutFullRowMapper());
	}
	

	public int queryInt(String sql,Object[] args,int types[]) {
		Integer i = this.getJdbcTemplate().queryForObject(sql, args,types,Integer.class);
		if(i == null) i=0;
		return i;
	}



	public List<Map<String, Object>> queryForList(String sql, Object[] args,
			int[] types) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().queryForList(sql, args, types);
	}
	


}
