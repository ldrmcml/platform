/**
 * 
 */
package jt.sensordata.server;

import java.util.ArrayList;
import java.util.HashMap;

import jt.sensordata.comread.ComRead;
import jt.sensordata.main.StartDataCenter;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yongboy.socketio.server.IOHandlerAbs;
import com.yongboy.socketio.server.transport.IOClient;

/**
 * @author Administartor
 *
 */
public class TopDataHandler extends IOHandlerAbs {
	
	Logger log = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see com.yongboy.socketio.server.IOHandler#OnConnect(com.yongboy.socketio.server.transport.IOClient)
	 */
	
	public void OnConnect(IOClient client) {
		// TODO Auto-generated method stub
		log.debug("A user connected :: "+client.getSessionID());
	}

	/* (non-Javadoc)
	 * @see com.yongboy.socketio.server.IOHandler#OnDisconnect(com.yongboy.socketio.server.transport.IOClient)
	 */
	
	public void OnDisconnect(IOClient client) {
		// TODO Auto-generated method stub
		log.debug("A user disconnected :: " + client.getSessionID());
	}

	/* (non-Javadoc)
	 * @see com.yongboy.socketio.server.IOHandler#OnMessage(com.yongboy.socketio.server.transport.IOClient, java.lang.String)
	 */
	
	public void OnMessage(IOClient client, String oriMessage) {
		// TODO Auto-generated method stub
		String jsonString = oriMessage.substring(oriMessage.indexOf('{'));
		jsonString = jsonString.replaceAll("\\\\", "");
		JSONObject jsonObject = JSON.parseObject(jsonString);
		String eventName = jsonObject.get("name").toString();
		log.debug("get a message:"+oriMessage+" from "+client.getSessionID());
		if (eventName.equals("GetTop")) {
			 JSONArray argsArray = jsonObject.getJSONArray("args");
             String commandName = argsArray.getString(0);
             if(commandName.equals("Now")){
            	 //立即发送一次拓扑结构
            	 sendTopToAClient(client);
             }
			 //将请求某Zigbee拓扑结构的client加入列表当中用于统一发送，以设备短地址为标识符
             ArrayList<IOClient> topClientList = StartDataCenter.getTopClientList();
             if(topClientList.contains(client)){
            	 
             }else{
            	 topClientList.add(client);
             }
             StartDataCenter.setTopClientList(topClientList);
			
		}
	}

	/* (non-Javadoc)
	 * @see com.yongboy.socketio.server.IOHandler#OnShutdown()
	 */
	
	public void OnShutdown() {
		// TODO Auto-generated method stub
		log.debug("A connect shutdown....");
	}
	
	/**
	 * 发送最新的拓扑结构图给初次连接的client
	 */
	public void sendTopToAClient(IOClient client){
		HashMap sendMessage = new HashMap();
    	//String content = "5:::{\"name\":\"my\",\"args\":[{\"my\":\"fffff\"}]}";
		//client.send(content);
    	sendMessage.put("name","refresh");
    	HashMap<String,String> top =  new HashMap<String,String>();
    	HashMap<String,HashMap<String,String>> networkInfo = ComRead.getNetworkInfo();
    	sendMessage.put("args", networkInfo);
    	System.out.println(JSON.toJSONString(sendMessage));
    	client.send("5:::"+JSON.toJSONString(sendMessage));
	}

}
