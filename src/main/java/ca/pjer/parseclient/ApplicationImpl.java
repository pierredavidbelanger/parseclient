package ca.pjer.parseclient;

import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

class ApplicationImpl implements Application {

	private final ParseClientImpl parseClient;
	private final String applicationId;
	private String restApiKey;
	private String masterKey;

	ApplicationImpl(ParseClientImpl parseClient, String applicationId) {
		this.parseClient = parseClient;
		this.applicationId = applicationId;
	}

	ApplicationImpl(ApplicationImpl that) {
		parseClient = that.parseClient;
		applicationId = that.applicationId;
		restApiKey = that.restApiKey;
		masterKey = that.masterKey;
	}

	protected ParseClientImpl getParseClient() {
		return parseClient;
	}

	protected String getApplicationId() {
		return applicationId;
	}

	protected String getRestApiKey() {
		return restApiKey;
	}

	protected String getMasterKey() {
		return masterKey;
	}

	public Application usingRestApiKey(String restApiKey) {
		ApplicationImpl clone = new ApplicationImpl(this);
		clone.restApiKey = restApiKey;
		return clone;
	}

	public Application usingMasterKey(String masterKey) {
		ApplicationImpl clone = new ApplicationImpl(this);
		clone.masterKey = masterKey;
		return clone;
	}

	public Perspective asAnonymous() {
		return getPerspective(getApplicationId(), getRestApiKey(), null, null);
	}

	public Perspective asSession(String sessionToken) {
		return getPerspective(getApplicationId(), getRestApiKey(), sessionToken, null);
	}

	public Perspective asMaster() {
		return getPerspective(getApplicationId(), null, null, getMasterKey());
	}

	protected Perspective getPerspective(String applicationId, String restApiKey, String sessionToken, String masterKey) {
		MultivaluedMap<String, Object> headers = new MultivaluedHashMap<String, Object>(6);
		if (applicationId != null)
			headers.putSingle("X-Parse-Application-Id", applicationId);
		if (restApiKey != null)
			headers.putSingle("X-Parse-REST-API-Key", restApiKey);
		if (sessionToken != null)
			headers.putSingle("X-Parse-Session-Token", sessionToken);
		if (masterKey != null)
			headers.putSingle("X-Parse-Master-Key", masterKey);
		return new PerspectiveImpl(this, new ImmutableMultivaluedMap<String, Object>(headers));
	}
}
