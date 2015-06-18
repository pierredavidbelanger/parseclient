package ca.pjer.parseclient.support.jersey;

import ca.pjer.parseclient.ParseError;
import ca.pjer.parseclient.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.ClientResponseContext;
import java.io.IOException;

public class ParseClientWithErrorResponseFilter extends ParseClientResponseFilter {

	private final ObjectMapper objectMapper;

	public ParseClientWithErrorResponseFilter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void unsuccessful(ClientResponseContext clientResponseContext) {
		ParseError parseError = null;
		try {
			// TODO: Would be great to deserialize the response entity in an instance of ParseError on a non 2xx response without introducing a dependency with Jackson
			parseError = objectMapper.readValue(clientResponseContext.getEntityStream(), ParseError.class);
		} catch (IOException ignored) {
		}
		throw new ParseException(clientResponseContext.getStatusInfo().getReasonPhrase(), parseError);
	}
}
