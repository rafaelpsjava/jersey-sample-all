package br.com.web.sample.jersey.ws.infra.filter.request;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * RequestFilter
 * 
 * @author Rafael
 *
 */
public class RequestFilter implements ContainerRequestFilter {

	@Context
	private HttpServletResponse servletResponse;

	/**
	 * filter
	 * 
	 * @param requestContext
	 *            {@link ContainerRequestContext}
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("RequestFilter");

		String path = requestContext.getUriInfo().getAbsolutePath().getPath();

		if (path.equals("")) {
			// servletResponse.sendError(403);
		}

		@SuppressWarnings("unused")
		final SecurityContext securityContext = requestContext.getSecurityContext();
		// if (securityContext == null ||
		// !securityContext.isUserInRole("privileged")) {
		// requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User
		// cannot access the resource.").build());
		// }
	}
}
