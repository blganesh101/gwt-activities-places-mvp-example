package com.dummylandapp.client.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;

import com.google.gwt.core.client.GWT;
import com.google.gwt.logging.client.RemoteLogHandlerBase;
import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.logging.shared.RemoteLoggingServiceAsync;

public class NetworkLoggerHandler extends RemoteLogHandlerBase {
	private RemoteLoggingServiceAsync service = (RemoteLoggingServiceAsync)GWT.create(RemoteLoggingService.class);

	private NetworkLoggerCallback callback = new NetworkLoggerCallback(wireLogger);
	
	@Override
	public void publish(LogRecord record) {
		if(record.getLevel() == Level.SEVERE)
			if (isLoggable(record))
				service.logOnServer(record, callback);
	}
}