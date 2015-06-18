package ca.pjer.parseclient;

class SessionResourcesImpl<T extends Session> extends ResourcesImpl<T> implements SessionResources<T> {

	SessionResourcesImpl(PerspectiveImpl perspective, Class<T> type) {
		super(perspective, type, "sessions");
	}
}
