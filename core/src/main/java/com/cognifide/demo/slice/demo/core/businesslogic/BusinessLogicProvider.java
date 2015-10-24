package com.cognifide.demo.slice.demo.core.businesslogic;

import java.util.Random;

public class BusinessLogicProvider {

	private static final int MAX_NUMBER = 49;
	
	private String dynamicMessage;

	// Any custom logic can be hidden from your model in simple classes
	// In case of zero-argument constructors no annotations are needed
	public BusinessLogicProvider() {
		this.dynamicMessage = String.valueOf(new Random().nextInt(MAX_NUMBER));
	}

	public String getDynamicMessage() {
		return dynamicMessage;
	}
}
