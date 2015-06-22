package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface SessionResources<T> extends Resources<T> {

	T me();

	Future<T> meAsync();

	Operation<T> meOperation();

}
