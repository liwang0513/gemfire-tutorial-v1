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
				.set("cache-xml-file", "config/cache.xml").create();

		System.out.println("Peer " + (cache.getMembers().size()+1) + " started.");

		// Wait to stop
		System.out.println("Hit ENTER to stop this server");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		// Close cache
		cache.close();

	}
}
