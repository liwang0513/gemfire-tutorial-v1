package io.pivotal.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;

public class StartPeer {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		// Create cache
		Cache cache = new CacheFactory()
				.set("log-level", "error")
				.set("name", args[0])
				.set("cache-xml-file", "config/cache.xml").create();

		System.out.println(args[0] + " started.");

		// Waiting for other process
		Thread.sleep(10000);
		
		// Close cache
		cache.close();
	}
}
