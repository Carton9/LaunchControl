package org.carton.common.net;

import java.util.Map;
import java.util.Queue;

import org.carton.common.service.ServiceListener;
/**
 * 
 * @author mike
 *
 */
public abstract class ReceiveListener implements ServiceListener {

	public ReceiveListener() {
		
	}

	@Override
	public final void action(Map<String, Object> result) {
		// TODO Auto-generated method stub
		Queue<byte[]> recevieQ=(Queue<byte[]>) result.get("recevie");
		if(verify(recevieQ.peek())) {
			process(recevieQ.poll());
		}
	}
	public abstract boolean verify(byte[] data);
	public abstract void process(byte[] data);
}
