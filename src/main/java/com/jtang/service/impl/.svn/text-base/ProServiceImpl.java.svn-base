package com.jtang.service.impl;

import java.sql.Types;
import java.util.List;

import com.jtang.dao.ProManagerDao;
import com.jtang.model.Product;
import com.jtang.service.IProService;

public class ProServiceImpl implements IProService{
	
	private ProManagerDao proManager; 

	public ProManagerDao getProManager() {
		return proManager;
	}

	public void setProManager(ProManagerDao proManager) {
		this.proManager = proManager;
	}

	public List<Product> getAllPro(int storageId) {
		// TODO Auto-generated method stub
		Object [] paras = {storageId};
		int [] argTypes = {Types.INTEGER};
		return proManager.query("select * from pro where storageId=?",paras,argTypes);
	}

	public int addProCount(int id,int addCount) {
		// TODO Auto-generated method stub
		return proManager.update("update pro set proCount=proCount+"+addCount+" where proId="+id, null,null);
	}

	public int minusProCount(int id,int minusCount) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Product getPro(int id) {
		// TODO Auto-generated method stub
		List<Product> pros = proManager.query("select * from pro where proId="+id, null,null);
		if(pros.size() == 1)
		{
			return pros.get(0);
		}
		else 
		{
			return null;
		}
		
	}

	public int updatePro(int id, int storageId, String name, String msg,
			int unit) {
		// TODO Auto-generated method stub
		String sql ="update pro SET proUnit=? , proName=? , proMsg=? WHERE proId=?";
		Object[]  paras =  new Object[]{unit,name,msg,id};
		int[] types = {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		return proManager.update(sql, paras, types);
	}

	public int addPro(int storageId, String name, String msg, int unit) {
		// TODO Auto-generated method stub
		String sql ="insert into pro(storageId,proName,proMsg,proUnit) values(?,?,?,?)";
		Object[]  paras =  new Object[]{storageId,name,msg,unit};
		int[] types = {Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		return proManager.update(sql, paras, types);
	}

	public int deletePro(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from pro where proId=?";
		Object[]  paras =  new Object[]{id};
		int[] types = {Types.INTEGER};
		
		return proManager.update(sql, paras, types);
	}

}
