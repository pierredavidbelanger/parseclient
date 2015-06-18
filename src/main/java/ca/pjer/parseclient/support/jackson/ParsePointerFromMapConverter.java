package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParsePointer;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Map;

public class ParsePointerFromMapConverter extends StdConverter<Map, ParsePointer> {

	static class ParsePointerImpl implements ParsePointer {

		private final String className;
		private final String objectId;

		public ParsePointerImpl(String className, String objectId) {
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

	@Override
	public ParsePointer convert(Map map) {
		return new ParsePointerImpl((String) map.get("className"), (String) map.get("objectId"));
	}
}
