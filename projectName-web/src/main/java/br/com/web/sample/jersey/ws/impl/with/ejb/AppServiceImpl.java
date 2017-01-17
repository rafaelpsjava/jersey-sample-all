package br.com.web.sample.jersey.ws.impl.with.ejb;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import br.com.domain.sample.App;
import br.com.domain.sample.annotation.Compress;
import br.com.ejb.sample.business.AppBusiness;
import br.com.exception.util.BusinessException;
import br.com.exception.util.ServiceException;
import br.com.sample.util.JsonUtil;
import br.com.web.sample.jersey.ws.api.AppService;
import br.com.web.sample.jersey.ws.infra.di.context.EjbLookup;
import br.com.web.sample.jersey.ws.infra.di.parameter.ParameterAnnotation;

/**
 * 
 * @author Rafael
 *
 */
@Path("/appUsingEjbService")
@Stateless
@Provider
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AppServiceImpl implements AppService {

	@Context
	private UriInfo uriInfo;

	@EjbLookup
	private AppBusiness appBusinessEjb;

	@GET
	@Path("/getAppInfoUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Response getAppInfoUsingGet() throws ServiceException {
		try {
			StringBuilder apps = getAppListAsStringBuilder(appBusinessEjb.findAll());
			return Response.status(200).entity(this.getClass().getName() + ".getAppInfoUsingGet()" + apps).build();
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@GET
	@Path("/getAppInfoWithParameterUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Response getAppInfoWithParameterUsingGet(@ParameterAnnotation final Object object) throws ServiceException {
		try {
			StringBuilder apps = getAppListAsStringBuilder(appBusinessEjb.findAll());

			return Response.status(200).entity(this.getClass().getName() + ".getAppInfoUsingGet()" + apps).build();
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@POST
	@Path("/getAppInfoUsingPost")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Compress
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public String getApssNamesUsingPost(String string) throws ServiceException {
		try {
			StringBuilder apps = getAppListAsStringBuilder(appBusinessEjb.findAll());
			return String.format(this.getClass().getName() + ".getAppInfoUsingPost() %s ", apps);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@GET
	@Path("/findAllUsingJpql")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Compress
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public String findAllUsingJpql() throws ServiceException {
		try {
			String appsJson = "";
			try {
				appsJson = JsonUtil.getInstance().toJson(appBusinessEjb.findByName("jersey-sample"));
			} catch (IOException e) {
			}

			return String.format(this.getClass().getName() + ".findAllUsingJpql() %s ", appsJson);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	private StringBuilder getAppListAsStringBuilder(List<App> appsList) {
		StringBuilder apps = new StringBuilder();
		if (appsList != null) {
			for (int i = 0; i < apps.length(); i++) {
				apps.append(appsList.get(i));
				if (i != appsList.size() - 1) {
					apps.append(", ");
				}
			}
		}
		return apps;
	}

}