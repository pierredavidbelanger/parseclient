package ca.pjer.parseclient;

import javax.ws.rs.client.Client;

public abstract class ParseClient {

	public static ParseClient create(Client client) {
		return new ParseClientImpl(client.target("https://api.parse.com").path("1"));
	}

	public abstract Application application(String applicationId);

}
