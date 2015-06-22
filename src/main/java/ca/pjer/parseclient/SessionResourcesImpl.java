package ca.pjer.parseclient;

import java.util.concurrent.Future;

class SessionResourcesImpl<T> extends ResourcesImpl<T> implements SessionResources<T> {

	SessionResourcesImpl(PerspectiveImpl perspective, Class<T> type) {
		super(perspective, type, "sessions");
	}

	public T me() {
		return meOperation().now();
	}

	public Future<T> meAsync() {
		return meOperation().later();
	}

	public Operation<T> meOperation() {
		return getOperation("me");
	}
}
