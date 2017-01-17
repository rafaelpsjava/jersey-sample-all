package br.com.company.projectname.oauth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.stormpath.sdk.account.Account;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeyList;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.application.Application;
import com.stormpath.sdk.authc.AuthenticationRequest;
import com.stormpath.sdk.authc.UsernamePasswordRequest;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.Clients;
import com.stormpath.sdk.impl.cache.DefaultCacheManager;
import com.stormpath.sdk.resource.ResourceException;

public class OauthApp {

	public static void main(String[] args) {
		ApiKey apiKey = ApiKeys.builder().setFileLocation("classpath:oauth.properties").build();
		Client client = Clients.builder().setApiKey(apiKey).setCacheManager(new DefaultCacheManager()).build();

		// https://api.stormpath.com/ui2/index.html#/applications
		Application application = client.getResource("https://api.stormpath.com/v1/applications/APP_TOKEN",
				Application.class);

		@SuppressWarnings("rawtypes")
		AuthenticationRequest request = new UsernamePasswordRequest("email", "password");
		Account authenticated;

		try {
			Account account = client.instantiate(Account.class);

			try {
				authenticated = application.authenticateAccount(request).getAccount();
				authenticated.delete();
			} catch (Throwable e) {
			}

			account = client.instantiate(Account.class);

			// Set account info and create the account
			account.setGivenName("");
			account.setSurname("");
			account.setUsername("");
			account.setEmail("email");
			account.setPassword("password");

			application.createAccount(account);

			getApiKeyList(account);

			authenticated = application.authenticateAccount(request).getAccount();

			System.out.println("is authenticated true");

		} catch (ResourceException e) {
			System.out.println("Failed to authenticate user");
		}
	}

	private static void getApiKeyList(Account account) {
		ApiKeyList apiKeyList = account.getApiKeys();

		boolean hasApiKey = false;
		String apiKeyId = "";
		String apiSecret = "";

		// If account already has an API Key
		for (Iterator<ApiKey> iter = apiKeyList.iterator(); iter.hasNext();) {
			hasApiKey = true;
			ApiKey element = iter.next();
			apiKeyId = element.getId();
			apiSecret = element.getSecret();
		}

		// If account doesn't have an API Key, generate one
		if (hasApiKey == false) {
			ApiKey newApiKey = account.createApiKey();
			apiKeyId = newApiKey.getId();
			apiSecret = newApiKey.getSecret();
		}

		// Get the username of the account
		String username = account.getUsername();

		// Make a JSON object with the key and secret to send back to the client

		Map<String, String> response = new HashMap<>();

		response.put("api_key", apiKeyId);
		response.put("api_secret", apiSecret);
		response.put("username", username);
	}
}
