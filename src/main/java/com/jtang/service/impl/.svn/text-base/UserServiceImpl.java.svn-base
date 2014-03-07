package com.jtang.service.impl;

import java.sql.Types;
import java.util.List;

import com.jtang.model.User;
import com.jtang.dao.UserManagerDao;
import com.jtang.service.IUserService;

public class UserServiceImpl implements IUserService{
	
	private UserManagerDao userManager;

	
	public UserManagerDao getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManagerDao userManager) {
		this.userManager = userManager;
	}

	public int registerUser() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @author yyj
	 * @param
	 * 	   id 
	 * 	   passwd  
	 * @return
	 *
	 */
	public User loginUser(String id, String passwd) {
		// TODO Auto-generated method stub

		Object [] paras=new String[2];
		paras[0] = id;
		paras[1] = passwd;
		int [] argTypes = {Types.VARCHAR,Types.VARCHAR};
		List<User> users=userManager.query("select * from user where id=? and password=?",paras,argTypes);
		if(users.size() == 1){
			return users.get(0);
		}else{
			return null;
		}

	}

	public String[] getStorageListByUser(String userId) {
		// TODO Auto-generated method stub
		String sql = "select * from user where id=?";
		Object [] paras = {userId};
		int [] argTypes = {Types.VARCHAR};
		List<User> users=userManager.query(sql,paras,argTypes);
		if(users.size() == 1){
			
			return users.get(0).getStorageList()==null?null:users.get(0).getStorageList().split(":");
		}else{
			return null;
		}
	}
	
		public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userManager.query("select * from user",null,null);
	}

}
