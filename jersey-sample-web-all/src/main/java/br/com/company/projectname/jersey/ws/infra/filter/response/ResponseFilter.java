package br.com.company.projectname.jersey.ws.infra.filter.response;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

/**
 * ResponseFilter
 * 
 * @author Rafael
 *
 */
public class ResponseFilter implements ContainerResponseFilter {

	/**
	 * filter
	 * 
	 * @param requestContext
	 *            {@link ContainerRequestContext}
	 * @param responseContext
	 *            {@link ContainerResponseContext}
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		// responseContext.getHeaders().add("X-Powered-By", "Jersey :-)");
		System.out.println(ResponseFilter.class.getName());
	}
}