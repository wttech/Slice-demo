package com.cognifide.demo.slice.demo.core.businesslogic;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessLogicProviderTest {

	public BusinessLogicProvider tested;
	
	@Before
	public void setUp(){
		this.tested = new BusinessLogicProvider();
	}
	
	@Test
	public void dynamicMessageShouldReturnNumberSmallerOrEequals49() {
		//when
		String actual = tested.getDynamicMessage();
		
		//then
		Assert.assertTrue(Integer.valueOf(actual)<=49);
	}

}
