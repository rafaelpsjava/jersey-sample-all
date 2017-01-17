package br.com.web.sample.jersey.ws.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import br.com.exception.util.ServiceException;
import br.com.web.sample.jersey.ws.api.AppService;
import br.com.web.sample.jersey.ws.infra.di.parameter.ParameterAnnotation;

/**
 * AppServiceImpl
 * 
 * @author Rafael
 *
 */
@Path("/appService")
public class AppServiceImpl implements AppService {

	@Override
	public Response getAppInfoUsingGet() {
		return Response.status(200).entity(this.getClass().getName() + "getAppInfoUsingGet").build();
	}

	@Override
	public Response getAppInfoWithParameterUsingGet(@ParameterAnnotation final Object object) throws ServiceException {
		return Response.status(200).entity(this.getClass().getName() + "getAppInfoWithParameterUsingGet").build();
	}

	@Override
	public String getApssNamesUsingPost(String string) {
		return this.getClass().getName() + "getApssNamesUsingPost() " + string;
	}

	@Override
	public String findAllUsingJpql() throws ServiceException {
		throw new RuntimeException("not implemented");
	}

}