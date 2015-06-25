package io.pivotal.app;

import java.util.Collection;
import java.util.Iterator;

import io.pivotal.domain.Customer;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.query.Query;
import com.gemstone.gemfire.cache.query.QueryService;
import com.gemstone.gemfire.cache.query.SelectResults;

public class QueryData {
	public static void main(String[] args) throws Exception {
		// Create cache
		Cache cache = new CacheFactory().set("log-level", "error")
				.set("cache-xml-file", "config/cache.xml").create();

		// Query the Data
		QueryService queryService = cache.getQueryService();
		Query query = queryService.newQuery("SELECT * FROM /Customer");
		Object result = query.execute();
		Collection<?> collection = ((SelectResults<?>) result).asList();
		Iterator<?> iter = collection.iterator();
		while (iter.hasNext()) {
			Customer cus = (Customer) iter.next();
			System.out.println(cus);
		}

		// Close cache
		cache.close();
	}
}
