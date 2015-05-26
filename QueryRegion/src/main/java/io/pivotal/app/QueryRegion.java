package io.pivotal.app;

import io.pivotal.domain.Customer;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.query.FunctionDomainException;
import com.gemstone.gemfire.cache.query.NameResolutionException;
import com.gemstone.gemfire.cache.query.QueryInvocationTargetException;
import com.gemstone.gemfire.cache.query.QueryService;
import com.gemstone.gemfire.cache.query.SelectResults;
import com.gemstone.gemfire.cache.query.TypeMismatchException;

public class QueryRegion {
	public static void main(String[] args) throws FunctionDomainException,
			TypeMismatchException, NameResolutionException,
			QueryInvocationTargetException {
		// Create cache
		Cache cache = new CacheFactory().set("log-level", "error")
				.set("cache-xml-file", "config/cache.xml").create();

		// Get the customer region
		Region<Integer, Customer> customers = cache.getRegion("Customer");
		System.out.println("Got the Customer Region: " + customers);

		// Load the customers into region
		Customer cust1 = new Customer(new Integer(1001), "Jaden", "Booth");
		customers.put(new Integer(1001), cust1);
		System.out.println("Inserted a customer: " + cust1);

		Customer cust2 = new Customer(new Integer(1002), "Lydia", "Owen");
		customers.put(new Integer(1002), cust2);
		System.out.println("Inserted a customer: " + cust2);

		Customer cust3 = new Customer(new Integer(1003), "Beverly", "Neal");
		customers.put(new Integer(1003), cust3);
		System.out.println("Inserted a customer: " + cust3);

		// Do a query
		QueryService qs = cache.getQueryService();
		String oql = "SELECT * FROM /Customer";
		SelectResults<?> results = (SelectResults<?>) qs.newQuery(oql)
				.execute();
		System.out.println("Customers in the region: " + results.asList());

		// Close cache
		cache.close();
	}
}
