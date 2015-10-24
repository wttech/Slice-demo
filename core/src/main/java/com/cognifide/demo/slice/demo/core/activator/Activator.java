package com.cognifide.demo.slice.demo.core.activator;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.cognifide.demo.slice.demo.core.modules.SampleModule;
import com.cognifide.slice.api.injector.InjectorRunner;
import com.cognifide.slice.commons.SliceModulesFactory;
import com.cognifide.slice.core.internal.module.OsgiToGuiceAutoBindModule;
import com.cognifide.slice.cq.module.CQModulesFactory;
import com.google.inject.Module;

public class Activator implements BundleActivator {

	// Your unique injector name, doesn't have to be related to bundle name
	public static final String INJECTOR_NAME = "slicedemo";

	// Filter for bundle names, can be more/less strict depending on how many
	// bundles should be scanned while looking for Models
	private static final String BUNDLE_NAME_FILTER = "slice-demo-app";

	// Root package, where the scanning for models will start from, it
	// should cover all your model classes
	private static final String BASE_PACKAGE = "com.cognifide.demo";

	@Override
	public void start(final BundleContext bundleContext) throws BundleException {

		final InjectorRunner injectorRunner = new InjectorRunner(bundleContext,
				INJECTOR_NAME, BUNDLE_NAME_FILTER, BASE_PACKAGE);

		final List<Module> sliceModules = SliceModulesFactory
				.createModules(bundleContext);

		// Slice AEM addon module, enables utilizing AEM specific objects out of the box
		final List<Module> cqModules = CQModulesFactory.createModules();
		// Your application specific modules
		final List<Module> customModules = createCustomModules(bundleContext);

		// Slice generic modules for Sling
		injectorRunner.installModules(sliceModules);
		// Slice AEM Addon specific modules
		injectorRunner.installModules(cqModules);
		// Your own modules with custom bindings/configurations
		injectorRunner.installModules(customModules);

		injectorRunner.start();
	}

	private List<Module> createCustomModules(BundleContext bundleContext) {
		List<Module> applicationModules = new ArrayList<Module>();
		//Sample application specific module
		applicationModules.add(new SampleModule());
		//OSGi Module enabling direct OSGi Services Injection
		applicationModules.add(new OsgiToGuiceAutoBindModule(bundleContext,
				BUNDLE_NAME_FILTER, BASE_PACKAGE));
		return applicationModules;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub

	}
}