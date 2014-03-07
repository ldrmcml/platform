package jt.sensordata.comread;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import jt.sensordata.main.StartDataCenter;

import org.apache.log4j.Logger;
import org.omg.CORBA.Object;







import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.jtang.model.Sensor;
import com.jtang.service.ISensorService;
import com.jtang.servicethread.CheckZigbee;
import com.jtang.servicethread.StoreTemp;
import com.jtang.servicethread.UpdateSensorWorkTime;
import com.yongboy.socketio.server.transport.IOClient;

/**
 * @author Administartor
 *
 */
public class ComRead implements SerialPortEventListener {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
	private InputStream inputStream;
    private SerialPort sPort = null;
    private String temp = ""; //用于存储从传感器接收到的数据
    private String comNumber = "COM3";
    private Logger logger = Logger.getLogger(this.getClass());
    
    //保存了Zigbee网络的拓扑结构，结果为 短地址-----（节点类型，父节点短地址）
    public static HashMap<String,HashMap<String,String>> networkInfo = new HashMap<String,HashMap<String,String>>();
    //Zigbee网络中长地址对短地址的映射关系
    public static HashMap<String,String> extToShort = new HashMap<String,String>();
    
    //保存着每隔传感器采集到的数据平均值
    
    public static HashMap<String,Float> averageTemp = new HashMap<String,Float>();
    
    public ISensorService sensorService; //由Spring注入
    
    public CheckZigbee checkZigbee;//由Spring注入
    
    public StoreTemp storeTemp;  //由Spring注入，定期存储传感器数据
    
    public UpdateSensorWorkTime updateSensorWorkTime;
    
    
    
    
    
    /**
	 * @return the storeTemp
	 */
	public StoreTemp getStoreTemp() {
		return storeTemp;
	}

	/**
	 * @param storeTemp the storeTemp to set
	 */
	public void setStoreTemp(StoreTemp storeTemp) {
		this.storeTemp = storeTemp;
	}

	/**
	 * @return the averageTemp
	 */
	public static synchronized HashMap<String, Float> getAverageTemp() {
		return averageTemp;
	}

	/**
	 * @param averageTemp the averageTemp to set
	 */
	public static synchronized void setAverageTemp(
			HashMap<String, Float> averageTemp) {
		ComRead.averageTemp = averageTemp;
	}

	/**
	 * @return the updateSensorWorkTime
	 */
	public UpdateSensorWorkTime getUpdateSensorWorkTime() {
		return updateSensorWorkTime;
	}

	/**
	 * @param updateSensorWorkTime the updateSensorWorkTime to set
	 */
	public void setUpdateSensorWorkTime(UpdateSensorWorkTime updateSensorWorkTime) {
		this.updateSensorWorkTime = updateSensorWorkTime;
	}

	public CheckZigbee getCheckZigbee() {
		return checkZigbee;
	}

	public void setCheckZigbee(CheckZigbee checkZigbee) {
		this.checkZigbee = checkZigbee;
	}

	public static synchronized HashMap<String, String> getExtToShort() {
		return extToShort;
	}

	public static synchronized void  setExtToShort(HashMap<String, String> extToShort) {
		ComRead.extToShort = extToShort;
	}

	public ISensorService getSensorService() {
		return sensorService;
	}

	public void setSensorService(ISensorService sensorService) {
		this.sensorService = sensorService;
	}

	/**
	 * @return the sPort
	 */
	public synchronized SerialPort getsPort() {
		return sPort;
	}

	/**
	 * @return the networkInfo
	 */
	public static synchronized HashMap<String, HashMap<String, String>> getNetworkInfo() {
		return networkInfo;
	}

	/**
	 * @param networkInfo the networkInfo to set
	 */
	public static synchronized void setNetworkInfo(
			HashMap<String, HashMap<String, String>> networkInfo) {
		ComRead.networkInfo = networkInfo;
	}

	public String getComNumber() {
		return comNumber;
	}

	public void setComNumber(String comNumber) {
		this.comNumber = comNumber;
	}

	public void startRead(ComRead cr){
  
		logger.debug("初始化串口"+comNumber);
		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier(comNumber);
			
			sPort = (SerialPort)portId.open("shipment",1000); 
			sPort.setSerialPortParams(115200,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
			
			inputStream = sPort.getInputStream();
			
			sPort.notifyOnDataAvailable(true); 
			sPort.notifyOnBreakInterrupt(true);  
			sPort.addEventListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.debug("初始化串口"+comNumber);
			e.printStackTrace();
		}
		
		new Thread(checkZigbee).start(); //启动检查过期的传感器,超时时间设置为5分钟,具体修改到CheckZigbee类中进行修改
		new Thread(updateSensorWorkTime).start(); //每隔一个小时更新传感器的工作时间
		new Thread(storeTemp).start();  //每隔一段时间就存储所有的温度数据
    }
    
    /**
     * �ж�һ���ַ��Ƿ��Ǹ���������
     */
    public static boolean isDouble(String str) {    
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");    
        return pattern.matcher(str).matches();    
    } 
    
	
	public void serialEvent(SerialPortEvent event) {
		// TODO Auto-generated method stub
		switch(event.getEventType()) {
	        case SerialPortEvent.BI:
	        case SerialPortEvent.OE:
	        case SerialPortEvent.FE:
	        case SerialPortEvent.PE:
	        case SerialPortEvent.CD:
	        case SerialPortEvent.CTS:
	        case SerialPortEvent.DSR:
	        case SerialPortEvent.RI:
	        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
	            break;
	        case SerialPortEvent.DATA_AVAILABLE:
	          
	        	BufferedInputStream bi = new BufferedInputStream(inputStream);
				try {
					while(true){
						int receivedData = bi.read();
						if(receivedData == -1)
							break;
						char c = (char)receivedData;
						if(c != '\n'){
							temp += c;
						}else{
							String tempData[] = temp.split(",");
							if(tempData.length == 5 && isDouble(tempData[0])){
								String temperature = tempData[0];
								String deviceClass = tempData[1];
								String shortAddr = tempData[2];
								String extAddr = bin2hex(tempData[3]);
								String parentShortAddr = tempData[4];
								
								for(int k=extAddr.length();k<20;k++){
									extAddr = "0"+extAddr;
								}
								
								System.out.println(temperature+","+deviceClass+","+shortAddr+","+extAddr+","+parentShortAddr);
								
								/*
								 * 计算传感器平均温度
								 */
								HashMap<String,Float> averageTemp = ComRead.getAverageTemp();
								if(averageTemp.get(extAddr) == null || averageTemp.get(extAddr) == 0){
									averageTemp.put(extAddr, Float.parseFloat(temperature));
								}else{
									averageTemp.put(extAddr, Float.parseFloat(df.format((averageTemp.get(extAddr) + Float.parseFloat(temperature))/2)));
								}
								ComRead.setAverageTemp(averageTemp);
								/**
								 * End
								 */
								
								int flag = 0;//标识是否需要更新sensor表
								
								if(extToShort.get(extAddr) == null){
									//第一次接收到该传感器
									flag = 1;
								}else if(!extToShort.get(extAddr).equals(shortAddr)){
									//传感器存在，但是地址发送了变化
									flag = 1;			
								}
								extToShort.put(extAddr, shortAddr);
								
								
								String nowTime = formatter.format(new Date());
								//发送温度数据给所有订阅者
								sendTempToAllClient(shortAddr,nowTime,temperature);
								
								if(deviceClass!=null && shortAddr!=null && parentShortAddr!=null){
									HashMap<String,String> cyto = networkInfo.get(shortAddr);
									if(cyto!=null){
										if(cyto.get("pointType").toString().equals(deviceClass)
												&&cyto.get("parentIp").toString().equals(parentShortAddr)){
											cyto.put("newTime", nowTime);
											networkInfo.put(shortAddr, cyto);
											//拓扑结构未发生变化,不需通知客户端
										}else{
											//拓扑结构发生了改变,需要更新
											cyto.put("pointType", deviceClass);
											cyto.put("parentIp", parentShortAddr);
											cyto.put("newTime", nowTime);
											networkInfo.put(shortAddr, cyto);
											sendTopToAllClient();
											flag = 1;
										}
									}else{
										//新增加了一个传感器或者传感器的短地址发生了变法
										cyto = new HashMap<String,String>();
										cyto.put("pointType", deviceClass);
										cyto.put("parentIp", parentShortAddr);
										cyto.put("newTime", nowTime);
										networkInfo.put(shortAddr, cyto);
										sendTopToAllClient();
										flag = 1;
									}
									
								}
								
								if(1 == flag){
									//需要插入到数据库或者更新到数据库
									Sensor s = new Sensor();
						        	s.setExtAddr(extAddr);
						        	s.setShortAddr(shortAddr);
						        	s.setNodeTypes(Integer.parseInt(deviceClass));
						        	s.setWorkStatus(1);
						        	s.setFatherNode(parentShortAddr);
						        	s.setPosition("UnKnow");
						        	s.setCreator("Sensor NetWork");
						        	s.setCreateTime(formatter.format(new Date()));
						        	s.setMender("Sensor NetWork");
						        	s.setMendTime(formatter.format(new Date()));
						        	s.setWorkTime(0);
						        	refreshToDatabase(s);
								}
																
								temp = ""; //一条记录取得过后清空buffer
						  }else{
							  temp = "";
						  }
						}
					}
					
					/*len = bi.available();
					byte[] readBuffer = new byte[len];
					while (inputStream.available() > 0) {
	                    int numBytes = inputStream.read(readBuffer);
	                }
					System.out.println(new String(readBuffer));*/
				} catch (IOException e) {
					e.printStackTrace();
				}       	
	       }
	}
	 /**
     * 字符串转换成十六进制值
     * @param bin String 我们看到的要转换成十六进制的字符串
     * @return 
     */
    public  String bin2hex(String bin) {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }
    
    /**
     * 发送sensorAddr指向传感器的数据到订阅该传感器的客户端
     * @param sensorAddr
     * @param time
     * @param temperature
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
    
    /**
	 * 发送Zigbee网络拓扑结构到订阅的客户端
	 */
	public static void sendTopToAllClient(){
		
		ArrayList<IOClient> clientList = StartDataCenter.getTopClientList();
		if(clientList!=null){
			HashMap sendMessage = new HashMap();
	    	//String content = "5:::{\"name\":\"my\",\"args\":[{\"my\":\"fffff\"}]}";
			//client.send(content);
	    	sendMessage.put("name","refresh");
	    	HashMap<String,String> top =  new HashMap<String,String>();
	    	HashMap<String,HashMap<String,String>> networkInfo = ComRead.getNetworkInfo();
	    	sendMessage.put("args", networkInfo);
	    	
	    	for(int i=0;i<clientList.size();i++){
	    		IOClient temp = clientList.get(i);
	    		temp.send("5:::"+JSON.toJSONString(sendMessage));
	    	}
		}

	}
	
	/**
	 * 开辟一个新线程将数据插入或更新到数据库
	 * 如果是更新，则不更新的字段有：Position，creator，createTime,workTime
	 * 如果是插入，则mender和mendTime字段插入“unknown”
	 * @param sensor
	 */
	public void refreshToDatabase(Sensor sensor){
		if(sensorService.isSensorExists(sensor.getExtAddr())){
			sensorService.updateASensor(sensor); 
		}else{
			sensor.setMender("unKnown");
			sensor.setMendTime("unKnow");
			sensorService.addASensor(sensor);
		}
	}

}
