package br.com.web.sample.jersey.ws.infra.di.parameter;

import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;

/**
 * 
 * @author Rafael
 *
 */
@Provider
public class ParameterParamInjectionResolver extends ParamInjectionResolver<ParameterAnnotation> {

	// @Inject
	// @Named(InjectionResolver.SYSTEM_RESOLVER_NAME)
	// private InjectionResolver<Inject> systemResolver;

	public ParameterParamInjectionResolver() {
		super(ParameterAbstractValueFactoryProvider.class);
	}

}