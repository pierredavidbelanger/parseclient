package ca.pjer.parseclient;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.concurrent.Future;

class CloudCodeImpl implements CloudCode {

	private final PerspectiveImpl perspective;

	CloudCodeImpl(PerspectiveImpl perspective) {
		this.perspective = perspective;
	}

	PerspectiveImpl getPerspective() {
		return perspective;
	}

	public FunctionResult<Object> invoke(String functionName, Object namedParameters) {
		return invokeOperation(functionName, namedParameters).now();
	}

	public Future<FunctionResult<Object>> invokeAsync(String functionName, Object namedParameters) {
		return invokeOperation(functionName, namedParameters).later();
	}

	public Operation<FunctionResult<Object>> invokeOperation(String functionName, Object namedParameters) {
		return invokeOperation(functionName, namedParameters, Object.class);
	}

	public <R> FunctionResult<R> invoke(String functionName, Object namedParameters, Class<R> resultType) {
		return invokeOperation(functionName, namedParameters, resultType).now();
	}

	public <R> Future<FunctionResult<R>> invokeAsync(String functionName, Object namedParameters, Class<R> resultType) {
		return invokeOperation(functionName, namedParameters, resultType).later();
	}

	public <R> Operation<FunctionResult<R>> invokeOperation(String functionName, Object namedParameters, Class<R> resultType) {
		return new OperationImpl<FunctionResult<R>>(getPerspective().getWebTarget().path("functions").path(functionName).request()
				.headers(getPerspective().getHeaders()), OperationImpl.Method.POST, Entity.json(namedParameters),
				getReturnType(resultType));
	}

	public <I> I proxy(Class<I> interfaceType) {
		//noinspection unchecked
		return (I) Proxy.newProxyInstance(interfaceType.getClassLoader(),
				new Class[]{interfaceType}, new FunctionsInvocationHandlerImpl(this));
	}

	protected <R> GenericType<FunctionResult<R>> getReturnType(final Class<R> resultType) {
		return new GenericType<FunctionResult<R>>(new ParameterizedType() {

			public Type[] getActualTypeArguments() {
				return new Type[]{resultType};
			}

			public Type getRawType() {
				return FunctionResultImpl.class;
			}

			public Type getOwnerType() {
				return null;
			}
		});
	}
}
