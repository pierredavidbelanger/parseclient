package ca.pjer.parseclient;

class FunctionResultImpl<R> extends ParseError implements FunctionResult<R> {

	private R result;

	public R getResult() {
		return result;
	}

	public void setResult(R result) {
		this.result = result;
	}
}
