package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParsePointer;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParsePointerToMapConverter extends StdConverter<ParsePointer, Map> {

	@Override
	public Map convert(ParsePointer parsePointer) {
		Map<String, String> map = new LinkedHashMap<String, String>(6);
		map.put("__type", "Pointer");
		map.put("className", parsePointer.getClassName());
		map.put("objectId", parsePointer.getObjectId());
		return map;
	}
}
