package debug;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.carton.common.net.ReceiveListener;
import org.carton.common.net.ServiceDiscoverUDPSocket;
import org.carton.common.service.GeneralServiceExecutePool;
import org.carton.common.util.ConfigAccesser;
import org.jdom.Element;
import org.jdom.JDOMException;

import gui.DisplayUnit;

public class TestMain {
	static int count=0;
	public static void main(String[] args) throws InterruptedException, FileNotFoundException, JDOMException, IOException {
//		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		for(int i=0;i<100;i++) {
//			String time = format0.format(new Date().getTime());
//			Thread.sleep(1000);
//			System.out.println(String.format("INFO %-20s WHEN %-19s, THAT %10s = %-10s","temputrue on line and ready",time,"test one",12312.12+""));
//		}
//		DataLogger dl=new DataLogger();
//		GeneralServiceExecutePool gsep=new GeneralServiceExecutePool();
//		
//		gsep.lunchUnit(dl);
//		for(int i=0;i<100;i++) {
//			dl.submitWarning("test "+i, "i", i+"");
//			Thread.sleep(10);
//		}
//		
//		gsep.closePool();
//		gsep.closePool();System.out.println("finish");
//		Timer t=new Timer();
//		t.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("task1");
//			}
//			
//		}, 10,1000);
//		t.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("task2");count++;
//				if(count==10) {
//					cancel();
//				}
//			}
//			
//		}, 100,100);
//		ServiceDiscoverUDPSocket dsus=new ServiceDiscoverUDPSocket(25225);
////		ServiceDiscoverUDPSocket dsus1=new ServiceDiscoverUDPSocket(25225);
////		dsus.addService("Test1", true);
//		dsus.discoverService("Test1", false, 25225, new ReceiveListener() {
//
//			@Override
//			public boolean verify(byte[] data, InetAddress ip, int port) {
//				// TODO Auto-generated method stub
//				return true;
//			}
//
//			@Override
//			public void process(byte[] data, InetAddress ip, int port) {
//				System.out.println(ip.getHostAddress());
//				
//			}
//
//		});
//		Scanner reader=new Scanner(System.in);
//		DataCore core =new DataCore();
//		core.addCollector(new TestCollecter());
//		reader.nextLine();
//		core.compileData();
//		JFrame jf=new JFrame();
//		jf.setSize(200, 200);
//		jf.setVisible(true);
//		DisplayUnit du1=new DisplayUnit("T#ROOM0");
//		DisplayUnit du2=new DisplayUnit("T#ROOM1");
//		DisplayUnit du3=new DisplayUnit("T#ROOM2");
//		DisplayUnit du4=new DisplayUnit("T#ROOM3");
//		JPanel jp=new JPanel(new GridLayout(4,1));
//		jp.add(du1);
//		jp.add(du2);
//		jp.add(du3);
//		jp.add(du4);
//		jf.getContentPane().add(jp);
//		HashMap<String,String> data=new HashMap<String,String>();
//		data.put("DataTerm", "T#ROOM");
//		data.put("Value", "123.2");
//		data.put("RawData", "123.3");
//		du1.updateInfo(data);
//		Random rd=new Random();
//		while(true){
//
//			int order=rd.nextInt(4);
//			data.put("DataTerm", "T#ROOM"+order);
//			Thread.sleep(100);
//			data.put("Value", "123%");
//			du1.updateInfo(data);
//			du2.updateInfo(data);
//			du3.updateInfo(data);
//			du4.updateInfo(data);
//			order=rd.nextInt(4);
//			data.put("DataTerm", "T#ROOM"+order);
//			Thread.sleep(100);
//			data.put("Value", "FAIL");
//			du1.updateInfo(data);
//			du2.updateInfo(data);
//			du3.updateInfo(data);
//			du4.updateInfo(data);
//			order=rd.nextInt(4);
//			data.put("DataTerm", "T#ROOM"+order);
//			Thread.sleep(100);
//			data.put("Value", "PASS");
//			du1.updateInfo(data);
//			du2.updateInfo(data);
//			du3.updateInfo(data);
//			du4.updateInfo(data);
//			order=rd.nextInt(4);
//			data.put("DataTerm", "T#ROOM"+order);
//			Thread.sleep(100);
//			data.put("Value", "123.2");
//			du1.updateInfo(data);
//			du2.updateInfo(data);
//			du3.updateInfo(data);
//			du4.updateInfo(data);
//		}
//		new MonitorCreater();
//=======
////		core.compileData();
//		ValueProfile vp=new ValueProfile("temp", "temp1", "VALUE","C", 1, 1, 1, 0.3);
//		Element e=vp.compileProfileToElement();
//		
//		System.out.println(e.getAttributes());
//		ValueProfile vp2=ValueProfile.complieProfileFromNode(e);
//		System.out.println(vp2.compileProfileToString());
//		File localPath=new File("").getAbsoluteFile();
//		File generalConfigFile=new File(localPath.getAbsolutePath()+File.separator+"setting2.xml");
//		ConfigAccesser ca=new ConfigAccesser(generalConfigFile);
//		HashMap<String,String> dataDeliverList=new HashMap<String,String>();
//		HashMap<String,String> generalSetting=new HashMap<String,String>();
//		ArrayList<String> listData=new ArrayList<String>();
//		/****/
//		generalSetting.put("DiscoverPort", "17851");
//		generalSetting.put("DataPort", "17852");
//		listData.add("ElectronicsEngineer");
//		listData.add("RocketEngineer");
//		listData.add("PowerEngineer");
//		dataDeliverList.put("temp", "ElectronicsEngineer,RocketEngineer");
//		dataDeliverList.put("voltage", "ElectronicsEngineer,PowerEngineer");
//		/***/
//		ca.saveListToXml("Monitors", listData);
//		ca.saveMapToXml("DataDeliverList", dataDeliverList);
//		ca.saveMapToXml("GeneralSetting", generalSetting);
//		ca.saveConfiguration();
//		DataCore core =new DataCore();
//		core.saveConfig();
//>>>>>>> 129acd13d706ab148d8e73492942af52173e159c
	}
	
}
