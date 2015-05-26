package io.pivotal.service;

import io.pivotal.domain.Customer;
import io.pivotal.util.CustomerFactory;

import com.gemstone.gemfire.cache.Region;

public class CustomerLoader {
	
	Region<Integer, Customer> customerRegion = null;
	CustomerFactory customerFactory = null;
	
	public CustomerLoader(Region<Integer, Customer> customerRegion) {
		this.customerRegion = customerRegion;
		this.customerFactory = new CustomerFactory();
	}
	
	public void bulkInsertion(int n) {

		for (int i = 0; i < n; i++) {
			Customer customer = customerFactory.generateCustomer(i+1);
			customerRegion.put(i + 1, customer);
		}
		
		System.out.println("Inserted " + n + " records.");
	}
	
	
}
