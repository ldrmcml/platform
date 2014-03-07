/**
 * 
 */
package com.jtang.servicethread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.jtang.service.ISensorService;

import jt.sensordata.comread.ComRead;

/**
 * @author zxy
 *
 */
public class UpdateSensorWorkTime implements Runnable {
	
	public Logger logger= Logger.getLogger(this.getClass());
	public ISensorService sensorService;
	

	public ISensorService getSensorService() {
		return sensorService;
	}


	public void setSensorService(ISensorService sensorService) {
		this.sensorService = sensorService;
	}	

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap<String,String> extToShort = ComRead.getExtToShort();
			
			Iterator it = extToShort.entrySet().iterator();
			
			while(it.hasNext()){
				Map.Entry entry = (Entry) it.next();
				String extAddr = (String) entry.getKey();
				int result = sensorService.updateWorkTime(extAddr, 1);
				if(result == 0){
					logger.error("尝试更新传感器工作时间，但发生了错误！");
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
					
	}

}
