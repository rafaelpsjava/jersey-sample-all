package br.com.company.projectname.ws;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import br.com.company.projectname.ws.abstractions.RestFulAbstractClient;
import br.com.domain.sample.ws.ResponseWS;
import br.com.domain.sample.ws.UserCredentials;

/**
 * LoginTest
 * 
 * @author Rafael
 *
 */
public class LoginTest extends RestFulAbstractClient {

	@Test
	public void login() {
		try {
			UserCredentials userCredentials = new UserCredentials();
			userCredentials.setUsername("user");
			userCredentials.setPassword("user");

			String login = getWebTarget().path("rest").path("loginRS").path("login").request()
					.accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity(userCredentials, MediaType.APPLICATION_JSON_TYPE), String.class);
			System.out.println(login);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Test
	public void loginInfo() {
		String loginInfo = getWebTarget().path("rest").path("loginRS").path("loginInfo").request()
				.accept(MediaType.APPLICATION_JSON).get(String.class);
		System.out.println(loginInfo);
	}

	@Test
	public void loginStr() {
		ResponseWS loginStr = getWebTarget().path("rest").path("loginRS").path("loginStr")
				.queryParam("string", "loginStr").request(MediaType.TEXT_PLAIN_TYPE).accept(MediaType.APPLICATION_JSON)
				.get(ResponseWS.class);
		System.out.println(loginStr);
	}

	public String getBaseUrl() {
		return "http://localhost:8080/projectName-web-all";
	}

}
