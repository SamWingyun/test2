package com.atguigu.ws.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyPasswordCallbackClient implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		 WSPasswordCallback wsPasswordCallback = (WSPasswordCallback) callbacks[0];
		 wsPasswordCallback.setIdentifier("root");
		 wsPasswordCallback.setPassword("aoyama");
	}

}
