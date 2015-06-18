package ca.pjer.parseclient;

import java.util.List;
import java.util.concurrent.Future;

public interface Perspective {

	Batch beginBatch();

	List<BatchResult> endBatch(Batch batch);

	Future<List<BatchResult>> endBatchAsync(Batch batch);

	Operation<List<BatchResult>> endBatchOperation(Batch batch);

	<T extends ParseObject> ParsePointer<T> toPointer(T object);

	<T extends ParseObject> T fromPointer(ParsePointer<T> pointer);

	<T extends ParseObject> Future<T> fromPointerAsync(ParsePointer<T> pointer);

	<T extends ParseObject> Operation<T> fromPointerOperation(ParsePointer<T> pointer);

	<T extends ParseObject> ObjectResources<T> withObjects(Class<T> type);

	ObjectResources<ParseObject> withObjects(String className);

	<T extends ParseObject> ObjectResources<T> withObjects(Class<T> type, String className);

	UserResources<User> withUsers();

	<T extends User> UserResources<T> withUsers(Class<T> type);

	SessionResources<Session> withSessions();

	<T extends Session> SessionResources<T> withSessions(Class<T> type);

	CloudCode withCloudCode();

}
