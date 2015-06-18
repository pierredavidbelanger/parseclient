package ca.pjer.parseclient;

import javax.ws.rs.client.WebTarget;

class ObjectResourcesImpl<T extends ParseObject> extends ResourcesImpl<T> implements ObjectResources<T> {

	private String className;

	ObjectResourcesImpl(PerspectiveImpl perspective, Class<? extends T> type, String className) {
		super(perspective, type, "classes");
		this.className = className;
	}

	protected String getClassName() {
		return className;
	}

	@Override
	protected WebTarget getResourceWebTarget() {
		return super.getResourceWebTarget().path(getClassName());
	}

	@Override
	protected String getBatchPath() {
		return super.getBatchPath() + "/" + getClassName();
	}
}
