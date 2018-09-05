import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.carton.common.net.GeneralUDPSocket;
import org.carton.common.net.ServiceDiscoverUDPSocket;
import org.carton.common.service.GeneralServiceExecutePool;

import gui.DisplayUnit;

public class DataMonitor extends GeneralServiceExecutePool {
	String name;
	boolean isGPSReady;
	ArrayList<ValueProfile> profileList;
	ArrayList<DisplayUnit> displayList;
	int dataPort;
	int discoverPort;
	ConcurrentLinkedQueue<HashMap<String,ArrayList<String>>> dataQueue = new ConcurrentLinkedQueue<HashMap<String,ArrayList<String>>>();
	GeneralUDPSocket gus;
	ServiceDiscoverUDPSocket sdus;
}
