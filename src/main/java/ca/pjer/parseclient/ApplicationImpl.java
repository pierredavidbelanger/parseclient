package ca.pjer.parseclient;

import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.Map;

class ApplicationImpl implements Application {

	private final ParseClientImpl parseClient;
	private final String applicationId;
	private String restApiKey;
	private String masterKey;
	private Map<String, Class> nameToType;
	private Map<Class, String> typeToName;

	ApplicationImpl(ParseClientImpl parseClient, String applicationId) {
		this.parseClient = parseClient;
		this.applicationId = applicationId;
	}

	ApplicationImpl(ApplicationImpl that) {
		parseClient = that.parseClient;
		applicationId = that.applicationId;
		restApiKey = that.restApiKey;
		masterKey = that.masterKey;
		nameToType = that.nameToType;
		typeToName = that.typeToName;
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

	@SuppressWarnings("unchecked")
	protected <T> Class<T> getTypeForName(String className) {
		if (nameToType != null && nameToType.containsKey(className))
			return nameToType.get(className);
		if (className.equals("_User"))
			return (Class<T>) User.class;
		else if (className.equals("_Session"))
			return (Class<T>) Session.class;
		return (Class<T>) ParseObject.class;
	}

	protected String getNameForType(Class type) {
		if (typeToName != null && typeToName.containsKey(type))
			return typeToName.get(type);
		if (User.class.isAssignableFrom(type))
			return "_User";
		if (Session.class.isAssignableFrom(type))
			return "_Session";
		return type.getSimpleName();
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

	public <T> Application registerUserClass(Class<T> type) {
		return registerObjectClass("_User", type);
	}

	public <T> Application registerSessionClass(Class<T> type) {
		return registerObjectClass("_Session", type);
	}

	public <T> Application registerObjectClass(Class<T> type) {
		return registerObjectClass(type.getSimpleName(), type);
	}

	public <T> Application registerObjectClass(String className, Class<T> type) {
		ApplicationImpl clone = new ApplicationImpl(this);
		if (clone.nameToType == null) clone.nameToType = new HashMap<String, Class>();
		if (clone.typeToName == null) clone.typeToName = new HashMap<Class, String>();
		clone.nameToType.put(className, type);
		clone.typeToName.put(type, className);
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
