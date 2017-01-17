package br.com.web.sample.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ServletContextListenerImpl
 * 
 * @author Rafael
 *
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

	/**
	 * contextInitialized
	 * 
	 * @param e
	 *            {@link ServletContextEvent}
	 */
	public void contextInitialized(ServletContextEvent e) {
		System.out.println("Context Initialized");

	}

	/**
	 * contextDestroyed
	 * 
	 * @param e
	 *            {@link ServletContextEvent}
	 */
	public void contextDestroyed(ServletContextEvent e) {
		System.out.println("Context Destroyed");

	}

}