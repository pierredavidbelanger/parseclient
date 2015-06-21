package ca.pjer.parseclient;

public interface Application {

	Application usingRestApiKey(String restApiKey);

	Application usingMasterKey(String masterKey);

	<T> Application registerUserClass(Class<T> type);

	<T> Application registerSessionClass(Class<T> type);

	<T> Application registerObjectClass(Class<T> type);

	<T> Application registerObjectClass(String className, Class<T> type);

	Perspective asAnonymous();

	Perspective asSession(String sessionToken);

	Perspective asMaster();

}
