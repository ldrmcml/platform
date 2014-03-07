package com.jtang.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.jtang.model.InOut;

public interface IInOutService {
	public int addInOut(String cardNum,String cardTime,int action,String personId,int rfidCount,int proId,int storageId);
	public void addInOuts(int size,int action,String personId,int rfidCount,JSONArray cards,JSONArray cardsTime,int proId,int storageId);
	public List<InOut> searchTodayIn(int proId,String personid,String startTime,int storageId);
	public List<InOut> searchTodayOut(int proId,String personid,String startTime,int storageId);
	public int countInToday(int storageId);
	public int countOutToday(int storageId);
	public List<InOut> queryTodayIn(int storageId);
	public List<InOut> queryTodayOut(int storageId); 
	public List<InOut> queryTodayAll(int storageId);
	
	/**
	 * 根据时间分类今日出入库信息
	 * @param storageId
	 * @return
	 */
	
	public List<Map<String,Object>> queryTodayInGroupByTime(int storageId);
	public List<Map<String,Object>> queryTodayOutGroupByTime(int storageId);
	public List<Map<String,Object>> queryInGroupByTimeAndPro(int storageId,String startTime,String endTime,int proId,int personId);
	public List<Map<String,Object>> queryOutGroupByTimeAndPro(int storageId,String startTime,String endTime,int proId,int personId);
	
	/**
	 * 按指定条件仓库出入库操作
	 * @param storageId
	 * @return
	 */
	public List<InOut> queryAll(int storageId,String startTime,String endTime,int proId,int personId);
	public List<InOut> queryIn(int storageId,String startTime,String endTime,int proId,int personId);
	public List<InOut> queryOut(int storageId,String startTime,String endTime,int proId,int personId);
}
