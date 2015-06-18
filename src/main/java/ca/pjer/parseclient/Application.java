package ca.pjer.parseclient;

public interface Application {

	Application usingRestApiKey(String restApiKey);

	Application usingMasterKey(String masterKey);

	<T extends User> Application registerUserClass(Class<T> type);

	<T extends Session> Application registerSessionClass(Class<T> type);

	<T extends ParseObject> Application registerObjectClass(Class<T> type);

	<T extends ParseObject> Application registerObjectClass(String className, Class<T> type);

	Perspective asAnonymous();

	Perspective asSession(String sessionToken);

	Perspective asMaster();

}
