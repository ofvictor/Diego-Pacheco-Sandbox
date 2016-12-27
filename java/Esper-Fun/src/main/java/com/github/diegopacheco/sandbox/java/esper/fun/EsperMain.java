package com.github.diegopacheco.sandbox.java.esper.fun;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.UpdateListener;

public class EsperMain {
	
	public static void main(String[] args) {

		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
		
		String expression = "select status,timestamp from com.github.diegopacheco.sandbox.java.esper.fun.StatusEvent.win:time(30 sec)";
		EPStatement statement = epService.getEPAdministrator().createEPL(expression);
		
		UpdateListener listener = new ConsoleListener();
		statement.addListener(listener);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					StatusEvent event = new StatusEvent("DOWN", getCurrentTimeStamp());
					epService.getEPRuntime().sendEvent(event);
					sleep(2000L);
				}
			}
		}).start();
	}
	
	public static String getCurrentTimeStamp() {
	    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}
	
	public static void sleep(Long timeInMs){
		try {
			Thread.sleep(timeInMs);
		} catch (InterruptedException e) {}
	}
	
}