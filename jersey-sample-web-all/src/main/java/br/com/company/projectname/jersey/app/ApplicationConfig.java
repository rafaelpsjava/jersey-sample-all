package br.com.company.projectname.jersey.app;

import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.WriterInterceptor;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.reflections.Reflections;

import br.com.company.projectname.jersey.ws.infra.di.context.Ejb;
import br.com.company.projectname.jersey.ws.infra.di.context.EjbContextInjectionResolver;

/**
 * ApplicationConfig
 * 
 * @author Rafael
 *
 */
public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		property("ResourceConfig.class.name", "ApplicationConfig");

		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(EjbContextInjectionResolver.class).to(new TypeLiteral<InjectionResolver<Ejb>>() {
				}).in(Singleton.class);
			}
		});

		// register(new AbstractBinder() {
		// @Override
		// protected void configure() {
		// bind(ParameterAbstractValueFactoryProvider.class).to(ValueFactoryProvider.class).in(Singleton.class);
		// bind(ParameterParamInjectionResolver.class).to(new
		// TypeLiteral<InjectionResolver<ParameterAnnotation>>() {
		// }).in(Singleton.class);
		// }
		// });

		// uncompress-gzip-http-response-using-jersey-client-api-java
		// compress every request
		// EncodingFilter.enableFor(this, GZipEncoder.class);
		String packageString = ApplicationConfig.class.getName().replace(".app." + ApplicationConfig.class.getSimpleName(), "");
		Reflections reflections = new Reflections(packageString);

		registerJaxRsServices(reflections);
		registerContatinerRequestFilters(reflections);
		registerContainerResponseFilters(reflections);
		registerWriterInterceptors(reflections);
		registerReaderInterceptors(reflections);

		register(JacksonJsonProvider.class);
	}

	/**
	 * registerReaderInterceptors
	 * 
	 * register all classes that implements {@link ReaderInterceptor}
	 * 
	 * @param reflections
	 */
	private void registerReaderInterceptors(Reflections reflections) {
		Set<Class<? extends ReaderInterceptor>> readerInterceptors = reflections.getSubTypesOf(ReaderInterceptor.class);
		if (readerInterceptors != null) {
			for (Class<?> readerInterceptor : readerInterceptors) {
				register(readerInterceptor);
			}
		}
	}

	/**
	 * registerWriterInterceptors
	 * 
	 * register all classes that implements {@link WriterInterceptor}
	 * 
	 * @param reflections
	 */
	private void registerWriterInterceptors(Reflections reflections) {
		Set<Class<? extends WriterInterceptor>> writerInterceptors = reflections.getSubTypesOf(WriterInterceptor.class);
		if (writerInterceptors != null) {
			for (Class<?> writerInterceptor : writerInterceptors) {
				register(writerInterceptor);
			}
		}
	}

	/**
	 * registerContainerResponseFilters
	 * 
	 * register all classes that implements {@link ContainerResponseFilter}
	 * 
	 * @param reflections
	 */
	private void registerContainerResponseFilters(Reflections reflections) {
		Set<Class<? extends ContainerResponseFilter>> containerResponseFilters = reflections.getSubTypesOf(ContainerResponseFilter.class);
		if (containerResponseFilters != null) {
			for (Class<?> containerResponseFilter : containerResponseFilters) {
				register(containerResponseFilter);
			}
		}
	}

	/**
	 * registerContatinerRequestFilters
	 * 
	 * register all classes that implements {@link ContainerRequestFilter}
	 * 
	 * @param reflections
	 */
	private void registerContatinerRequestFilters(Reflections reflections) {
		Set<Class<? extends ContainerRequestFilter>> containerRequestFilters = reflections.getSubTypesOf(ContainerRequestFilter.class);
		if (containerRequestFilters != null) {
			for (Class<?> containerRequestFilter : containerRequestFilters) {
				register(containerRequestFilter);
			}
		}
	}

	/**
	 * registerJaxRsServices
	 * 
	 * register all classes that has {@link Path}
	 * 
	 * @param reflections
	 */
	private void registerJaxRsServices(Reflections reflections) {
		Set<Class<?>> webservices = reflections.getTypesAnnotatedWith(Path.class);
		if (webservices != null) {
			for (Class<?> webservice : webservices) {
				register(webservice);
			}
		}
	}

}