package br.com.web.sample.jersey.ws.infra.di;

import java.util.Set;

import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.spi.ComponentProvider;

@Provider
public class ComponentProviderImpl implements ComponentProvider {

	private ServiceLocator serviceLocator;

	@Override
	public void initialize(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	@Override
	@SuppressWarnings("unused")
	public boolean bind(Class<?> component, Set<Class<?>> providerContracts) {
		Class<?> clazz = null;
		if (clazz != null && component == clazz) {
			final DynamicConfigurationService dynamicConfigService = serviceLocator.getService(DynamicConfigurationService.class);
			final DynamicConfiguration dynamicConfiguration = dynamicConfigService.createDynamicConfiguration();

			// BindingBuilderFactory.addBinding(BindingBuilderFactory.newFactoryBinder(PerSessionFactory.class).to(PerSessionResource.class),
			// dynamicConfiguration);

			dynamicConfiguration.commit();

			return true;
		}

		return false;
	}

	@Override
	public void done() {
	}
}