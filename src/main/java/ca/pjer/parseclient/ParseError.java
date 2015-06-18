package ca.pjer.parseclient;

public class ParseError {

	private int code;
	private String error;

	public int getCode() {
		return code;
	}

	void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	void setError(String error) {
		this.error = error;
	}
}
