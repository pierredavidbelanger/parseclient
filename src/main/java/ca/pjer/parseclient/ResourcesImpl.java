package ca.pjer.parseclient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Type;
import java.util.concurrent.Future;

abstract class ResourcesImpl<T extends ParseObject> implements Resources<T> {

	private final PerspectiveImpl perspective;
	private final Type type;
	private final String resourcePath;

	protected ResourcesImpl(PerspectiveImpl perspective, Type type, String resourcePath) {
		this.perspective = perspective;
		this.type = type;
		this.resourcePath = resourcePath;
	}

	protected PerspectiveImpl getPerspective() {
		return perspective;
	}

	protected Type getType() {
		return type;
	}

	protected String getResourcePath() {
		return resourcePath;
	}

	protected WebTarget getWebTarget() {
		return getPerspective().getWebTarget();
	}

	protected WebTarget getResourceWebTarget() {
		return getWebTarget().path(getResourcePath());
	}

	protected MultivaluedMap<String, Object> getHeaders() {
		return getPerspective().getHeaders();
	}

	public ParseObjectCreation create(T object) {
		return createOperation(object).now();
	}

	public Future<ParseObjectCreation> createAsync(T object) {
		return createOperation(object).later();
	}

	public Operation<ParseObjectCreation> createOperation(T object) {
		return new OperationImpl<ParseObjectCreation>(getResourceWebTarget().request()
				.headers(getHeaders()), OperationImpl.Method.POST,
				Entity.json(object), ParseObjectHeader.class);
	}

	public void create(Batch batch, T object) {
		batch(batch, "POST", "", object);
	}

	public T get(String objectId) {
		return getOperation(objectId).now();
	}

	public Future<T> getAsync(String objectId) {
		return getOperation(objectId).later();
	}

	public Operation<T> getOperation(String objectId) {
		return new OperationImpl<T>(getResourceWebTarget().path(objectId).request()
				.headers(getHeaders()), OperationImpl.Method.GET, null, getType());
	}

	public ParseObjectUpdate update(String objectId, T object) {
		return updateOperation(objectId, object).now();
	}

	public Future<ParseObjectUpdate> updateAsync(String objectId, T object) {
		return updateOperation(objectId, object).later();
	}

	public Operation<ParseObjectUpdate> updateOperation(String objectId, T object) {
		return new OperationImpl<ParseObjectUpdate>(getResourceWebTarget().path(objectId).request()
				.headers(getHeaders()), OperationImpl.Method.PUT, Entity.json(object), ParseObjectHeader.class);
	}

	public void update(Batch batch, String objectId, T object) {
		batch(batch, "PUT", "/" + objectId, object);
	}

	public void delete(String objectId) {
		deleteOperation(objectId).now();
	}

	public Future<Void> deleteAsync(String objectId) {
		return deleteOperation(objectId).later();
	}

	public Operation<Void> deleteOperation(String objectId) {
		return new OperationImpl<Void>(getResourceWebTarget().path(objectId).request()
				.headers(getHeaders()), OperationImpl.Method.DELETE, null, Void.class);
	}

	public void delete(Batch batch, String objectId) {
		batch(batch, "DELETE", "/" + objectId, null);
	}

	protected void batch(Batch batch, String method, String path, Object body) {
		if (batch instanceof BatchImpl)
			((BatchImpl) batch).getRequests().add(
					new BatchRequestImpl(method,
							getBatchPath() + path,
							body));
	}

	public Query<T> query() {
		return new QueryImpl<T>(this);
	}

	protected String getBatchPath() {
		return "/1/" + getResourcePath();
	}
}
