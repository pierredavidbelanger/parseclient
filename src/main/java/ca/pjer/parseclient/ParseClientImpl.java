package ca.pjer.parseclient;

import ca.pjer.parseclient.support.Utils;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyWriter;

class ParseClientImpl extends ParseClient {

	private final WebTarget target;
	private final MessageBodyWriter<Object> messageBodyWriter;

	public ParseClientImpl(WebTarget target) {
		this.target = target;
		this.messageBodyWriter = Utils.findMessageBodyWriter(
				target.getConfiguration(),
				MediaType.APPLICATION_JSON_TYPE);
	}

	protected WebTarget getWebTarget() {
		return target;
	}

	protected MessageBodyWriter<Object> getMessageBodyWriter() {
		return messageBodyWriter;
	}

	@Override
	public Application application(String applicationId) {
		return new ApplicationImpl(this, applicationId);
	}
}
