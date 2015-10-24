package com.cognifide.demo.slice.demo.core.models;


import org.apache.sling.settings.SlingSettingsService;

import com.cognifide.demo.slice.demo.core.businesslogic.BusinessLogicProvider;
import com.cognifide.slice.api.annotation.OsgiService;
import com.cognifide.slice.mapper.annotation.JcrProperty;
import com.cognifide.slice.mapper.annotation.SliceResource;
import com.google.inject.Inject;

@SliceResource
public class HelloWorldModel {

	// Properties mapped on field level, we don't need to unit test it, since
	// this functionality was tested within Slice framework already
	@JcrProperty
	private String text;

	@JcrProperty("sling:resourceType")
	private String resourceType;

	private SlingSettingsService settings;

	private BusinessLogicProvider logicProvider;

	// Injection through constructor to make our class easily testable by enabling mocking
	@Inject
	public HelloWorldModel(@OsgiService SlingSettingsService settings,
			BusinessLogicProvider logicProvider) {
		this.settings = settings;
		this.logicProvider = logicProvider;
	}

	// Simple getter for automatically mapped property
	public String getText() {
		return text;
	}

	public String getMessage() {
		String returnMessage = "Hello World!\n"
				+ "This is instance: " + settings.getSlingId() + "\n"
				+ "Resource type is: " + resourceType + "\n";

		return returnMessage;
	}

	// Message delivered from injected business logic provider
	public String getDynamicMessage() {
		return logicProvider.getDynamicMessage();
	}

}
