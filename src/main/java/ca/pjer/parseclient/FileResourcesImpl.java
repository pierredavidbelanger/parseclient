package ca.pjer.parseclient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.concurrent.Future;

class FileResourcesImpl implements FileResources {

	private final PerspectiveImpl perspective;

	FileResourcesImpl(PerspectiveImpl perspective) {
		this.perspective = perspective;
	}

	PerspectiveImpl getPerspective() {
		return perspective;
	}

	public ParseFile upload(String name, String contentType, InputStream inputStream) {
		return uploadOperation(name, contentType, inputStream).now();
	}

	public Future<ParseFile> uploadAsync(String name, String contentType, InputStream inputStream) {
		return uploadOperation(name, contentType, inputStream).later();
	}

	public Operation<ParseFile> uploadOperation(String name, String contentType, InputStream inputStream) {
		return new OperationImpl<ParseFile>(getPerspective().getWebTarget().path("files").path(name).request()
				.headers(getPerspective().getHeaders()), OperationImpl.Method.POST,
				Entity.entity(inputStream, MediaType.valueOf(contentType)), ParseFile.class);
	}

	public void delete(String name) {
		deleteOperation(name).now();
	}

	public Future<Void> deleteAsync(String name) {
		return deleteOperation(name).later();
	}

	public Operation<Void> deleteOperation(String name) {
		return new OperationImpl<Void>(getPerspective().getWebTarget().path("files").path(name)
				.request().headers(getPerspective().getHeaders()), OperationImpl.Method.DELETE, null, Void.class);
	}
}
