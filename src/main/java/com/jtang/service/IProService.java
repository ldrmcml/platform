package com.jtang.service;

import java.util.List;

import com.jtang.model.Product;

public interface IProService {
	public List<Product> getAllPro(int storageId);
	public int addProCount(int id,int addCount);
	public int minusProCount(int id,int minusCount);
	public Product getPro(int id);
	public int updatePro(int id,int storageId,String name,String msg ,int unit);
	public int addPro(int storageId,String name,String msg,int unit);
	public int deletePro(int id);
}
