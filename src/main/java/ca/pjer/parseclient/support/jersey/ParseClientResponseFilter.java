package ca.pjer.parseclient.support.jersey;

import ca.pjer.parseclient.ParseException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class ParseClientResponseFilter implements ClientResponseFilter {

	public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
		if (clientResponseContext.getStatusInfo().getFamily() != Response.Status.Family.SUCCESSFUL)
			unsuccessful(clientRequestContext, clientResponseContext);
	}

	public void unsuccessful(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) {
		throw new ParseException(clientResponseContext.getStatusInfo().getReasonPhrase(), null);
	}
}
