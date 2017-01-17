package br.com.company.projectname.jersey.ws.api;

import javax.ws.rs.core.Response;

import br.com.company.projectname.jersey.ws.infra.di.parameter.ParameterAnnotation;
import br.com.company.projectname.util.exception.ServiceException;

public interface AppService {

	public Response getAppInfoUsingGet() throws ServiceException;

	Response getAppInfoWithParameterUsingGet(@ParameterAnnotation final Object object) throws ServiceException;

	public String getAppInfoUsingPost(String string) throws ServiceException;

	String findAllUsingJpql() throws ServiceException;

}