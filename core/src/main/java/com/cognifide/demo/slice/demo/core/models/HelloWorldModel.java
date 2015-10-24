/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.cognifide.demo.slice.demo.core.models;

import javax.inject.Inject;

import org.apache.sling.settings.SlingSettingsService;

import com.cognifide.demo.slice.demo.core.businesslogic.BusinessLogicProvider;
import com.cognifide.slice.api.annotation.OsgiService;
import com.cognifide.slice.mapper.annotation.JcrProperty;
import com.cognifide.slice.mapper.annotation.SliceResource;

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
		String returnMessage = "Hello World!\n";
		returnMessage += "This is instance: " + settings.getSlingId() + "\n";
		returnMessage += "Resource type is: " + resourceType + "\n";

		return returnMessage;
	}

	// Message delivered from injected business logic provider
	public String getDynamicMessage() {
		return logicProvider.getDynamicMessage();
	}

}
