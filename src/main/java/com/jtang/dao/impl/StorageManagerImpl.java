/**
 * 
 */
package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.StorageManagerDao;
import com.jtang.dao.impl.TempManagerImpl.TempRowMapper;
import com.jtang.model.Storage;


/**
 * @author Administartor
 *
 */
public class StorageManagerImpl extends JdbcDaoSupport implements StorageManagerDao {
	
	
	class StorageRowMapper implements RowMapper<Storage> {
        //将查询到的结果集转换为Sensor对象
        public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Storage s = new Storage();
        	s.setId(rs.getInt("id")); 
        	s.setLength(rs.getInt("length"));
        	s.setWidth(rs.getInt("width"));
        	s.setHigh(rs.getInt("high"));
        	s.setCapacity(rs.getInt("capacity"));
        	s.setDescription(rs.getString("description"));
        	s.setCreator(rs.getString("creator"));
        	s.setCreateTime(rs.getString("createTime"));
        	s.setName(rs.getString("name"));
        	s.setEnterpriseId(rs.getString("enterpriseId"));
        	s.setUsed(rs.getInt("used"));
        	s.setMaxTemp(rs.getFloat("maxTemp"));
        	s.setMinTemp(rs.getFloat("minTemp"));
            return s;
        }
	};

	/* (non-Javadoc)
	 * @see com.jtang.dao.StorageManagerDao#query(java.lang.String, java.lang.Object[], int[])
	 */
	public List<Storage> query(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args,argTypes, new StorageRowMapper());
	}
	
	public List<Storage> queryUseMapSql(String sql,MapSqlParameterSource parameters) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, new StorageRowMapper(),parameters);
	}

	/* (non-Javadoc)
	 * @see com.jtang.dao.StorageManagerDao#add(java.lang.String, java.lang.Object[], int[])
	 */
	public int add(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}

	/* (non-Javadoc)
	 * @see com.jtang.dao.StorageManagerDao#update(java.lang.String, java.lang.Object[], int[])
	 */
	public int update(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}

	/* (non-Javadoc)
	 * @see com.jtang.dao.StorageManagerDao#delete(java.lang.String, java.lang.Object[], int[])
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

}
