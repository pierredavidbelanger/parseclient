package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParseDate;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParseDateToMapConverter extends StdConverter<ParseDate, Map> {

	@Override
	public Map convert(ParseDate parseDate) {
		Map<String, String> map = new LinkedHashMap<String, String>(4);
		map.put("__type", "Date");
		map.put("iso", ParseDate.ISO.format(parseDate));
		return map;
	}
}
