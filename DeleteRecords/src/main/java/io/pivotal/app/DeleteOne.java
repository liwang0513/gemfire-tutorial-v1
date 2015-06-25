package io.pivotal.app;

import io.pivotal.domain.Customer;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;

public class DeleteOne {
	public static void main(String[] args) {
		// Create cache
		Cache cache = new CacheFactory().set("log-level", "error")
				.set("cache-xml-file", "config/cache.xml").create();

		// Get the customer region
		Region<Integer, Customer> customers = cache.getRegion("Customer");

		// Delete some records
		customers.remove(1001);
		System.out.println("Customer 1001 was deleted");
		
		// Close cache
		cache.close();
	}
}
