package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParseDate;
import ca.pjer.parseclient.ParsePointer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.Map;

public class UntypedParseTypeDeserializer extends UntypedObjectDeserializer {

	private final ObjectCodec codec;

	public UntypedParseTypeDeserializer(ObjectCodec codec) {
		this.codec = codec;
	}

	@Override
	protected Object mapObject(JsonParser jp, DeserializationContext ctxt) throws IOException {
		TreeNode treeNode = jp.getCodec().readTree(jp);
		TreeNode __typeNode = treeNode.get("__type");
		if (__typeNode != null && __typeNode instanceof TextNode) {
			String __type = ((TextNode) __typeNode).textValue();
			if (__type != null) {
				if (__type.equals("Date")) {
					return codec.treeToValue(treeNode, ParseDate.class);
				} else if (__type.equals("Pointer")) {
					return codec.treeToValue(treeNode, ParsePointer.class);
				}
			}
		}
		return codec.treeToValue(treeNode, Map.class);
	}
}
