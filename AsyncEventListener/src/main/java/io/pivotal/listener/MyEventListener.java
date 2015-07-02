package io.pivotal.listener;

import io.pivotal.domain.Customer;

import java.util.List;
import java.util.Properties;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEvent;
import com.gemstone.gemfire.cache.asyncqueue.AsyncEventListener;
import com.gemstone.gemfire.pdx.PdxInstance;

public class MyEventListener implements AsyncEventListener, Declarable {

	@Override
	public boolean processEvents(@SuppressWarnings("rawtypes") List<AsyncEvent> entries) {
		for (@SuppressWarnings("rawtypes") AsyncEvent ge : entries) {

			PdxInstance pdxInstance = (PdxInstance) ge.getDeserializedValue();
			Customer cus = (Customer) pdxInstance.getObject();

			System.out.println(cus.toString());
		}

		return true;
	}

	@Override
	public void close() {
	}

	@Override
	public void init(Properties arg0) {
	}

}