/**
 * 
 */
package com.jtang.servicethread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jt.sensordata.comread.ComRead;

import com.jtang.model.Temperature;
import com.jtang.service.ITempService;

/**
 * @author Administartor
 *
 */
public class StoreTemp implements Runnable {

	public ITempService tempService;
	
	/**
	 * @return the tempService
	 */
	public ITempService getTempService() {
		return tempService;
	}

	/**
	 * @param tempService the tempService to set
	 */
	public void setTempService(ITempService tempService) {
		this.tempService = tempService;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Thread.sleep(60*1000);
				HashMap<String,Float> averageTemp = ComRead.getAverageTemp();
				
				Iterator it = (Iterator) averageTemp.entrySet().iterator();
				
				while(it.hasNext()){
					Map.Entry entry = (Map.Entry) it.next();
					String extAddr = (String) entry.getKey();
					Float temperature = (Float) entry.getValue();
					Temperature temp = new Temperature();
					temp.setSensorExtAddr(extAddr);
					temp.setTemperature(temperature);
					temp.setRecordTime(formatter.format(new Date()));					
					tempService.insertRecord(temp);					
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
