package br.com.company.projectname.ws.abstractions;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.filter.EncodingFilter;

public abstract class RestFulAbstractClient {

	private WebTarget target;

	public WebTarget getWebTarget() {
		if (target == null) {
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			client.register(GZipEncoder.class);
			client.register(EncodingFilter.class);
			URI url = UriBuilder.fromUri(getBaseUrl()).build();
			target = client.target(url);
		}
		return target;
	}

	public WebTarget getWebTarget(String path) {
		if (target == null) {
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			client.register(GZipEncoder.class);
			client.register(EncodingFilter.class);
			URI url = UriBuilder.fromUri(getBaseUrl()).build();
			target = client.target(url).path(path);
		}
		return target;
	}

	public abstract String getBaseUrl();

}