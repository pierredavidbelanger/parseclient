package ca.pjer.parseclient.support.jackson;

import ca.pjer.parseclient.ParseACL;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public interface ParseACLMixin {

	@JsonAnyGetter
	Map<String, Map<String, Boolean>> getPermissions();

	@JsonAnySetter
	void setPermission(String subject, Map<String, Boolean> permission);

	@JsonIgnore
	boolean getPublicReadAccess();

	@JsonIgnore
	ParseACL setPublicReadAccess(boolean allowed);

	@JsonIgnore
	boolean getPublicWriteAccess();

	@JsonIgnore
	ParseACL setPublicWriteAccess(boolean allowed);

	@JsonIgnore
	ParseACL setPublicReadWriteAccess(boolean allowed);

	@JsonIgnore
	boolean getReadAccess(String userObjectId);

	@JsonIgnore
	ParseACL setReadAccess(String userObjectId, boolean allowed);

	@JsonIgnore
	boolean getWriteAccess(String userObjectId);

	@JsonIgnore
	ParseACL setWriteAccess(String userObjectId, boolean allowed);

	@JsonIgnore
	ParseACL setReadWriteAccess(String userObjectId, boolean allowed);

	@JsonIgnore
	boolean getRoleReadAccess(String roleName);

	@JsonIgnore
	ParseACL setRoleReadAccess(String roleName, boolean allowed);

	@JsonIgnore
	boolean getRoleWriteAccess(String roleName);

	@JsonIgnore
	ParseACL setRoleWriteAccess(String roleName, boolean allowed);

	@JsonIgnore
	ParseACL setRoleReadWriteAccess(String roleName, boolean allowed);
}
