package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParseFile;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParseFileToMapConverter extends StdConverter<ParseFile, Map> {

	@Override
	public Map convert(ParseFile parseFile) {
		Map<String, String> map = new LinkedHashMap<String, String>(6);
		map.put("__type", "File");
		map.put("name", parseFile.getName());
		map.put("url", parseFile.getUrl());
		return map;
	}
}
