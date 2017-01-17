package br.com.web.sample.jersey.ws.infra.filter.compression;

import java.lang.reflect.Method;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * CompressionDynamicBinding
 * 
 * @author Rafael
 *
 */
public class CompressionDynamicBinding implements DynamicFeature {

	/**
	 * configure
	 * 
	 * @param resourceInfo
	 *            {@link ResourceInfo}
	 * @param context
	 *            {@link FeatureContext}
	 */
	@Override
	public void configure(ResourceInfo resourceInfo, FeatureContext context) {
		@SuppressWarnings("unused")
		Class<?> resourceClass = resourceInfo.getResourceClass();
		@SuppressWarnings("unused")
		Method resourceMethod = resourceInfo.getResourceMethod();
		// TODO check if the requested resource is in some list ...
		context.register(GZIPWriterInterceptor.class);
	}
}