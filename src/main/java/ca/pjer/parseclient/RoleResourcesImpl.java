package ca.pjer.parseclient;

import java.lang.reflect.Type;

class RoleResourcesImpl<T> extends ResourcesImpl<T> implements RoleResources<T> {

	RoleResourcesImpl(PerspectiveImpl perspective, Type type) {
		super(perspective, type, "roles");
	}
}
