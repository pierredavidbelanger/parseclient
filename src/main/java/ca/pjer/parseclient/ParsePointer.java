package ca.pjer.parseclient;

public class ParsePointer<T> {

	private final String className;
	private final String objectId;

	public ParsePointer(String className, String objectId) {
		this.className = className;
		this.objectId = objectId;
	}

	public String getClassName() {
		return className;
	}

	public String getObjectId() {
		return objectId;
	}
}
