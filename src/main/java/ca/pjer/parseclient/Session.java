package ca.pjer.parseclient;

import java.util.Date;
import java.util.Map;

public class Session extends ParseObject implements UserSignup {

	private String sessionToken;
	private Map<String, Object> createdWith;
	private Boolean restricted;
	private Date expiresAt;
	private String installationId;
	private ParsePointer<User> user;

	public String getSessionToken() {
		return sessionToken;
	}

	void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public Map<String, Object> getCreatedWith() {
		return createdWith;
	}

	void setCreatedWith(Map<String, Object> createdWith) {
		this.createdWith = createdWith;
	}

	public Boolean getRestricted() {
		return restricted;
	}

	void setRestricted(Boolean restricted) {
		this.restricted = restricted;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getInstallationId() {
		return installationId;
	}

	void setInstallationId(String installationId) {
		this.installationId = installationId;
	}

	public ParsePointer<User> getUser() {
		return user;
	}

	void setUser(ParsePointer<User> user) {
		this.user = user;
	}
}
