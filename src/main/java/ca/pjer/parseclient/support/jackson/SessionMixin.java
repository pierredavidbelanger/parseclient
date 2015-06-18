package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParsePointer;
import ca.pjer.parseclient.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

public interface SessionMixin {

	@JsonIgnore
	String getSessionToken();

	@JsonProperty
	void setSessionToken(String sessionToken);

	@JsonIgnore
	Map<String, Object> getCreatedWith();

	@JsonProperty
	void setCreatedWith(Map<String, Object> createdWith);

	@JsonIgnore
	Boolean getRestricted();

	@JsonProperty
	void setRestricted(Boolean restricted);

	@JsonIgnore
	Date getExpiresAt();

	@JsonProperty
	void setExpiresAt(Date expiresAt);

	@JsonIgnore
	String getInstallationId();

	@JsonProperty
	void setInstallationId(String installationId);

	@JsonIgnore
	ParsePointer<User> getUser();

	@JsonProperty
	void setUser(ParsePointer<User> user);

}
