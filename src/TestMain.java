import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.carton.common.net.ReceiveListener;
import org.carton.common.net.ServiceDiscoverUDPSocket;
import org.carton.common.service.GeneralServiceExecutePool;
import org.jdom.JDOMException;

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
//			dl.submitInfo("test "+i, "i", i+"");
//			Thread.sleep(10);
//			System.out.println(i);
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
		Scanner reader=new Scanner(System.in);
		DataCore core =new DataCore();
		core.addCollector(new TestCollecter());
		reader.nextLine();
		core.compileData();
	}
}
