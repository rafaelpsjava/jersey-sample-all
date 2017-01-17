package br.com.web.sample.jersey.ws.api;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.domain.sample.annotation.Compress;
import br.com.exception.util.ServiceException;
import br.com.web.sample.jersey.ws.infra.di.parameter.ParameterAnnotation;

public interface AppService {

	@GET
	@Path("/getAppInfoUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAppInfoUsingGet() throws ServiceException;

	@GET
	@Path("/getAppInfoWithParameterUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	Response getAppInfoWithParameterUsingGet(@ParameterAnnotation final Object object) throws ServiceException;

	@POST
	@Path("/getAppInfoUsingPost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Compress
	public String getApssNamesUsingPost(String string) throws ServiceException;

	@GET
	@Path("/findAllUsingJpql")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Compress
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	String findAllUsingJpql() throws ServiceException;

}