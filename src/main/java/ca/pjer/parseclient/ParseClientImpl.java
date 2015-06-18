package ca.pjer.parseclient;

import javax.ws.rs.client.WebTarget;

class ParseClientImpl extends ParseClient {

	private final WebTarget target;

	public ParseClientImpl(WebTarget target) {
		this.target = target;
	}

	protected WebTarget getWebTarget() {
		return target;
	}

	@Override
	public Application application(String applicationId) {
		return new ApplicationImpl(this, applicationId);
	}
}
