package ca.pjer.parseclient;

import java.util.List;
import java.util.concurrent.Future;

public interface Perspective {

	Batch beginBatch();

	List<BatchResult> endBatch(Batch batch);

	Future<List<BatchResult>> endBatchAsync(Batch batch);

	Operation<List<BatchResult>> endBatchOperation(Batch batch);

	<T> ObjectResources<T> withObjects(Class<T> type);

	ObjectResources<ParseObject> withObjects(String className);

	<T> ObjectResources<T> withObjects(Class<T> type, String className);

	UserResources<ParseUser> withUsers();

	<T> UserResources<T> withUsers(Class<T> type);

	SessionResources<ParseSession> withSessions();

	<T> SessionResources<T> withSessions(Class<T> type);

	RoleResources<ParseRole> withRoles();

	<T> RoleResources<T> withRoles(Class<T> type);

	FileResources withFiles();

	CloudCode withCloudCode();

}
