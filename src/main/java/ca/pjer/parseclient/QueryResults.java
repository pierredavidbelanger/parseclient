package ca.pjer.parseclient;

public interface QueryResults<T extends ParseObject> extends Iterable<T> {

	Integer count();

	T first();

}
