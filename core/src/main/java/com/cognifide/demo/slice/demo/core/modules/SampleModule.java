package com.cognifide.demo.slice.demo.core.modules;

import org.ops4j.peaberry.Peaberry;

import com.adobe.granite.xss.XSSAPI;
import com.google.inject.Binder;
import com.google.inject.Module;

public class SampleModule implements Module {

	@Override
	public void configure(Binder binder) {
		//You can provide you custom Guice bindings here
		binder.bind(XSSAPI.class).toProvider(Peaberry.service(XSSAPI.class).single());
	}

}
