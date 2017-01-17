package br.com.web.sample.jersey.ws.infra.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ThrowableHandler implements ExceptionMapper<Throwable> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ThrowableHandler.class);

	public ThrowableHandler() {
	}

	@Override
	public Response toResponse(Throwable t) {

		return Response.status(Response.Status.fromStatusCode(500)).type(MediaType.APPLICATION_JSON_TYPE).entity("someobject to json").build();
	}
}