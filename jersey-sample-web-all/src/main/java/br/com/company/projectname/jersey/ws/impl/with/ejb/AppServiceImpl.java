package br.com.company.projectname.jersey.ws.impl.with.ejb;

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

import br.com.company.projectname.ejb.business.api.AppBusiness;
import br.com.company.projectname.jersey.ws.api.AppService;
import br.com.company.projectname.jersey.ws.infra.di.context.Ejb;
import br.com.company.projectname.jersey.ws.infra.di.parameter.ParameterAnnotation;
import br.com.company.projectname.util.exception.BusinessException;
import br.com.company.projectname.util.exception.ServiceException;
import br.com.domain.sample.App;
import br.com.domain.sample.annotation.Compress;

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

	@Ejb
	private AppBusiness appBusinessEjb;

	@GET
	@Path("/getAppInfoUsingGet")
	@Produces(MediaType.APPLICATION_JSON)
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@Override
	public Response getAppInfoUsingGet() throws ServiceException {
		try {
			StringBuilder apps = getAppListAsStringBuilder(appBusinessEjb.findAll());

			return Response.status(200)
					.entity(String.format(this.getClass().getName() + ".getAppInfoUsingGet() %s", apps)).build();
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

			return Response.status(200)
					.entity(String.format(this.getClass().getName() + ".getAppInfoUsingGet() %s", apps)).build();
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
	public String getAppInfoUsingPost(String string) throws ServiceException {
		try {
			StringBuilder apps = getAppListAsStringBuilder(appBusinessEjb.findAll());
			return String.format(this.getClass().getName() + ".getAppInfoUsingPost() %s ", apps);
		} catch (BusinessException e) {
			throw new ServiceException(e);
		}
	}

	@POST
	@Path("/findAllUsingJpql")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Compress
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public String findAllUsingJpql() throws ServiceException {
		try {
			StringBuilder apps = getAppListAsStringBuilder(appBusinessEjb.findAll());
			return String.format(this.getClass().getName() + ".findAllUsingJpql() %s ", apps);
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