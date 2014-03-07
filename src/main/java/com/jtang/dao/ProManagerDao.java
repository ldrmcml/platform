package com.jtang.dao;

import java.util.List;

import com.jtang.model.Product;

public interface ProManagerDao {
	public List<Product> query(String sql,Object[] args,int[] argTypes);
    public int update(String sql,Object[] args,int[] argTypes);
    public int insert(String sql,Object[] args,int[] argTypes);
}
