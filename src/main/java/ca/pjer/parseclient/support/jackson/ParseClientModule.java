package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.*;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;

import java.util.logging.Logger;

public class ParseClientModule extends Module {

	private static final Logger logger = Logger.getLogger(ParseClientModule.class.getName());

	@Override
	public String getModuleName() {
		return "ParseClient";
	}

	@Override
	public Version version() {
		return Version.unknownVersion();
	}

	@Override
	public void setupModule(SetupContext setupContext) {

		setupContext.setMixInAnnotations(ParseObjectHeader.class, ParseObjectHeaderMixin.class);
		setupContext.setMixInAnnotations(ParseObject.class, ParseObjectMixin.class);
		setupContext.setMixInAnnotations(ParseACL.class, ParseACLMixin.class);
		setupContext.setMixInAnnotations(Session.class, SessionMixin.class);

		SimpleSerializers serializers = new SimpleSerializers();
		serializers.addSerializer(new StdDelegatingSerializer(ParseDate.class, new ParseDateToMapConverter()));
		serializers.addSerializer(new StdDelegatingSerializer(ParseGeoPoint.class, new ParseGeoPointToMapConverter()));
		serializers.addSerializer(new StdDelegatingSerializer(ParsePointer.class, new ParsePointerToMapConverter()));
		setupContext.addSerializers(serializers);

		SimpleDeserializers deserializers = new SimpleDeserializers();
		deserializers.addDeserializer(Object.class, new UntypedParseTypeDeserializer(setupContext.getOwner()));
		deserializers.addDeserializer(ParseDate.class, new StdDelegatingDeserializer<ParseDate>(new ParseDateFromMapConverter()));
		deserializers.addDeserializer(ParseGeoPoint.class, new StdDelegatingDeserializer<ParseGeoPoint>(new ParseGeoPointFromMapConverter()));
		deserializers.addDeserializer(ParsePointer.class, new StdDelegatingDeserializer<ParsePointer>(new ParsePointerFromMapConverter()));
		setupContext.addDeserializers(deserializers);
	}
}
