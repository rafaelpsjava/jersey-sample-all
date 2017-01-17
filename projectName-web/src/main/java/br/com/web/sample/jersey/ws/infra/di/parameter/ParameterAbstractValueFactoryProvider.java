package br.com.web.sample.jersey.ws.infra.di.parameter;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.model.Parameter;

/**
 * 
 * @author Rafael
 *
 */
@Singleton
@Provider
public class ParameterAbstractValueFactoryProvider extends AbstractValueFactoryProvider {

	@Inject
	public ParameterAbstractValueFactoryProvider(final MultivaluedParameterExtractorProvider extractorProvider, final ServiceLocator injector) {
		super(extractorProvider, injector);
	}

	@Override
	protected Factory<?> createValueFactory(Parameter parameter) {
		if (parameter != null) {
			final Class<?> classType = parameter.getRawType();
			if (parameter.getAnnotation(EJB.class) == null) {
				return null;
			}

			if (classType == null || (!classType.equals(ParameterAnnotation.class))) {
				return null;
			}
		} else {
			return null;
		}

		return new AbstractContainerRequestValueFactory<Object>() {
			@Override
			public Object provide() {
				return "Parameter dependency injection class ParameterAnnotation.class";
			}
		};
	}

}