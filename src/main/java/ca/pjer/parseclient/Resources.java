package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface Resources<T> {

	ParseObjectCreation create(T object);

	Future<ParseObjectCreation> createAsync(T object);

	Operation<ParseObjectCreation> createOperation(T object);

	void create(Batch batch, T object);

	T get(String objectId);

	Future<T> getAsync(String objectId);

	Operation<T> getOperation(String objectId);

	ParseObjectUpdate update(String objectId, T object);

	Future<ParseObjectUpdate> updateAsync(String objectId, T object);

	Operation<ParseObjectUpdate> updateOperation(String objectId, T object);

	void update(Batch batch, String objectId, T object);

	void delete(String objectId);

	Future<Void> deleteAsync(String objectId);

	Operation<Void> deleteOperation(String objectId);

	void delete(Batch batch, String objectId);

	Query<T> query();

}
