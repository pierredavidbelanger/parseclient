package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface CloudCode {

	FunctionResult<Object> invoke(String functionName, Object namedParameters);

	Future<FunctionResult<Object>> invokeAsync(String functionName, Object namedParameters);

	Operation<FunctionResult<Object>> invokeOperation(String functionName, Object namedParameters);

	<R> FunctionResult<R> invoke(String functionName, Object namedParameters, Class<R> resultType);

	<R> Future<FunctionResult<R>> invokeAsync(String functionName, Object namedParameters, Class<R> resultType);

	<R> Operation<FunctionResult<R>> invokeOperation(String functionName, Object namedParameters, Class<R> resultType);

	<I> I proxy(Class<I> interfaceType);

}
