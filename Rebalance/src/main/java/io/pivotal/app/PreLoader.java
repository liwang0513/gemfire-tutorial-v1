package io.pivotal.app;

import io.pivotal.domain.Customer;
import io.pivotal.service.CustomerLoader;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;

public class PreLoader {
	public static void main(String[] args) {
		// Create cache
		Cache cache = new CacheFactory()
				.set("log-level", "error")
				.set("cache-xml-file", "config/cache.xml").create();

		// Get the customer region
		Region<Integer, Customer> customers = cache.getRegion("Customer");
		System.out.println("Got the Customer Region: " + customers);

		// Load 20 customers into region
		CustomerLoader customerLoader = new CustomerLoader(customers);
		customerLoader.bulkInsertion(20);

		// Close cache
		cache.close();

	}
}
