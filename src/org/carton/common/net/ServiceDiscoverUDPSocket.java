package org.carton.common.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

public class ServiceDiscoverUDPSocket {
	boolean isAlive=true;
	Thread receiver=new Thread() {
		public void run() {
			while(isAlive) {
				byte[] buffer=new byte[2048];
				DatagramPacket packet=new DatagramPacket(buffer,buffer.length);
				try {
					ds.receive(packet);
					byte tempBuff[]=packet.getData();
					
					HashMap<String, Object> map=new HashMap<String, Object>();
					map.put("data", tempBuff);
					map.put("address", packet.getAddress());
					map.put("port", packet.getPort());
					for(ReceiveListener l:rlList) {
						l.action(map);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	};

	int port;
	DatagramSocket ds;
	Timer timer=new Timer();
	ArrayList<ReceiveListener> rlList=new ArrayList<ReceiveListener>();
	public ServiceDiscoverUDPSocket(DatagramSocket ds) {
		this.ds=ds;
		receiver.start();
	}
	public ServiceDiscoverUDPSocket(int port) throws SocketException {
		ds=new DatagramSocket(port);
		receiver.start();
	}
	public void addService(String service,boolean isHost) {
		ReceiveListener rl=new ReceiveListener() {

			@Override
			public boolean verify(byte[] data, InetAddress ip, int port) {
				// TODO Auto-generated method stub
				String[] info=(new String(data)).trim().split("@");
				if(info.length<2)return false;
				if(info[0].equals(service)) {
					return true;
				}
				return false;
			}

			@Override
			public void process(byte[] data, InetAddress ip, int port) {
				// TODO Auto-generated method stub
				String[] info=(new String(data)).trim().split("@");
				System.out.println(ds.getLocalPort()+" process");
				if(info[1].equals("host")&&!isHost) {
					try {
						final String sendingMessege=service+"@"+"peer";
//						DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName(info[3]), ds.getLocalPort());
						DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),ip, port);
						ds.send(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(info[1].equals("peer")&&isHost) {
					try {
						final String sendingMessege=service+"@"+"host";
//						DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName(info[3]), ds.getLocalPort());
						DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),ip, port);
						ds.send(packet);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		rlList.add(rl);
	}
	public void discoverService(String service,boolean isHost,int port,ReceiveListener l) throws UnknownHostException {
		String messege=service+"@";
		if(isHost)
			messege+="host";
		else
			messege+="peer";
		final String sendingMessege=messege;
		CounterTask ct=new CounterTask(10) {

			@Override
			void action() {
				// TODO Auto-generated method stub
				try {
//					DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName("255.255.255.255"), ds.getLocalPort());
					DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByAddress(new byte[] { (byte) 255, (byte) 255, (byte) 255, (byte) 255 }), port);
//					System.out.println(packet);
					ds.send(packet);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		timer.schedule(ct, 0,100);
		rlList.add(l);
	}
}




//@Override
//public boolean verify(byte[] data) {
//	// TODO Auto-generated method stub
//	String[] info=(new String(data)).trim().split("@");
//	if(info.length<3)return false;
//	if(info[0].equals(service)) {
//		return true;
//	}
//	return false;
//}
//
//@Override
//public void process(byte[] data) {
//	// TODO Auto-generated method stub
//	String[] info=(new String(data)).trim().split("@");
//	if(info[1].equals("host")&&!isHost) {
//		try {
//			final String sendingMessege=service+"@"+"peer"+"@"+InetAddress.getLocalHost();
////			DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName(info[3]), ds.getLocalPort());
//			DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName(info[3]), port);
//			ds.send(packet);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}else if(info[1].equals("peer")&&isHost) {
//		try {
//			final String sendingMessege=service+"@"+"host"+"@"+InetAddress.getLocalHost();
////			DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName(info[3]), ds.getLocalPort());
//			DatagramPacket packet=new DatagramPacket(sendingMessege.getBytes(), sendingMessege.length(),InetAddress.getByName(info[3]), port);
//			ds.send(packet);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
