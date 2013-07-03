package com.dummylandapp.client.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A callback handler for when the {@link NetworkLoggerHandler} makes a remote
 * logging service call. 
 * 
 * @author zharvey
 */
public class NetworkLoggerCallback implements AsyncCallback<String> {
	private Logger wireLogger;

	public NetworkLoggerCallback(Logger logger) {
		super();
		
		setWireLogger(logger);
	}
	
	@Override
	public void onFailure(Throwable throwable) {
		wireLogger.log(Level.SEVERE, "Remote logging failed: ", throwable);
	}

	@Override
	public void onSuccess(String result) {
		if(result != null)
			wireLogger.severe("Remote logging failed: " + result);
		else
			wireLogger.finest("Remote logging message acknowledged");
	}

	public Logger getWireLogger() {
		return wireLogger;
	}

	public void setWireLogger(Logger wireLogger) {
		this.wireLogger = wireLogger;
	}
}