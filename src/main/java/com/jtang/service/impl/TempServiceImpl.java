/**
 * 
 */
package com.jtang.service.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jtang.dao.TempManagerDao;
import com.jtang.model.Temperature;
import com.jtang.service.ITempService;

/**
 * @author Administartor
 *
 */
public class TempServiceImpl implements ITempService {
	
	public TempManagerDao tempManager;
	

	/**
	 * @return the tempManager
	 */
	public TempManagerDao getTempManager() {
		return tempManager;
	}

	/**
	 * @param tempManager the tempManager to set
	 */
	public void setTempManager(TempManagerDao tempManager) {
		this.tempManager = tempManager;
	}

	/* (non-Javadoc)
	 * @see com.jtang.service.ITempService#getDataByTime(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Temperature> getDataByTime(String extAddr, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		String sql = "select * from temperature where sensorExtAddr = ? and (recordTime between ? and ?) order by recordTime";
		Object [] args = {extAddr,startTime,endTime};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		return tempManager.query(sql, args,argTypes);
	}

	/* (non-Javadoc)
	 * @see com.jtang.service.ITempService#insertRecord(com.jtang.model.Temperature)
	 */
	public int insertRecord(Temperature temp) {
		// TODO Auto-generated method stub
		String sql = "insert into temperature(sensorExtAddr,recordTime,temperature) values(?,?,?)";
		Object [] args = {temp.getSensorExtAddr(),temp.getRecordTime(),temp.getTemperature()};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.FLOAT};
		return tempManager.add(sql, args, argTypes);
	}
	
	/**
	 *orderbyNumber传入1的时候升序排序，反之降序
	 */
	public List<Temperature> getDataUseLimit(String extAddr, String startTime,
			String endTime, int limitNumber, int orderbyNumber) {
		// TODO Auto-generated method stub
		String orderbyRule = orderbyNumber == 1?"":"desc";
		String sql = "select * from temperature where sensorExtAddr = ? and (recordTime between ? and ?) order by temperature "
				+orderbyRule+ " limit ?";
		Object [] args = {extAddr,startTime,endTime,limitNumber};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		return tempManager.query(sql, args,argTypes);
	}

	public List<Temperature> getExceptionDataByTime(String extAddr,
			String startTime, String endTime, float maxTemp, float minTemp) {
		// TODO Auto-generated method stub
		String sql = "select * from temperature where sensorExtAddr = ? and (recordTime between ? and ?) "
				+ "and (temperature not between ? and ?) order by recordTime";
		Object [] args = {extAddr,startTime,endTime,minTemp,maxTemp};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.FLOAT,Types.FLOAT};
		return tempManager.query(sql, args,argTypes);

	}

	public List<Temperature> getExceptionDataUseLimit(String extAddr,
			String startTime, String endTime, int limitNumber,
			int orderbyNumber, float maxTemp, float minTemp) {
		// TODO Auto-generated method stub
		String orderbyRule = orderbyNumber == 1?"":"desc";
		String sql = "select * from temperature where sensorExtAddr = ? and (recordTime between ? and ?) "
				+ " and (temperature not between ? and ?) order by temperature "
				+orderbyRule+ " limit ?";
		Object [] args = {extAddr,startTime,endTime,maxTemp,minTemp,limitNumber};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.FLOAT,Types.FLOAT,Types.INTEGER};
		return tempManager.query(sql, args,argTypes);
	}

	public int getNormalNumber(String extAddr, String startTime,
			String endTime, float maxTemp, float minTemp) {
		// TODO Auto-generated method stub
		String sql = "select count(*) num from temperature where sensorExtAddr = ? and (recordTime between ? and ?) "
				+ " and (temperature  between ? and ?)";
		Object [] args = {extAddr,startTime,endTime,minTemp,maxTemp};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.FLOAT,Types.FLOAT};
		List<Map<String,Object>> numList = tempManager.queryForList(sql, args, argTypes);
		if(numList!=null && numList.size() == 1){
			return Integer.parseInt( numList.get(0).get("num").toString());
		}
		return 0;
	}

	public int getExceedNumber(String extAddr, String startTime,
			String endTime, float maxTemp) {
		// TODO Auto-generated method stub
		String sql = "select count(*) num from temperature where sensorExtAddr = ? and (recordTime between ? and ?) "
				+ " and (temperature  > ? )";
		Object [] args = {extAddr,startTime,endTime,maxTemp};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.FLOAT};
		List<Map<String,Object>> numList = tempManager.queryForList(sql, args, argTypes);
		if(numList!=null && numList.size() == 1){
			return Integer.parseInt( numList.get(0).get("num").toString());
		}
		return 0;
	}

	public int getLowerNumber(String extAddr, String startTime, String endTime,
			float minTemp) {
		// TODO Auto-generated method stub
		String sql = "select count(*) num from temperature where sensorExtAddr = ? and (recordTime between ? and ?) "
				+ " and (temperature  < ? )";
		Object [] args = {extAddr,startTime,endTime,minTemp};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.FLOAT};
		List<Map<String,Object>> numList = tempManager.queryForList(sql, args, argTypes);
		if(numList!=null && numList.size() == 1){
			return Integer.parseInt( numList.get(0).get("num").toString());
		}
		return 0;
	}

}
