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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.apache.sling.settings.SlingSettingsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cognifide.demo.slice.demo.core.businesslogic.BusinessLogicProvider;

/**
 * Simple JUnit test verifying the HelloWorldModel
 */
@RunWith(MockitoJUnitRunner.class)
public class HelloWorldModelTest {

	//Class tested in isolation from injected dependencies, by providing their mocks
	@InjectMocks
	private HelloWorldModel tested;

	@Mock
	private SlingSettingsService settingsService;

	@Mock
	private BusinessLogicProvider logicProvider;

	@Test
	public void getMessageShouldSayHelloWorldAndReturnSlingInstanceIdAndResourceType() throws Exception {

		// given
		String slingId = "1234";
		when(settingsService.getSlingId()).thenReturn(slingId);

		// when
		String actual = tested.getMessage();

		// then
		String expected = "Hello World!\nThis is instance: " + slingId
				+ "\nResource type is: null\n";
		assertEquals(expected, actual);
	}

	@Test
	public void getDynamicMessageShouldProvideMessageFromBusinessLogicLayer() {

		// given
		when(logicProvider.getDynamicMessage()).thenReturn("Goodbye world");

		// when
		String actual = tested.getDynamicMessage();

		// then
		assertEquals("Goodbye world", actual);
	}

}
