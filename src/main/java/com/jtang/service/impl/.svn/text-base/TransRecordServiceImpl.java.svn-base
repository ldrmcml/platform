package com.jtang.service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import com.jtang.dao.TransRecordManagerDao;
import com.jtang.model.TransRecord;
import com.jtang.service.ITransRecordService;

/**
 * @author chenminglong
 *
 */
public class TransRecordServiceImpl implements ITransRecordService {
	
    private TransRecordManagerDao transRecordManager;  //由Spring注入实体类


	public TransRecordManagerDao getTransRecordManager() {
		return transRecordManager;
	}

	public void setTransRecordManager(TransRecordManagerDao transRecordManager) {
		this.transRecordManager = transRecordManager;
	}

	public List<TransRecord> getAllTransRecords() {
		// TODO Auto-generated method stub
		String sql = "select * from transportation_record";
		List<TransRecord> AllTransRecords = transRecordManager.query(sql, null);
		return AllTransRecords;
	}

	public int addATransRecord(TransRecord transRecord) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO transportation_record(StartPlace,EndPlace,StartTime,EndTime,Temperature)"
				+ "VALUES(?, ?, ?, ?, ?)";
		/*
		Object[] args = {product.getName(),product.getBarcode(),product.getProductionDate(),product.getStorageRecordId()
				,product.getTransportationRecordId(),product.getRawmaterialRecordId(),product.getRecorderId(),
				product.getRecordDate()};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
				*/
		Object[] args = {transRecord.getStartPlace(),transRecord.getEndPlace(),transRecord.getStartTime(),transRecord.getEndTime(),
				transRecord.getTemperature()};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP,Types.FLOAT};
		return transRecordManager.add(sql, args, argTypes);
	}

	public int updateATransRecord(TransRecord transRecord) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteATransRecord(String extAddr) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int findTransRecordId() {
		// TODO Auto-generated method stub
		String sql="SELECT MAX(Id) AS LargestId FROM transportation_record";
		return transRecordManager.queryRetInt(sql);
	}

}
