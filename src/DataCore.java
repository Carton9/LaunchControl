import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.carton.common.net.GeneralUDPSocket;
import org.carton.common.net.ReceiveListener;
import org.carton.common.net.ServiceDiscoverUDPSocket;
import org.carton.common.service.GeneralServiceExecutePool;
import org.carton.common.util.ConfigAccesser;
import org.jdom.JDOMException;

public class DataCore extends GeneralServiceExecutePool{
	ConfigAccesser dataFormatConfig;
	ConfigAccesser generalConfig;
	HashMap<String,HashMap<String,String>> dataItems;
	HashMap<String,String> generalSetting;
	ArrayList<DataCollector> collectors=new ArrayList<DataCollector>();
	private ConcurrentLinkedQueue<HashMap<String,ArrayList<String>>> dataQueue = new ConcurrentLinkedQueue<HashMap<String,ArrayList<String>>>();
	GeneralUDPSocket gus;
	HashSet<InetAddress> ipList=new HashSet<InetAddress>();
	public DataCore() throws FileNotFoundException, JDOMException, IOException {
		File localPath=new File("").getAbsoluteFile();
		File dataFormatFile=new File(localPath.getAbsolutePath()+File.separator+"data_format.info");
		File generalConfigFile=new File(localPath.getAbsolutePath()+File.separator+"general_config.info");
		dataFormatConfig=new ConfigAccesser(dataFormatFile);
		generalConfig=new ConfigAccesser(generalConfigFile);
		loadDataItems();
//		gus=new GeneralUDPSocket(Integer.parseInt(generalSetting.get("DataPort")));
//		ServiceDiscoverUDPSocket sdus=new ServiceDiscoverUDPSocket(Integer.parseInt(generalSetting.get("DiscoverPort")));
//		sdus.addService(generalSetting.get("Service"), true,new ReceiveListener() {
//
//			@Override
//			public boolean verify(byte[] data, InetAddress ip, int port) {
//				// TODO Auto-generated method stub
//				return true;
//			}
//
//			@Override
//			public void process(byte[] data, InetAddress ip, int port) {
//				// TODO Auto-generated method stub
//				ipList.add(ip);
//			}
//			
//		});
		
		gus=new GeneralUDPSocket(Integer.parseInt("17852"));
		ServiceDiscoverUDPSocket sdus=new ServiceDiscoverUDPSocket(Integer.parseInt("17851"));
		sdus.addService("ts", true,new ReceiveListener() {
			
						@Override
						public boolean verify(byte[] data, InetAddress ip, int port) {
							// TODO Auto-generated method stub
							return true;
						}
			
						@Override
						public void process(byte[] data, InetAddress ip, int port) {
							// TODO Auto-generated method stub
							System.out.println(""+ip+":"+port);
							ipList.add(ip);
						}
						
					});
		this.lunchUnit(gus);
	}
	public void addCollector(DataCollector dc) {
		collectors.add(dc);
	}
	public void loadDataItems() {
		ArrayList<String> items=new ArrayList<String>();
		dataFormatConfig.loadListFromElement("DataItems", items);
		dataItems=new HashMap<String,HashMap<String,String>>();
		for(String i:items) {
			HashMap<String,String> dataFormat=new HashMap<String,String>();
			dataFormatConfig.loadMapFromElement(i, dataFormat);
			dataItems.put(i, dataFormat);
		}
		///////////////////////////////////////////
		generalSetting=new HashMap<String,String>();
		generalConfig.loadMapFromElement("GeneralSetting", generalSetting);
	}
	// DATA FORMAT
	// <DATA TYPE>,<DATA 1>,<DATA 2>,<DATA 3>
	//int - 123
	//double - 12.12
	//Point - 21.3#23.123
	public void compileData() {
		HashMap<String,ArrayList<String>> dataFrame=new HashMap<String,ArrayList<String>>();
		for(DataCollector dc:collectors) {
			String[] data=dc.getRocketData();
			String dataType=data[0];
			data=Arrays.copyOfRange(data, 1, data.length);
			HashMap<String,String> dataFormat=dataItems.get(dataType);
//			boolean isPoint=dataFormat.get("ValueType").equals("Point");
			//TestCase
			boolean isPoint=false;
			ArrayList<String> result=new ArrayList<String>();
			for(int i=0;i<data.length;i++) {
				String dataPoint=data[i];
				if(isPoint) {
					result.add(dataPoint.replaceAll("#", ","));
				}else {
					result.add(dataPoint);
				}
			}
			dataFrame.put(dataType, result);
		}
		dataQueue.add(dataFrame);
		//TestCase
		try {
			sendData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendData() throws IOException {
		if(dataQueue.isEmpty())return;
		HashMap<String,ArrayList<String>> dataFrame=dataQueue.remove();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(dataFrame);
		oos.flush();
		byte[] dataPackage=bos.toByteArray();
		oos.close();
		bos.close();
		System.out.println(dataFrame);
		for(InetAddress i:ipList) {
			gus.send(dataPackage, Integer.parseInt(generalSetting.get("DataPort")), i);
		}
		
	}
	
}
