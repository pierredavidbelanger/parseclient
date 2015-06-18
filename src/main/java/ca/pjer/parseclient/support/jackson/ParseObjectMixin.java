package ca.pjer.parseclient.support.jackson;


import ca.pjer.parseclient.ParseACL;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public interface ParseObjectMixin {

	@JsonAnyGetter
	Map<String, Object> getProperties();

	@JsonAnySetter
	Object set(String name, Object value);

	@JsonProperty("ACL")
	ParseACL getACL();

	@JsonProperty("ACL")
	void setACL(ParseACL ACL);

}
