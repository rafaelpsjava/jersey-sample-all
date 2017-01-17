package br.com.ejb.sample.test.impl;

import java.util.Arrays;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import br.com.ejb.sample.business.AppBusiness;
import br.com.ejb.sample.test.abstractions.AbstractEJBContainer;
import br.com.sample.ejb.util.impl.ServiceLocator;
import br.com.sample.ejb.util.pojo.AppDefinition;
import br.com.sample.ejb.util.pojo.AppType;
import br.com.sample.ejb.util.pojo.EjbContainer;
import br.com.sample.ejb.util.pojo.Enviroment;
import br.com.sample.ejb.util.pojo.JEEServer;
import br.com.sample.util.MethodToInvoke;
import br.com.sample.util.ReflectionsUtil;

/**
 * ApplicationBusinessTest
 * 
 * @author Rafael
 *
 */
public class ApplicationBusinessTest extends AbstractEJBContainer {
	private static AppDefinition appDefinition = new AppDefinition();
	{
		appDefinition.setApplicationName("ejb-sample");
		appDefinition.setEnviroment(Enviroment.DEVELOPING);

		EjbContainer ejbContainer = new EjbContainer();
		ejbContainer.setJeeServer(JEEServer.JBOSS);
		ejbContainer.setPath("C:/Users/Rafael/Documents/DEVELOPER/SERVERS/jboss-as-7.1.1.Final/");
		appDefinition.setEjbServer(ejbContainer);
		appDefinition.setAppType(AppType.JAR);
	}

	public ApplicationBusinessTest() {
		super(appDefinition);
	}

	// @Test
	public void getAppInfo() {
		try {
			Context context = ejbContainer.getContext();
			Object appBusiness = ServiceLocator.getInstance(0.5f).findEjb(context,
					properties.getProperty(EJBContainer.APP_NAME),
					AppBusiness.EJB_NAME + "!" + AppBusiness.class.getName());

			List<MethodToInvoke> methodsToInvoke = Arrays.asList(new MethodToInvoke("getAppInfo"));
			ReflectionsUtil.getInstance().invoke(appBusiness, methodsToInvoke);
			for (MethodToInvoke methodInvocation : methodsToInvoke) {
				String name = methodInvocation.getName();
				Object invocationResult = methodInvocation.getInvocationResult();

				System.out.println(name + " : " + (invocationResult != null ? invocationResult : ""));
			}
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

}