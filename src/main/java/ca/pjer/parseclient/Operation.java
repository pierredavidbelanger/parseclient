package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface Operation<T> {

	Future<T> later();

	void later(OperationCallback<T> callback);

	T now();

}
