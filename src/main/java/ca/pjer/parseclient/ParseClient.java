package ca.pjer.parseclient;

import javax.ws.rs.client.Client;
import java.net.URI;

public abstract class ParseClient {

	public static ParseClient create(Client client, URI uri) {
		return new ParseClientImpl(client.target(uri));
	}

	public abstract Application application(String applicationId);

}
