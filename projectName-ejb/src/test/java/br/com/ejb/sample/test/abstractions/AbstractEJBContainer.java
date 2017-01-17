package br.com.ejb.sample.test.abstractions;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;

import br.com.sample.ejb.util.pojo.AppDefinition;
import br.com.sample.ejb.util.pojo.JEEServer;

/**
 * AbstractEJBContainer
 * 
 * Just implement the ejb test(s) if there is no web service in project the
 * client acess direct the ejb(s)
 * 
 * @author Rafael
 *
 */
public abstract class AbstractEJBContainer {
	protected EJBContainer ejbContainer;
	protected Properties properties = new Properties();

	private AppDefinition appDefinition;

	public AbstractEJBContainer(AppDefinition appDefinition) {
		this.setEjbServer(appDefinition);
	}

	@Before
	public void before() {

		if (JEEServer.JBOSS.equals(appDefinition.getEjbServer().getJeeServer())) {
			try {
				System.setProperty("jboss.home", appDefinition.getEjbServer().getPath());
				System.setProperty("org.jboss.as.embedded.ejb3.BARREN", "true");
				System.setProperty("org.jboss.as.ejb3.EMBEDDED", "true");

				properties.setProperty(EJBContainer.APP_NAME, appDefinition.getApplicationName());
				properties.put(EJBContainer.PROVIDER, "org.jboss.as.embedded.ejb3.JBossStandaloneEJBContainer");

				String jeeContainerModules = String.format("target/%s.%s", appDefinition.getApplicationName(),
						appDefinition.getAppType().name().toLowerCase());
				properties.setProperty(EJBContainer.MODULES, jeeContainerModules); // target/classes

				properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
				properties.setProperty(javax.naming.Context.PROVIDER_URL, "localhost");

				// JBossStandaloneEJBContainerProvider,
				// JBossStandaloneEJBContainer
				ejbContainer = EJBContainer.createEJBContainer(properties);
			} catch (Throwable throwable) {
				System.out.println(throwable.getMessage());
			}
		} else {

			throw new RuntimeException(getEjbServer() + " 'EJBContainer' not implemented yet!");
		}
	}

	@After
	public void after() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

	public AppDefinition getEjbServer() {
		return appDefinition;
	}

	public void setEjbServer(AppDefinition appDefinition) {
		this.appDefinition = appDefinition;
	}
}
