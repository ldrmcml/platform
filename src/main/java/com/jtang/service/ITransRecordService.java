package com.jtang.service;

import java.util.List;
import com.jtang.model.TransRecord;

public interface ITransRecordService {
	/**
	 * 获取数据库中所有运输记录
	 * @return
	 */
	public List<TransRecord > getAllTransRecords();
	
	/**
	 * 向数据库中添加一条运输记录
	 * 成功则返回1，失败则返回0
	 */
	public int addATransRecord(TransRecord transRecord);
	
	/**
	 * 修改运输记录
	 */
	public int updateATransRecord(TransRecord transRecord);
	
	/**
	 * 删除一条指定的TransRecord
	 */
	public int deleteATransRecord(String extAddr);
	
	/**
	 * 找出运输记录最大Id值
	 */
	public int findTransRecordId();
	
	
}
