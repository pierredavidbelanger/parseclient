package ca.pjer.parseclient.support.jersey;

import ca.pjer.parseclient.ParseError;
import ca.pjer.parseclient.ParseException;
import ca.pjer.parseclient.support.Utils;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import java.io.IOException;

public class ParseClientWithErrorResponseFilter extends ParseClientResponseFilter {

	@Override
	public void unsuccessful(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) {
		ParseError parseError = null;
		try {
			MessageBodyReader<ParseError> messageBodyReader = Utils.findMessageBodyReader(
					clientRequestContext.getConfiguration(),
					MediaType.APPLICATION_JSON_TYPE);
			parseError = messageBodyReader.readFrom(ParseError.class,
					ParseError.class, null, MediaType.APPLICATION_JSON_TYPE,
					null, clientResponseContext.getEntityStream());
		} catch (IOException ignored) {
			// should we log this one ?
		}
		throw new ParseException(clientResponseContext.getStatusInfo().getReasonPhrase(), parseError);
	}
}
