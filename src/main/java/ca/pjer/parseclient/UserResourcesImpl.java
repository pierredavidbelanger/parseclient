package ca.pjer.parseclient;

import ca.pjer.parseclient.support.Utils;
import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.concurrent.Future;

class UserResourcesImpl<T> extends ResourcesImpl<T> implements UserResources<T> {

	UserResourcesImpl(PerspectiveImpl perspective, Type type) {
		super(perspective, type, "users");
	}

	public UserSignup signup(T user, Boolean useRevocableSession) {
		return signupOperation(user, useRevocableSession).now();
	}

	public Future<UserSignup> signupAsync(T user, Boolean useRevocableSession) {
		return signupOperation(user, useRevocableSession).later();
	}

	public Operation<UserSignup> signupOperation(T user, Boolean useRevocableSession) {
		return new OperationImpl<UserSignup>(getResourceWebTarget().request()
				.headers(getHeaders(useRevocableSession)), OperationImpl.Method.POST,
				Entity.json(user), User.class);
	}

	public T login(String username, String password, Boolean useRevocableSession) {
		return loginOperation(username, password, useRevocableSession).now();
	}

	public Future<T> loginAsync(String username, String password, Boolean useRevocableSession) {
		return loginOperation(username, password, useRevocableSession).later();
	}

	public Operation<T> loginOperation(String username, String password, Boolean useRevocableSession) {
		return new OperationImpl<T>(getWebTarget().path("login")
				.queryParam(
						Utils.queryParamSpaceEncoded("username"),
						Utils.queryParamSpaceEncoded(username))
				.queryParam(
						Utils.queryParamSpaceEncoded("password"),
						Utils.queryParamSpaceEncoded(password))
				.request().headers(getHeaders(useRevocableSession)),
				OperationImpl.Method.GET, null, getType());
	}

	public void logout() {
		logoutOperation().now();
	}

	public Future<Void> logoutAsync() {
		return logoutOperation().later();
	}

	public Operation<Void> logoutOperation() {
		return new OperationImpl<Void>(getWebTarget().path("logout").request()
				.headers(getHeaders()), OperationImpl.Method.POST, null, Void.class);
	}

	public void requestPasswordReset(String email) {
		requestPasswordResetOperation(email).now();
	}

	public Future<Void> requestPasswordResetAsync(String email) {
		return requestPasswordResetOperation(email).later();
	}

	public Operation<Void> requestPasswordResetOperation(String email) {
		return new OperationImpl<Void>(getWebTarget().path("requestPasswordReset").request()
				.headers(getHeaders()), OperationImpl.Method.POST,
				Entity.json(Collections.singletonMap("email", email)), Void.class);
	}

	public T me() {
		return meOperation().now();
	}

	public Future<T> meAsync() {
		return meOperation().later();
	}

	public Operation<T> meOperation() {
		return getOperation("me");
	}

	protected MultivaluedMap<String, Object> getHeaders(Boolean useRevocableSession) {
		MultivaluedMap<String, Object> headers = getHeaders();
		if (useRevocableSession != null) {
			headers = new MultivaluedHashMap<String, Object>(headers);
			headers.putSingle("X-Parse-Revocable-Session", useRevocableSession ? "1" : "0");
			headers = new ImmutableMultivaluedMap<String, Object>(headers);
		}
		return headers;
	}
}
