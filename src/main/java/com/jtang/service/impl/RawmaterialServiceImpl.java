package com.jtang.service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import com.jtang.dao.RawmaterialManagerDao;
import com.jtang.model.Rawmaterial;
import com.jtang.service.IRawmaterialService;

/**
 * @author chenminglong
 *
 */
public class RawmaterialServiceImpl implements IRawmaterialService {
	
    private RawmaterialManagerDao rawmaterialManager;  //由Spring注入实体类

	public RawmaterialManagerDao getRawmaterialManager() {
		return rawmaterialManager;
	}

	public void setRawmaterialManager(RawmaterialManagerDao rawmaterialManager) {
		this.rawmaterialManager = rawmaterialManager;
	}

	public List<Rawmaterial> getAllRawmaterials() {
		// TODO Auto-generated method stub
		String sql = "select * from rawmaterial";
		List<Rawmaterial> AllRawmaterials = rawmaterialManager.query(sql, null);
		return AllRawmaterials;
	}

	public int addARawmaterial(Rawmaterial rawmaterial) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO rawmaterial(Name,Manufacturer,ProductionPlace,ProductionDate)"
				+ "VALUES(?, ?, ?, ?)";
		/*
		Object[] args = {product.getName(),product.getBarcode(),product.getProductionDate(),product.getStorageRecordId()
				,product.getTransportationRecordId(),product.getRawmaterialRecordId(),product.getRecorderId(),
				product.getRecordDate()};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
				*/
		Object[] args = {rawmaterial.getName(),rawmaterial.getManufacturer(),rawmaterial.getProductionPlace(),rawmaterial.getProductionDate()};
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
		return rawmaterialManager.add(sql, args, argTypes);
	}

	public int updateARawmaterial(Rawmaterial rawmaterial) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteARawmaterial(String extAddr) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int findRawmaterialId() {
		// TODO Auto-generated method stub
		String sql="SELECT MAX(Id) AS LargestId FROM rawmaterial";
		return rawmaterialManager.queryRetInt(sql);
	}

}
