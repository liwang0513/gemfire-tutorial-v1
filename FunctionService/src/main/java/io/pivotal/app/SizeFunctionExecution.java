package io.pivotal.app;

import io.pivotal.function.SizeFunction;

import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.client.ClientCacheFactory;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;

public class SizeFunctionExecution {

	public static void main(String[] args) {
		// create cache
		ClientCache cache = new ClientCacheFactory().set("log-level", "error")
				.set("cache-xml-file", "config/client-cache.xml").create();

		// execute size function
		Execution execution = FunctionService.onServer(cache).withArgs("/Customer");
		ResultCollector<?, ?> collector = execution.execute(SizeFunction.ID);

		System.out.println("The size of Customer Region is: " + collector.getResult());
	}
}
