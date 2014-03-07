package com.jtang.dao;

import java.util.List;

import com.jtang.model.ProductQuery;

/**
 * 
 * @author chenminglong
 *
 */
public interface ProductQueryManagerDao {
	

	/**
	 * 获取数据库中所有的产品
	 * @return
	 */
	public List<ProductQuery> query(String sql, Object[] args);

}
