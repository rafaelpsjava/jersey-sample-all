package br.com.company.projectname.jersey.ws.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.company.projectname.jersey.ws.api.AppService;
import br.com.company.projectname.jersey.ws.infra.di.parameter.ParameterAnnotation;
import br.com.company.projectname.util.exception.ServiceException;
import br.com.domain.sample.annotation.Compress;

/**
 * 
 * @author Rafael
 *
 */
@Path("/appService")
public class AppServiceImpl implements AppService {

	@GET
	@Path("/getAppInfoUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAppInfoUsingGet() {
		return Response.status(200).entity("getApplicationInfoUsingGet").build();
	}

	@GET
	@Path("/getAppInfoWithParameterUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getAppInfoWithParameterUsingGet(@ParameterAnnotation final Object object) throws ServiceException {
		return Response.status(200).entity("getAppInfoWithParameterUsingGet").build();
	}

	@POST
	@Path("/getAppInfoUsingPost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Compress
	@Override
	public String getAppInfoUsingPost(String string) {
		return "getApplicationInfoUsingPost() " + string;
	}

	@Override
	public String findAllUsingJpql() throws ServiceException {
		return null;
	}

}