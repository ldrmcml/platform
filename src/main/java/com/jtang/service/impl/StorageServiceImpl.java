/**
 * 
 */
package com.jtang.service.impl;

import java.sql.Types;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.jtang.dao.StorageManagerDao;
import com.jtang.model.Sensor;
import com.jtang.model.Storage;
import com.jtang.service.IStorageService;

/**
 * @author Administartor
 *
 */
public class StorageServiceImpl implements IStorageService {

	public StorageManagerDao storageManager;
	
	/**
	 * @return the storageManager
	 */
	public StorageManagerDao getStorageManager() {
		return storageManager;
	}

	/**
	 * @param storageManager the storageManager to set
	 */
	public void setStorageManager(StorageManagerDao storageManager) {
		this.storageManager = storageManager;
	}

	/* (non-Javadoc)
	 * @see com.jtang.service.IStorageService#getStorageListByUser(java.lang.String)
	 */
	public List<Storage> getStorageListByIds(String [] storageId,String enterpriseId) {
		// TODO Auto-generated method stub
		if(storageId == null) return null;
		String sql = "select * from storage where id in ("; 
		
		for(int i=0;i<storageId.length;i++){
			sql=sql+storageId[i];
			if(i!=storageId.length-1){
				sql+=",";
			}
		}
		
		sql+=")";
		
		sql += " and enterpriseId = ?";
		
		Object [] params ={enterpriseId};
		int [] argTypes = {Types.VARCHAR};
		
		return storageManager.query(sql, params,argTypes);
	}
	
	public int addStorageUse(int storageId, int count) {
		// TODO Auto-generated method stub
		String sql = "UPDATE storage SET used=used+? WHERE id=?";
		Object[] paras = {count,storageId};
		int[] types = {Types.INTEGER,Types.INTEGER};
		return storageManager.update(sql, paras, types);
	}

	public int minusStorageUse(int storageId,  int count) {
		// TODO Auto-generated method stub
		String sql = "UPDATE storage SET used=used-? WHERE id=?";
		Object[] paras = {count,storageId};
		int[] types = {Types.INTEGER,Types.INTEGER};
		return storageManager.update(sql, paras, types);
	}

	public int updateStorageUse(int storageId) {
		// TODO Auto-generated method stub
		String sql = "UPDATE storage SET used = (SELECT SUM(pro.proCount) FROM pro WHERE pro.storageId=? ) WHERE storage.id=?";
		Object[] paras = {storageId,storageId};
		int[] types = {Types.INTEGER,Types.INTEGER};
		return storageManager.update(sql, paras, types);
	}


	public boolean isStorageExists(int storageId) {
		// TODO Auto-generated method stub
		String sql = "select * from storage where id=?";
		Object[] args={storageId};
		int [] types = {Types.INTEGER};
		List<Storage> storage = storageManager.query(sql, args,types);
		if(storage.size() == 1){
			return true;
		}else{
			return false;
		}
		
	}

	public int updateStorage(Storage storage) {
		// TODO Auto-generated method stub
		String sql = "update storage set length=?,width=?,high=?,capacity=?,name=?,used=?,maxTemp=?,minTemp=? where id = ?";
		Object [] args = {storage.getLength(),storage.getWidth(),storage.getHigh(),
						 storage.getCapacity(),storage.getName(),storage.getUsed(),storage.getMaxTemp(),storage.getMinTemp(),
						 storage.getId()};
		int [] types = {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER,
						Types.FLOAT,Types.FLOAT,Types.INTEGER};
		return storageManager.update(sql, args, types);
	}

	public int updateMaxMinTemp(int storageId, float maxTemp, float minTemp) {
		// TODO Auto-generated method stub
		String sql = "update storage set maxTemp=?,minTemp=? where id=?";
		Object[] args = {maxTemp,minTemp,storageId};
		int [] types = {Types.FLOAT,Types.FLOAT,Types.INTEGER};
		
		return storageManager.update(sql, args, types);
	}

}
