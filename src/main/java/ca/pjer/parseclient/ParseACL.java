package ca.pjer.parseclient;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParseACL {

	private static final String publicSubject = "*";
	private static final String roleSubject = "role:";
	private static final String readAllowance = "read";
	private static final String writeAllowance = "write";

	private Map<String, Map<String, Boolean>> permissions;

	public ParseACL() {
		this.permissions = new LinkedHashMap<String, Map<String, Boolean>>(5);
	}

	Map<String, Map<String, Boolean>> getPermissions() {
		return permissions;
	}

	void setPermission(String subject, Map<String, Boolean> permission) {
		getPermissions().put(subject, permission);
	}

	boolean get(String subject, String allowance) {
		Map<String, Boolean> permission = getPermissions().get(subject);
		if (permission == null) return false;
		Boolean allowed = permission.get(allowance);
		if (allowed == null) return false;
		return allowed;
	}

	ParseACL set(String subject, String allowance, boolean allowed) {
		Map<String, Boolean> permission = getPermissions().get(subject);
		if (permission == null) {
			if (!allowed) return this;
			getPermissions().put(subject, permission = new LinkedHashMap<String, Boolean>(3));
		}
		if (allowed)
			permission.put(allowance, true);
		else
			permission.remove(allowance);
		if (permission.isEmpty())
			getPermissions().remove(subject);
		return this;
	}

	public ParseACL reset() {
		getPermissions().clear();
		return this;
	}

	public boolean getPublicReadAccess() {
		return get(publicSubject, readAllowance);
	}

	public ParseACL setPublicReadAccess(boolean allowed) {
		return set(publicSubject, readAllowance, allowed);
	}

	public boolean getPublicWriteAccess() {
		return get(publicSubject, writeAllowance);
	}

	public ParseACL setPublicWriteAccess(boolean allowed) {
		return set(publicSubject, writeAllowance, allowed);
	}

	public ParseACL setPublicReadWriteAccess(boolean allowed) {
		return set(publicSubject, readAllowance, allowed)
				.set(publicSubject, writeAllowance, allowed);
	}

	public boolean getReadAccess(String userObjectId) {
		return get(userObjectId, readAllowance);
	}

	public ParseACL setReadAccess(String userObjectId, boolean allowed) {
		return set(userObjectId, readAllowance, allowed);
	}

	public boolean getWriteAccess(String userObjectId) {
		return get(userObjectId, writeAllowance);
	}

	public ParseACL setWriteAccess(String userObjectId, boolean allowed) {
		return set(userObjectId, writeAllowance, allowed);
	}

	public ParseACL setReadWriteAccess(String userObjectId, boolean allowed) {
		return set(userObjectId, readAllowance, allowed)
				.set(userObjectId, writeAllowance, allowed);
	}

	public boolean getRoleReadAccess(String roleName) {
		return get(roleSubject + roleName, readAllowance);
	}

	public ParseACL setRoleReadAccess(String roleName, boolean allowed) {
		return set(roleSubject + roleName, readAllowance, allowed);
	}

	public boolean getRoleWriteAccess(String roleName) {
		return get(roleSubject + roleName, writeAllowance);
	}

	public ParseACL setRoleWriteAccess(String roleName, boolean allowed) {
		return set(roleSubject + roleName, writeAllowance, allowed);
	}

	public ParseACL setRoleReadWriteAccess(String roleName, boolean allowed) {
		return set(roleSubject + roleName, readAllowance, allowed)
				.set(roleSubject + roleName, writeAllowance, allowed);
	}
}
