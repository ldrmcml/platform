package jt.sensordata.server;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jt.sensordata.main.StartDataCenter;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yongboy.socketio.server.IOHandlerAbs;
import com.yongboy.socketio.server.transport.IOClient;

/**
 * 
 * @author Administartor
 *ʹ�ô�Handler���ͻ��˵ĵ��÷�ʽ�����ڣ�
 *socket.emit('SensorData', '0X45FF');
 */
public class RTTempDataHandler extends IOHandlerAbs {

	private Logger log = Logger.getLogger(this.getClass());
	
	public void OnConnect(IOClient client) {
		// TODO Auto-generated method stub
		log.debug("A user connected :: "+client.getSessionID());
		
	}

	
	public void OnDisconnect(IOClient client) {
		// TODO Auto-generated method stub
		log.debug("A user disconnected :: " + client.getSessionID());
		//���ÿͻ��˴�clientList�����޳�����Լ��Դ
		removeClient(client);

		client.disconnect();
	}

	
	public void OnMessage(IOClient client, String oriMessage) {
		// TODO Auto-generated method stub
		String jsonString = oriMessage.substring(oriMessage.indexOf('{'));
		jsonString = jsonString.replaceAll("\\\\", "");
		JSONObject jsonObject = JSON.parseObject(jsonString);
		String eventName = jsonObject.get("name").toString();
		if (eventName.equals("SensorData")) {
			 JSONArray argsArray = jsonObject.getJSONArray("args");
             String sensorAddr = argsArray.getString(0);
			 log.debug("User "+client.getSessionID()+" request the temperature data of "+sensorAddr);
			 //������ĳ���������ݵ�client�����б�������ͳһ���ͣ����豸�̵�ַΪ��ʶ��
			 
			 HashMap<String,ArrayList<IOClient>> clientList = StartDataCenter.getClientList();
			 ArrayList<IOClient> subClientList = clientList.get(sensorAddr);
			 if(subClientList!=null){
				 subClientList.add(client);
			 }else{
				 subClientList = new  ArrayList<IOClient>();
				 subClientList.add(client);
			 }
			 clientList.put(sensorAddr, subClientList);
			 StartDataCenter.setClientList(clientList);
		}else if(eventName.equals("ChangeSensor")){
			
			JSONArray argsArray = jsonObject.getJSONArray("args");
			String sensorAddr = argsArray.getString(0);
			log.debug("User "+client.getSessionID()+" change the sensor to "+sensorAddr);
			removeClient(client);
			 HashMap<String,ArrayList<IOClient>> clientList = StartDataCenter.getClientList();
			 ArrayList<IOClient> subClientList = clientList.get(sensorAddr);
			 if(subClientList!=null){
				 subClientList.add(client);
			 }else{
				 subClientList = new  ArrayList<IOClient>();
				 subClientList.add(client);
			 }
			 clientList.put(sensorAddr, subClientList);
			 StartDataCenter.setClientList(clientList);
		}
		
		
	}


	public void OnShutdown() {
		// TODO Auto-generated method stub
		log.debug("A connect shutdown....");
	}
	
	public void removeClient(IOClient client){
		HashMap<String,ArrayList<IOClient>> clientList = StartDataCenter.getClientList();
		Iterator iter = clientList.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			//Object key = entry.getKey();
			ArrayList<IOClient> val = (ArrayList<IOClient>) entry.getValue();
			if(val.remove(client)){
				break;
			}
		}
	}

}
