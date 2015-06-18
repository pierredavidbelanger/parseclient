package ca.pjer.parseclient;

public interface FunctionResult<R> {

	int getCode();

	String getError();

	R getResult();

}
