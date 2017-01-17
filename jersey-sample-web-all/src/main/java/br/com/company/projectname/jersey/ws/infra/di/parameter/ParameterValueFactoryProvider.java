package br.com.company.projectname.jersey.ws.infra.di.parameter;

import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

public class ParameterValueFactoryProvider implements ValueFactoryProvider {

	// @Inject

	@Override
	public Factory<?> getValueFactory(Parameter parameter) {
		if (parameter.getAnnotation(ParameterAnnotation.class) != null) {

			return null;
		}

		return null;
	}

	@Override
	public PriorityType getPriority() {
		return Priority.NORMAL;
	}

}