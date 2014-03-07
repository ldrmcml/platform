/**
 * 
 */
package com.jtang.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.jtang.dao.SensorManagerDao;
import com.jtang.model.Sensor;


/**
 * @author zhouxinyu
 *
 */
/*
 * CREATE  TABLE `storagemanager`.`Sensor` (

  `extAddr` VARCHAR(65) NOT NULL ,

  `shortAddr` VARCHAR(10) NULL ,

  `nodeTypes` INT NULL COMMENT '0表示协调器,1表示路由器,2表示终端节点' ,

  `workStatus` INT NULL COMMENT '0表示停止,1表示正常工作,2表示异常工作' ,

  `fatherNode` VARCHAR(10) NULL COMMENT '父节点的shortaddr' ,

  PRIMARY KEY (`extAddr`) ,

  UNIQUE INDEX `extAddr_UNIQUE` (`extAddr` ASC) );
 */
public class SensorManagerImpl extends JdbcDaoSupport implements SensorManagerDao {

	
	class SensorRowMapper implements RowMapper<Sensor> {
        //将查询到的结果集转换为Sensor对象
        public Sensor mapRow(ResultSet rs, int rowNum) throws SQLException {
        	Sensor s = new Sensor();
        	s.setExtAddr(rs.getString("extAddr"));
        	s.setShortAddr(rs.getString("shortAddr"));
        	s.setNodeTypes(rs.getInt("nodeTypes"));
        	s.setWorkStatus(rs.getInt("workStatus"));
        	s.setFatherNode(rs.getString("fatherNode"));
        	s.setPosition(rs.getString("position"));
        	s.setCreator(rs.getString("creator"));
        	s.setCreateTime(rs.getString("createTime"));
        	s.setMender(rs.getString("mender"));
        	s.setMendTime(rs.getString("mendTime"));
        	s.setWorkTime(rs.getInt("workTime"));
        	s.setStorageId(rs.getInt("storageId"));
        	s.setName(rs.getString("name"));
            return s;
        }
	};

	/* (non-Javadoc)
	 * @see com.jtang.dao.SensorManagerDao#getAllSensors()
	 */
	public List<Sensor> query(String sql, Object[] args,int []types) {
		// TODO Auto-generated method stub
		return this.getJdbcTemplate().query(sql, args,types, new SensorRowMapper());
	}

	public int add(String sql, Object[] args,int [] argTypes) {
		// TODO Auto-generated method stub
		//return the number of rows affected
		return executeUpdate(sql, args,argTypes);

	}

	public int update(String sql, Object[] args,int [] argTypes) {
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

	public int delete(String sql, Object[] args, int[] argTypes) {
		// TODO Auto-generated method stub
		return executeUpdate(sql, args,argTypes);
	}

	





}
