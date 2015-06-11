package io.pivotal.app;

import io.pivotal.domain.Customer;

import java.util.Map;
import java.util.Set;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.partition.PartitionRegionHelper;
import com.gemstone.gemfire.distributed.DistributedMember;

public class VerifyDataLocations {

	private Cache cache = null;

	public VerifyDataLocations() {
		CacheFactory cf = new CacheFactory();
		cf.set("log-level", "error");
		cf.set("cache-xml-file", "config/cache.xml");
		cache = cf.create();
	}

	public void run() throws InterruptedException {
		Region<Integer, Customer> customers = cache.getRegion("Customer");
		System.out.println("Customer region size = " + customers.size());

		Set<Map.Entry<Integer, Customer>> entries = customers.entrySet();

		for (Map.Entry<Integer, Customer> entry : entries) {

			DistributedMember member = PartitionRegionHelper
					.getPrimaryMemberForKey(customers,
							(Integer) entry.getKey());
			System.out.println(String.format(
					"\"Primary Member [Name=%s - Key=%s - Value=%s]", member.getName(), entry.getKey(), entry.getValue()));
		}

		cache.close();
	}

	public static void main(String[] args) throws InterruptedException {
		VerifyDataLocations test = new VerifyDataLocations();
		test.run();
		System.exit(1);
	}

}
