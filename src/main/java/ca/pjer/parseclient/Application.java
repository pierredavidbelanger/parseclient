package ca.pjer.parseclient;

public interface Application {

	Application usingRestApiKey(String restApiKey);

	Application usingMasterKey(String masterKey);

	Perspective asAnonymous();

	Perspective asSession(String sessionToken);

	Perspective asMaster();

}
