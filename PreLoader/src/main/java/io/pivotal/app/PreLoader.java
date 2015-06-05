package io.pivotal.app;

import io.pivotal.domain.Customer;
import io.pivotal.service.CustomerLoader;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;

public class PreLoader {
	
	public static void main(String[] args) {
		PreLoader preloader = new PreLoader();
		preloader.load("Customer");
	}
	
	
	public void load(String regionType) {
		// Create cache
		Cache cache = new CacheFactory()
			.set("name", "PreLoader")
			.set("log-level", "error")
			.set("cache-xml-file", "config/cache.xml").create();

		// Get the customer region
		Region<Integer, Customer> region = cache.getRegion(regionType);
		System.out.println("Got the " + regionType + " Region: " + region.getFullPath());

		// Bulk load the customers into region
		CustomerLoader customerLoader = new CustomerLoader(region);
		customerLoader.preLoad();

		System.out.println(region.size() + " records are successfully loaded");

		cache.close();
	}
}
