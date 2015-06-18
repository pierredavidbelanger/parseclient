package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParseDate;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.text.ParseException;
import java.util.Map;

public class ParseDateFromMapConverter extends StdConverter<Map, ParseDate> {

	@Override
	public ParseDate convert(Map map) {
		try {
			return new ParseDate((String) map.get("iso"));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
