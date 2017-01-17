package br.com.web.sample.ws;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import br.com.web.sample.ws.abstractions.RestFulAbstractClient;

public class AppServiceTest extends RestFulAbstractClient {

	@Test
	public void getAppInfoUsingGet() throws Exception {
		try {
			String getAppInfoUsingGet = getWebTarget("rest").path("appService").path("getAppInfoUsingGet").request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			System.out.println(getAppInfoUsingGet);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Test
	public void getAppInfoWithParameterUsingGet() throws Exception {
		try {
			String getAppInfoWithParameter = getWebTarget("rest").path("appService")
					.path("getAppInfoWithParameterUsingGet").request().accept(MediaType.APPLICATION_JSON)
					.get(String.class);
			System.out.println(getAppInfoWithParameter);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Test
	public void getAppInfoUsingPost() {
		try {
			String getAppInfoUsingPost = getWebTarget("rest").path("appService").path("getAppInfoUsingPost").request()
					.accept(MediaType.APPLICATION_JSON)
					.post(Entity.entity("string", MediaType.APPLICATION_JSON), String.class);
			System.out.println(getAppInfoUsingPost);
		} catch (Throwable throwable) {
			System.out.println(throwable.getMessage());
		}
	}

	@Test
	public void findAllUsingJpql() {
		try {
			String getAppInfoUsingPost = getWebTarget("rest").path("appService").path("findAllUsingJpql").request()
					.accept(MediaType.APPLICATION_JSON).get(String.class);
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
