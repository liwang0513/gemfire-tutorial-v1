package io.pivotal.listener;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEventListener;
import com.gemstone.gemfire.pdx.PdxInstance;

public class MyEventListener implements AsyncEventListener, Declarable {
	
	@Override
	public boolean processEvents(List<AsyncEvent> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close() {
	}

	@Override
	public void init(Properties arg0) {
	}

}