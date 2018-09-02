import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.carton.common.service.GeneralServiceExecutePool;

public class TestMain {
	public static void main(String[] args) throws InterruptedException {
//		SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		
//		for(int i=0;i<100;i++) {
//			String time = format0.format(new Date().getTime());
//			Thread.sleep(1000);
//			System.out.println(String.format("INFO %-20s WHEN %-19s, THAT %10s = %-10s","temputrue on line and ready",time,"test one",12312.12+""));
//		}
		DataLogger dl=new DataLogger();
		GeneralServiceExecutePool gsep=new GeneralServiceExecutePool();
		
		gsep.lunchUnit(dl);
		for(int i=0;i<100;i++) {
			dl.submitInfo("test "+i, "i", i+"");
			Thread.sleep(10);
			System.out.println(i);
		}
		System.out.println("finish");
		gsep.closePool();
	}
}
