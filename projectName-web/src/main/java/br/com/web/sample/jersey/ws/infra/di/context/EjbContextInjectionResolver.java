package br.com.web.sample.jersey.ws.infra.di.context;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;

import javax.ws.rs.ext.Provider;

import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;

import br.com.sample.ejb.util.impl.ServiceLocator;


/**
 * EjbContextInjectionResolver
 * 
 * is necessary the ejb interface has an atribute like: public static final
 * String EJB_NAME = "...";
 * 
 * @author Rafael
 *
 */
@Provider
public class EjbContextInjectionResolver implements InjectionResolver<EjbLookup> {

	@Override
	public Object resolve(Injectee injectee, ServiceHandle<?> root) {
		final AnnotatedElement annotedElement = injectee.getParent();
		if (annotedElement != null) {
			EjbLookup ejbAnnotation = annotedElement.getAnnotation(EjbLookup.class);
			String ejbName = null;
			if (ejbAnnotation != null && ejbAnnotation.mappedName() != null && ejbAnnotation.name() != null && !ejbAnnotation.mappedName().trim().isEmpty()) {
				ejbName = ejbAnnotation.mappedName();
			} else {
				try {
					String interfaceClassName = annotedElement.toString().split(" ")[1];
					if (interfaceClassName != null) {
						Class<?> interfaceClass = Class.forName(interfaceClassName);
						if (interfaceClass != null) {
							Field declaredField = interfaceClass.getDeclaredField("EJB_NAME");
							if (declaredField != null) {
								ejbName = (String) declaredField.get(String.class);
							}
						}
					}
				} catch (Throwable throwable) {
				}
			}

			if (ejbName != null) {
				return ServiceLocator.getInstance().findEjb(ejbName);
			}
		}

		return null;
	}

	@Override
	public boolean isConstructorParameterIndicator() {
		return false;
	}

	@Override
	public boolean isMethodParameterIndicator() {
		return false;
	}

}