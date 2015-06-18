package ca.pjer.parseclient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

class OperationImpl<T> implements Operation<T> {

	enum Method {GET, POST, PUT, DELETE}

	private static final Logger logger = Logger.getLogger(OperationImpl.class.getName());

	private final Invocation.Builder invocationBuilder;
	private final Method method;
	private final Entity entity;
	private final GenericType<T> responseGenericType;

	OperationImpl(Invocation.Builder invocationBuilder, Method method, Entity entity, Type responseType) {
		this(invocationBuilder, method, entity, new GenericType<T>(responseType));
	}

	OperationImpl(Invocation.Builder invocationBuilder, Method method, Entity entity, GenericType<T> responseGenericType) {
		this.invocationBuilder = invocationBuilder;
		this.method = method;
		this.entity = entity;
		this.responseGenericType = responseGenericType;
	}

	protected Invocation getInvocation() {
		if (entity != null)
			return invocationBuilder.build(method.toString(), entity);
		return invocationBuilder.build(method.toString());
	}

	protected GenericType<T> getResponseGenericType() {
		return responseGenericType;
	}

	public Future<T> later() {
		return getInvocation().submit(getResponseGenericType());
	}

	public void later(final OperationCallback<T> callback) {
		getInvocation().submit(new InvocationCallback<Response>() {

			public void completed(Response response) {
				if (callback == null)
					return;
				try {
					callback.future(response.readEntity(getResponseGenericType()), null);
				} catch (Throwable throwable) {
					failed(throwable);
				}
			}

			public void failed(Throwable throwable) {
				if (callback != null)
					callback.future(null, throwable);
				else if (logger.isLoggable(Level.WARNING))
					logger.log(Level.WARNING, "Invocation failed and callback is null: " + throwable.getMessage(), throwable);
			}
		});
	}

	public T now() {
		return getInvocation().invoke(getResponseGenericType());
	}
}
