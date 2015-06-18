package ca.pjer.parseclient;

class BatchRequestImpl {

	private String method;
	private String path;
	private Object body;

	BatchRequestImpl(String method, String path, Object body) {
		this.method = method;
		this.path = path;
		this.body = body;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}
