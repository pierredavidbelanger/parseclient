package ca.pjer.parseclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ParseDate extends Date {

	public static final SimpleDateFormat ISO;

	static {
		ISO = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		ISO.setTimeZone(TimeZone.getTimeZone("UTC"));
	}

	public ParseDate() {
	}

	public ParseDate(String iso) throws ParseException {
		this(ISO.parse(iso).getTime());
	}

	public ParseDate(long date) {
		super(date);
	}
}
