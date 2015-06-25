package io.pivotal.function;

import io.pivotal.domain.Customer;

import java.util.Properties;

import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.execute.FunctionAdapter;
import com.gemstone.gemfire.cache.execute.FunctionContext;

public class SizeFunction extends FunctionAdapter implements Declarable {

	private static final long serialVersionUID = 1L;
	public static final String ID = "size-function";
	private Cache cache;

	public SizeFunction() {
		this.cache = CacheFactory.getAnyInstance();
	}

	@Override
	public void execute(FunctionContext context) {
		String regionName = (String) context.getArguments();
		Region<Integer, Customer> region = this.cache.getRegion(regionName);
		System.out.println("Getting size of region " + region.getFullPath());
		context.getResultSender().lastResult(region.size());
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void init(Properties arg0) {
	}

}
