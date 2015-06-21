package ca.pjer.parseclient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.concurrent.Future;

class PerspectiveImpl implements Perspective {

	private final ApplicationImpl application;
	private final MultivaluedMap<String, Object> headers;

	PerspectiveImpl(ApplicationImpl application, MultivaluedMap<String, Object> headers) {
		this.application = application;
		this.headers = headers;
	}

	protected ApplicationImpl getApplication() {
		return application;
	}

	protected MultivaluedMap<String, Object> getHeaders() {
		return headers;
	}

	protected WebTarget getWebTarget() {
		return getApplication().getParseClient().getWebTarget();
	}

	public Batch beginBatch() {
		return new BatchImpl();
	}

	public List<BatchResult> endBatch(Batch batch) {
		return endBatchOperation(batch).now();
	}

	public Future<List<BatchResult>> endBatchAsync(Batch batch) {
		return endBatchOperation(batch).later();
	}

	public Operation<List<BatchResult>> endBatchOperation(Batch batch) {
		if (batch instanceof BatchImpl) {
			BatchImpl impl = (BatchImpl) batch;
			return new OperationImpl<List<BatchResult>>(getWebTarget().path("batch").request()
					.headers(getHeaders()), OperationImpl.Method.POST,
					Entity.json(impl), new GenericType<List<BatchResult>>() {
			});
		}
		return null;
	}

	public <T extends ParseObject> ParsePointer<T> toPointer(T object) {
		String className = getApplication().getNameForType(object.getClass());
		return new ParsePointer<T>(className, object.getObjectId());
	}

	public <T> T fromPointer(ParsePointer<T> pointer) {
		return fromPointerOperation(pointer).now();
	}

	public <T> Future<T> fromPointerAsync(ParsePointer<T> pointer) {
		return fromPointerOperation(pointer).later();
	}

	public <T> Operation<T> fromPointerOperation(ParsePointer<T> pointer) {
		Class<T> type = getApplication().getTypeForName(pointer.getClassName());
		return new ObjectResourcesImpl<T>(this, type, pointer.getClassName()).getOperation(pointer.getObjectId());
	}

	public <T> ObjectResources<T> withObjects(Class<T> type) {
		String className = getApplication().getNameForType(type);
		return withObjects(type, className);
	}

	public ObjectResources<ParseObject> withObjects(String className) {
		Class<ParseObject> type = getApplication().getTypeForName(className);
		return withObjects(type, className);
	}

	public <T> ObjectResources<T> withObjects(Class<T> type, String className) {
		return new ObjectResourcesImpl<T>(this, type, className);
	}

	public UserResources<User> withUsers() {
		Class<User> type = getApplication().getTypeForName("_User");
		return withUsers(type);
	}

	public <T> UserResources<T> withUsers(Class<T> type) {
		return new UserResourcesImpl<T>(this, type);
	}

	public SessionResources<Session> withSessions() {
		Class<Session> type = getApplication().getTypeForName("_Session");
		return withSessions(type);
	}

	public <T> SessionResources<T> withSessions(Class<T> type) {
		return new SessionResourcesImpl<T>(this, type);
	}

	public CloudCode withCloudCode() {
		return new CloudCodeImpl(this);
	}
}
