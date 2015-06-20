package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParseGeoPoint;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParseGeoPointToMapConverter extends StdConverter<ParseGeoPoint, Map> {

	@Override
	public Map convert(ParseGeoPoint parseGeoPoint) {
		Map<String, Object> map = new LinkedHashMap<String, Object>(6);
		map.put("__type", "GeoPoint");
		map.put("latitude", parseGeoPoint.getLatitude());
		map.put("longitude", parseGeoPoint.getLongitude());
		return map;
	}
}
