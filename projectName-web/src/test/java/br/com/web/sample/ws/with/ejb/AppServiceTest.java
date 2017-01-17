package br.com.web.sample.ws.with.ejb;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import br.com.web.sample.ws.abstractions.RestFulAbstractClient;

public class AppServiceTest extends RestFulAbstractClient {

	@Test
	public void getAppInfoUsingGet() throws Exception {
		try {
			String getAppInfoUsingGet = getWebTarget("rest").path("appUsingEjbService").path("getAppInfoUsingGet")
					.request().accept(MediaType.APPLICATION_JSON).get(String.class);

			System.out.println(getAppInfoUsingGet);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Test
	public void getAppInfoUsingPost() {
		try {
			String getAppInfoUsingPost = getWebTarget("rest").path("appUsingEjbService").path("getAppInfoUsingPost")
					.request().post(Entity.entity("string", MediaType.APPLICATION_JSON), String.class);
			System.out.println(getAppInfoUsingPost);

		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Test
	public void findAllUsingJpql() {
		try {
			String getAppInfoUsingPost = getWebTarget("rest").path("appUsingEjbService").path("findAllUsingJpql")
					.request().accept(MediaType.APPLICATION_JSON).get(String.class);
			System.out.println(getAppInfoUsingPost);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Override
	public String getBaseUrl() {
		return "http://localhost:8080/projectName-web";
	}

}
