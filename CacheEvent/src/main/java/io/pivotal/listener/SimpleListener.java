package io.pivotal.listener;

import java.util.Properties;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.util.CacheListenerAdapter;

public class SimpleListener<K,V> extends CacheListenerAdapter<K,V> implements Declarable {

	@Override
	public void init(Properties arg0) {
	}

	@Override
    public void afterCreate(EntryEvent<K,V> e) {
          String line = " Received afterCreate event for entry: " + e.getNewValue();
          
          System.out.println(line);
    }
}
