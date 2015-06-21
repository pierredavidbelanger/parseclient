package ca.pjer.parseclient;

import javax.ws.rs.ProcessingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class FunctionsInvocationHandlerImpl implements InvocationHandler {

	private final CloudCodeImpl cloudCode;

	FunctionsInvocationHandlerImpl(CloudCodeImpl cloudCode) {
		this.cloudCode = cloudCode;
	}

	CloudCodeImpl getCloudCode() {
		return cloudCode;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String functionName = method.getName();
		Object namedParameters = args.length > 0 ? args[0] : null;
		Class resultType = method.getReturnType();
		FunctionResultImpl functionResult;
		try {
			functionResult = (FunctionResultImpl) getCloudCode().invoke(functionName, namedParameters, resultType);
		} catch (ProcessingException e) {
			if (e.getCause() instanceof ParseException)
				throw e.getCause();
			throw e;
		}
		if (functionResult.getError() != null)
			throw new ParseException(functionResult.getError(), functionResult);
		return functionResult.getResult();
	}
}
