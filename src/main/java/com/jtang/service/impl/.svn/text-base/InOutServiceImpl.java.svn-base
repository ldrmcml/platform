package com.jtang.service.impl;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.jtang.dao.InOutManagerDao;
import com.jtang.model.InOut;
import com.jtang.service.IInOutService;


public class InOutServiceImpl implements IInOutService{

	private InOutManagerDao inoutManager;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public InOutManagerDao getInoutManager() {
		return inoutManager;
	}

	public void setInoutManager(InOutManagerDao inoutManager) {
		this.inoutManager = inoutManager;
	}

	/**
	 * 增加单个出入库
	 * @param cardNum
	 * @param cardTime
	 * @param action
	 * @param personId
	 * @param rfidCount
	 * @return
	 */
	public int addInOut(String cardNum,String cardTime,int action,String personId,int rfidCount,int proId,int storageId) 
	{
		// TODO Auto-generated method stub
		Object[] paras = {cardNum,action,personId,rfidCount,cardTime,proId,"",storageId};
		return inoutManager.update("insert into in_out(cardNum,action,personId,bindCount,time,proId,barCode,storageId) values(?,?,?,?,?,?,?,?)", 
				paras);
		
	}
 
	/**
	 * 批量增加出入库
	 * @param size
	 * @param action
	 * @param personId
	 * @param rfidCount
	 * @param cards
	 * @param cardsTime
	 * @return
	 */
	public void addInOuts(int size,int action,String personId,int rfidCount,JSONArray cards,JSONArray cardsTime,int proId,int storageId)
	{
		for(int i = 0;i < size;i++)
		{
			addInOut(cards.getString(i),cardsTime.getString(i),action,personId,rfidCount,proId,storageId);
		}
		return ;
	}
	
	/**
	 * 
	 * @param action 
	 *        1 查询入库
	 *        0 查询出库
	 *        2/其他  查询所有
	 * @return
	 */
	private List<InOut> queryTodayInOut(int action,int storageId) {
		// TODO Auto-generated method stub
		
		String nowTime = formatter.format(new Date());
		
		Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0); 
        c.set(Calendar.MINUTE, 0); 
        c.set(Calendar.SECOND, 0); 
        Date d = c.getTime();
        String startTime = formatter.format(d);
        
        String sql = "select in_out.*,pro.proName as proName from in_out,pro where in_out.time <? and in_out.time >= ? and in_out.proId = pro.proId and in_out.storageId =? ";
        if(action == 0 || action == 1)
        	sql = sql +" and action="+action;
        Object[] paras ={nowTime,startTime,storageId};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		List<InOut> inouts=inoutManager.queryWithPro(sql,paras,types);
		return inouts;
	}


	public int countInToday(int storageId) {
		// TODO Auto-generated method stub
		String nowTime = formatter.format(new Date());
		
		Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0); 
        c.set(Calendar.MINUTE, 0); 
        c.set(Calendar.SECOND, 0); 
        Date d = c.getTime();
        String startTime = formatter.format(d);
        
        Object[] paras ={nowTime,startTime,storageId};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		return inoutManager.queryInt("SELECT SUM(bindCount) from in_out WHERE action=1 and time <?and time >= ? and storageId=?",paras,types );
	}

	public int countOutToday(int storageId) {
		// TODO Auto-generated method stub
		String nowTime = formatter.format(new Date());
		
		Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0); 
        c.set(Calendar.MINUTE, 0); 
        c.set(Calendar.SECOND, 0); 
        Date d = c.getTime();
        String startTime = formatter.format(d);
        
        Object[] paras ={nowTime,startTime,storageId};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		return inoutManager.queryInt("SELECT SUM(bindCount) from in_out WHERE action=0 and time <?and time >= ? and storageId=?",paras,types );
	}

	/**
	 * 
	 * @param proId
	 * @param personId
	 * @param startTime
	 * @param action
	 *        1 查询入库
	 *        0 查询出库
	 *        2/其他  查询所有
	 * @return
	 */
	private List<InOut> searchTodayInOut(int proId, String personId,String startTime,int action,int storageId) {
		// TODO Auto-generated method stub
		String nowTime = formatter.format(new Date());
		
		//暂时写死
		Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0); 
        c.set(Calendar.MINUTE, 0); 
        c.set(Calendar.SECOND, 0); 
        Date d = c.getTime();
        startTime = formatter.format(d);
        
        String sql = "select in_out.*,pro.proName as proName from in_out,pro where in_out.time <? and in_out.time >= ? and in_out.proId = ? and in_out.personId = ? and in_out.proId = pro.proId and in_out.storageId=? ";
        if(action ==0 || action == 1)
        	sql = sql+ " and action="+action;
    
        Object[] paras ={nowTime,startTime,proId,personId,storageId};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		List<InOut> inouts =inoutManager.queryWithPro(sql,paras,types);
		
		return inouts;
	}
	
	/**
	 * 
	 * @param action
	 *      1   入库
	 *      0   出库
	 * @return
	 */
	private List<Map<String, Object>> queryTodayAllGroupByTime(int action,int storageId) {
		// TODO Auto-generated method stub
		
		String nowTime = formatter.format(new Date());

		Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0); 
        c.set(Calendar.MINUTE, 0); 
        c.set(Calendar.SECOND, 0); 
        Date d = c.getTime();
        String startTime = formatter.format(d);
        
        String sql = "select time,SUM(bindCount) as count FROM in_out where time<? and time >=? and storageId=? and action =? GROUP BY time";
        Object[] paras ={nowTime,startTime,storageId,action};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER};
		return inoutManager.queryForList(sql, paras, types);
	}
	
	/**
	 * 根据条件
	 * @param action
	 * 		2 全部
	 *   	1 入库
	 *   	0 出库
	 * @param storageId
	 *      仓库ID 必须指定
	 * @param startTime
	 * @param endTime
	 * @param proId
	 * 	    0 不限制，查询全部商品
	 * @param personId
	 *      0 不限制，查询全部商品
	 * @return
	 */
	private List<Map<String, Object>> queryAllGroupByTimeAndPro(int storageId,String startTime,String endTime,int proId,int personId,int action)
	{
		String sql ="SELECT pro.proName as name,time,SUM(bindCount) as count FROM in_out,pro where in_out.time <? and in_out.time >= ? and in_out.proId = pro.proId and in_out.storageId=? ";
		if(proId != 0)
			sql = sql + " and in_out.proId=" + proId;
		if(personId != 0)
			sql = sql + " and in_out.personId=" + personId;
		 if(action ==0 || action == 1)
	        sql = sql+ " and action="+action;
		 
		 sql += " GROUP BY time,in_out.proId";
		 Object[] paras ={endTime,startTime,storageId};
	     int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
	     return inoutManager.queryForList(sql, paras, types);
		 
	}
	
	/**
	 * 根据条件查询出入库信息
	 * @param action
	 * 		2 全部
	 *   	1 入库
	 *   	0 出库
	 * @param storageId
	 *      仓库ID 必须指定
	 * @param startTime
	 * @param endTime
	 * @param proId
	 * 	    0 不限制，查询全部商品
	 * @param personId
	 *      0 不限制，查询全部商品
	 * @return
	 */
	private List<InOut> queryInOut(int action,int storageId,String startTime,
			String endTime, int proId, int personId){
		
		String sql ="select in_out.*,pro.proName as proName from in_out,pro where in_out.time <? and in_out.time >= ? and in_out.proId = pro.proId and in_out.storageId=? ";
		if(proId != 0)
			sql = sql + " and in_out.proId=" + proId;
		if(personId != 0)
			sql = sql + " and in_out.personId=" + personId;
		 if(action ==0 || action == 1)
	        sql = sql+ " and action="+action;

		Object[] paras ={endTime,startTime,storageId};
        int[] types = {Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		return inoutManager.queryWithPro(sql,paras,types);
	}
	

	public List<InOut> queryTodayIn(int storageId) {
		// TODO Auto-generated method stub	
		return this.queryTodayInOut(1,storageId);
	}

	public List<InOut> queryTodayOut(int storageId) {
		// TODO Auto-generated method stub
		return this.queryTodayInOut(0,storageId);
	}

	public List<InOut> queryTodayAll(int storageId) {
		// TODO Auto-generated method stub
		return this.queryTodayInOut(2,storageId);
	}

	public List<InOut> searchTodayIn(int proId, String personId,
			String startTime,int storageId) {
		// TODO Auto-generated method stub
		return this.searchTodayInOut(proId, personId, startTime, 1,storageId);
	}

	public List<InOut> searchTodayOut(int proId, String personId,
			String startTime,int storageId) {
		// TODO Auto-generated method stub
		return this.searchTodayInOut(proId, personId, startTime, 0,storageId);
	}

	public List<Map<String, Object>> queryTodayInGroupByTime(int storageId) {
		// TODO Auto-generated method stub
		return this.queryTodayAllGroupByTime(1,storageId);
	}

	public List<Map<String, Object>> queryTodayOutGroupByTime(int storageId) {
		// TODO Auto-generated method stub
		return this.queryTodayAllGroupByTime(0,storageId);
	}

	public List<InOut> queryAll(int storageId, String startTime,
			String endTime, int proId, int personId) {
		// TODO Auto-generated method stub
		return this.queryInOut(2, storageId, startTime, endTime, proId, personId);
	}

	public List<InOut> queryIn(int storageId, String startTime, String endTime,
			int proId, int personId) {
		// TODO Auto-generated method stub
		return this.queryInOut(1, storageId, startTime, endTime, proId, personId);
	}

	public List<InOut> queryOut(int storageId, String startTime,
			String endTime, int proId, int personId) {
		// TODO Auto-generated method stub
		return this.queryInOut(0, storageId, startTime, endTime, proId, personId);
	}

	public List<Map<String, Object>> queryInGroupByTimeAndPro(int storageId,
			String startTime, String endTime, int proId, int personId) {
		// TODO Auto-generated method stub
		return this.queryAllGroupByTimeAndPro(storageId, startTime, endTime, proId, personId, 1);
	}

	public List<Map<String, Object>> queryOutGroupByTimeAndPro(int storageId,
			String startTime, String endTime, int proId, int personId) {
		// TODO Auto-generated method stub
		return this.queryAllGroupByTimeAndPro(storageId, startTime, endTime, proId, personId, 0);
	}




}
