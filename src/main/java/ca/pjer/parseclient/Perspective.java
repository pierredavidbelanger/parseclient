package ca.pjer.parseclient;

import java.util.List;
import java.util.concurrent.Future;

public interface Perspective {

	Batch beginBatch();

	List<BatchResult> endBatch(Batch batch);

	Future<List<BatchResult>> endBatchAsync(Batch batch);

	Operation<List<BatchResult>> endBatchOperation(Batch batch);

	<T extends ParseObject> ParsePointer<T> toPointer(T object);

	<T> T fromPointer(ParsePointer<T> pointer);

	<T> Future<T> fromPointerAsync(ParsePointer<T> pointer);

	<T> Operation<T> fromPointerOperation(ParsePointer<T> pointer);

	<T> ObjectResources<T> withObjects(Class<T> type);

	ObjectResources<ParseObject> withObjects(String className);

	<T> ObjectResources<T> withObjects(Class<T> type, String className);

	UserResources<User> withUsers();

	<T> UserResources<T> withUsers(Class<T> type);

	SessionResources<Session> withSessions();

	<T> SessionResources<T> withSessions(Class<T> type);

	CloudCode withCloudCode();

}
