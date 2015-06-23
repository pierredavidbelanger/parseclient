package ca.pjer.parseclient;

import java.io.InputStream;
import java.util.concurrent.Future;

public interface FileResources {

	ParseFile upload(String name, String contentType, InputStream inputStream);

	Future<ParseFile> uploadAsync(String name, String contentType, InputStream inputStream);

	Operation<ParseFile> uploadOperation(String name, String contentType, InputStream inputStream);

	void delete(String name);

	Future<Void> deleteAsync(String name);

	Operation<Void> deleteOperation(String name);

}
