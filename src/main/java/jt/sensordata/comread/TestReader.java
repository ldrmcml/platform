/**
 * 
 */
package jt.sensordata.comread;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import jt.sensordata.main.StartDataCenter;

import com.alibaba.fastjson.JSON;
import com.yongboy.socketio.server.transport.IOClient;

/**
 * @author Administartor
 *
 */
public class TestReader extends Thread {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public void run(){
		try {
			while(true){
				Random rand = new Random();
				float temp = rand.nextFloat();
				sendTempToAllClient("ABCD",formatter.format(new Date()),temp+"");
				Thread.sleep(2000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 /**
     * 将采集到的温度数据发送给所有监听该设备的客户端
     */
    @SuppressWarnings("unchecked")
	public void sendTempToAllClient(String sensorAddr,String time,String temperature){
    	HashMap sendMessage = new HashMap();
    	//String content = "5:::{\"name\":\"my\",\"args\":[{\"my\":\"fffff\"}]}";
		//client.send(content);
    	sendMessage.put("name","temperature");
    	HashMap<String,String> tat =  new HashMap<String,String>();
    	tat.put("time", time);
    	tat.put("temp", temperature);
    	sendMessage.put("args", tat);
    	
    	HashMap<String,ArrayList<IOClient>> clientList = StartDataCenter.getClientList();
    	ArrayList<IOClient> subClientList = clientList.get(sensorAddr);
    	if(subClientList!=null){
			 for(int i=0;i<subClientList.size();i++){
				 IOClient tempClient = subClientList.get(i);
				 tempClient.send("5:::"+JSON.toJSONString(sendMessage));
				 
			 }
		 }
    	
    }
}
