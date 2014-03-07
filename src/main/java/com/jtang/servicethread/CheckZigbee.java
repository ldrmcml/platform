package com.jtang.servicethread;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;



import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jtang.service.ISensorService;

import jt.sensordata.comread.ComRead;

public class CheckZigbee implements Runnable {

	public Logger logger= Logger.getLogger(this.getClass());
	public ISensorService sensorService;
	

	public ISensorService getSensorService() {
		return sensorService;
	}


	public void setSensorService(ISensorService sensorService) {
		this.sensorService = sensorService;
	}	

	public void run() {
		// TODO Auto-generated method stub
		while(true){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				
				HashMap<String,HashMap<String,String>> networkInfo = ComRead.getNetworkInfo();
				HashMap<String,String> extToShort = ComRead.getExtToShort();
				Iterator it = (Iterator) networkInfo.entrySet().iterator();
				
				ArrayList<String > networkNeedRemove = new ArrayList<String>();
				ArrayList<String > extToShortNeedRemove = new ArrayList<String>();
				
				while(it.hasNext()){
					Map.Entry entry = (Map.Entry) it.next();
					HashMap<String,String> value = (HashMap<String, String>) entry.getValue();
					String shortAddr = (String) entry.getKey();
					String newTime = value.get("newTime");
					
					try {
						long diff = new Date().getTime() - formatter.parse(newTime).getTime();
						System.out.println("diff=="+diff);
						if(diff>5*1000*60){
							//保存需要删除的传感器节点的短地址
							networkNeedRemove.add(shortAddr);
							
					
							System.out.println("diff1=="+diff);
							String extAddr = needRemove(extToShort,shortAddr);
							if(!extAddr.equals("")){
								//保存需要删除的传感器节点的长地址
								extToShortNeedRemove.add(extAddr);
							}
	
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				for(int i = 0;i< networkNeedRemove.size();i++){
					networkInfo.remove(networkNeedRemove.get(i));
					if(i==networkNeedRemove.size()-1){
						
						ComRead.setNetworkInfo(networkInfo);
						ComRead.sendTopToAllClient();
					}					
				}
				for(int i=0;i<extToShortNeedRemove.size();i++){
					int result = sensorService.updateWorkStatus(extToShortNeedRemove.get(i), 0);
					if(0 == result){
						logger.error("CheckZigbee:"+"停止传感器失败");
					}
					extToShort.remove(extToShortNeedRemove.get(i));
					if(i == extToShortNeedRemove.size() - 1){
						ComRead.setExtToShort(extToShort);
						ComRead.sendTopToAllClient();
					}
					
				}
				Thread.sleep(10*1000); //每隔五分钟扫描一次
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param extToShort
	 * @param shortAddr
	 * @return
	 * 返回需要删除传感器节点的长地址,如果不需删除则返回空字符串
	 */
	private String needRemove(HashMap<String, String> extToShort,
			String shortAddr) {
		// TODO Auto-generated method stub
		Iterator it = (Iterator) extToShort.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry) it.next();
			if(((String)entry.getValue()).equals(shortAddr)){
				return (String)entry.getKey();
			}
		}
		return "";
	}

}
