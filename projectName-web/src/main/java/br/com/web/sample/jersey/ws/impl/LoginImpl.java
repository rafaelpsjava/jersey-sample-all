package br.com.web.sample.jersey.ws.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import br.com.domain.sample.annotation.Compress;
import br.com.domain.sample.ws.ResponseWS;
import br.com.domain.sample.ws.UserCredentials;
import br.com.sample.util.JsonUtil;

@Path("/loginRS")
public class LoginImpl {

	@Context
	private HttpServletResponse servletResponse;

	@Context
	private HttpServletRequest servletRequest;

	@Context
	private HttpHeaders httpHeaders;

	@GET
	@Path("/loginStr")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseWS loginStr(@QueryParam("string") String string) {
		try {
			return new ResponseWS(JsonUtil.getInstance().toJson(new UserCredentials("user", "user")));
		} catch (Throwable e) {
			return new ResponseWS(null, 501);
		}
	}

	@GET
	@Compress
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/loginInfo")
	public ResponseWS loginInfo() {
		try {
			return new ResponseWS(JsonUtil.getInstance().toJson(new UserCredentials("user", "user")));
		} catch (Throwable e) {
			return new ResponseWS(null, 501);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public String login(UserCredentials userCredentials) throws Exception {
		return JsonUtil.getInstance().toJson(userCredentials);
	}
}