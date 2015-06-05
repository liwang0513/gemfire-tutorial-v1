package io.pivotal.app;

import junit.framework.TestCase;

/**
 * Unit tests for PreLoader
 */
public class PreLoaderTest extends TestCase
{

    public void testPreLoader()
    {
    	PreLoader preLoader = new PreLoader();
    	preLoader.load("Customer");
        assertTrue( true );
    }
}
