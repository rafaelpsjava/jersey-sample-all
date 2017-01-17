package br.com.company.projectname.util.lookup;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

public class ServiceLocator {

	private static ServiceLocator serviceLocator;

	// java:global/app-name/
	// java:app/app-name/beanToLookup
	// java:module/beanToLookup
	// java:global/app-name/beanToLookup
	// java:app/app-name/beanToLookup
	// java:module/beanToLookup
	private ServiceLocator() {
	}

	public synchronized Object findEjb(Context context, String app, String beanName) {
		if (context == null) {
			throw new IllegalArgumentException("context cannot be null!");
		}
		app = app != null ? app : "";

		String[] beansToLookup = new String[] { String.format("java:global/%s/%s", app, beanName), String.format("java:module/%s/%s", app, beanName),
				String.format("java:app/%s/%s", app, beanName) };

		for (String beanToLookup : beansToLookup) {
			try {
				Object foundEjb = context.lookup(beanToLookup);
				if (foundEjb != null) {
					return foundEjb;
				}
			} catch (Throwable throwable) {
			}
		}

		return null;
	}

	public synchronized Object findEjb(String beanName) {
		Properties JNDI_PROPS = new Properties();
		JNDI_PROPS.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		// JNDI_PROPS.put(Context.INITIAL_CONTEXT_FACTORY,
		// "org.jnp.interfaces.NamingContextFactory");
		// JNDI_PROPS.put(Context.URL_PKG_PREFIXES,
		// "org.jboss.naming:org.jnp.interfaces");

		String[] beansToLookup = new String[] { String.format("java:module/%s", beanName), String.format("java:comp/env/ejb/%s", beanName) };
		for (String beanToLookup : beansToLookup) {
			try {
				Object foundEjb = new InitialContext(JNDI_PROPS).lookup(beanToLookup);
				if (foundEjb != null) {
					return foundEjb;
				}
			} catch (Throwable throwable) {
			}
		}

		return null;
	}

	public static ServiceLocator getInstance() {
		if (serviceLocator == null) {
			synchronized (ServiceLocator.class) {
				if (serviceLocator == null) {
					serviceLocator = new ServiceLocator();
				}
			}
		}
		return serviceLocator;
	}

	public static ServiceLocator getInstance(float fractionToWaitBeforeReturnInstance) throws InterruptedException {
		if (fractionToWaitBeforeReturnInstance > 0) {
			Thread.sleep((int) (1000 * fractionToWaitBeforeReturnInstance * 60));
		}

		return getInstance();
	}

	public static ServiceLocator getNewInstance() throws InterruptedException {

		return new ServiceLocator();
	}
}
