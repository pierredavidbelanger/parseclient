package ca.pjer.parseclient;

public class BatchResult {

	private ParseError error;
	private ParseObjectHeader success;

	public ParseError getError() {
		return error;
	}

	void setError(ParseError error) {
		this.error = error;
	}

	public ParseObjectHeader getSuccess() {
		return success;
	}

	void setSuccess(ParseObjectHeader success) {
		this.success = success;
	}
}
