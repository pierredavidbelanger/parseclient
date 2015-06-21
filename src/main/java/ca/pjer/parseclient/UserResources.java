package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface UserResources<T> extends Resources<T> {

	UserSignup signup(T user, Boolean useRevocableSession);

	Future<UserSignup> signupAsync(T user, Boolean useRevocableSession);

	Operation<UserSignup> signupOperation(T user, Boolean useRevocableSession);

	T login(String username, String password, Boolean useRevocableSession);

	Future<T> loginAsync(String username, String password, Boolean useRevocableSession);

	Operation<T> loginOperation(String username, String password, Boolean useRevocableSession);

	void logout();

	Future<Void> logoutAsync();

	Operation<Void> logoutOperation();

	void requestPasswordReset(String email);

	Future<Void> requestPasswordResetAsync(String email);

	Operation<Void> requestPasswordResetOperation(String email);

	T me();

	Future<T> meAsync();

	Operation<T> meOperation();

}
