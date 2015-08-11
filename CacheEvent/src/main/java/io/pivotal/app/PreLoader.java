package io.pivotal.app;

import io.pivotal.domain.Customer;
import io.pivotal.service.CustomerLoader;

import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;

public class PreLoader {
	public static void main(String[] args) throws Exception {
		// Create cache
		ClientCache cache = new ClientCacheFactory()
				.set("log-level", "error")
				.set("cache-xml-file", "config/client-cache.xml").create();

		// Get the customer region
		Region<Integer, Customer> customers = cache.getRegion("Customer");
		System.out.println("Got the Customer Region: " + customers);

		// Load 20 customers into region
		CustomerLoader customerLoader = new CustomerLoader(customers);
		customerLoader.bulkInsertion(20);
		System.out.println(customers.size() + " records are successfully loaded");

		// Close cache
		cache.close();
	}
}
