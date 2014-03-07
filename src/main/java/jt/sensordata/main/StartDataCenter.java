package jt.sensordata.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.yongboy.socketio.MainServer;
import com.yongboy.socketio.server.transport.IOClient;

import jt.sensordata.comread.ComRead;
import jt.sensordata.comread.TestReader;
import jt.sensordata.server.RTTempDataHandler;
import jt.sensordata.server.SendCommandHandler;
import jt.sensordata.server.TopDataHandler;

public class StartDataCenter {
	
	/**
	 * clientList 为获取传感器温度数据的客户端列表
	 * 用hashmap来区分订阅不同传感器设备的客户端
	 */
	public  static HashMap<String,ArrayList<IOClient>> clientList = new HashMap<String,ArrayList<IOClient>>();
	
	/**
	 * @return the clientList
	 */
	public static synchronized HashMap<String, ArrayList<IOClient>> getClientList() {
		return clientList;
	}

	/**
	 * @param clientList the clientList to set
	 */
	public static synchronized void setClientList(
			HashMap<String, ArrayList<IOClient>> clientList) {
		StartDataCenter.clientList = clientList;
	}
	
	/**
	 * cytClientList为获取Zigbee拓扑网络结构图的客户端列表
	 * 
	 */
	static ArrayList<IOClient> topClientList = new ArrayList<IOClient>();
	


	/**
	 * @return the topClientList
	 */
	public static synchronized ArrayList<IOClient> getTopClientList() {
		return topClientList;
	}

	/**
	 * @param topClientList the topClientList to set
	 */
	public static synchronized void setTopClientList(ArrayList<IOClient> topClientList) {
		StartDataCenter.topClientList = topClientList;
	}

	public static int RTTIOPort = 9001;
	public static int TopIOPort = 9002;
	public static int CommandPort = 9003;
	public static  ComRead comRead; //该ComRead由Spring注入
	public static String comNumber = "";
	
	
	public  void setComRead(ComRead comRead) {
		this.comRead = comRead;
	}

	public  void startAll(String [] args){
		//启动串口读写服务
		startComReader();
		//TestReader tr = new TestReader();
		//tr.start();
		//启动Socket服务为终端提供实时传感器温度数据
		startCommandSender();
		startRTTSocket();
		startTopSocket();
	}
	
	private static void startCommandSender() {
		// TODO Auto-generated method stub
		MainServer CSServer = new MainServer(new SendCommandHandler(), CommandPort);
		CSServer.start();
	}

	public  void startComReader(){
		//cr = new ComRead();
		comRead.setComNumber(comNumber);
		
		comRead.startRead(comRead);
	}
	
	/**
	 * 用于推送最新采集到的传感器数据
	 */
	public static void startRTTSocket(){
		//RTT = Real Time Temperature
		MainServer RTTServer = new MainServer(new RTTempDataHandler(),RTTIOPort);
		
		RTTServer.start();
	}
	
	/**
	 * 该Socket接口用于推送最新的拓扑结构图
	 */
	public static void startTopSocket(){
		MainServer CYTServer = new MainServer(new TopDataHandler(),TopIOPort);
		
		CYTServer.start();
	}
}
