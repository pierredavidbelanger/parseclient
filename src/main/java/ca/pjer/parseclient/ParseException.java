package ca.pjer.parseclient;

public class ParseException extends RuntimeException {

	private final ParseError error;

	public ParseException(String message, ParseError error) {
		super(message);
		this.error = error;
	}

	public ParseException(String message, Throwable cause, ParseError error) {
		super(message, cause);
		this.error = error;
	}

	public ParseError getError() {
		return error;
	}
}
