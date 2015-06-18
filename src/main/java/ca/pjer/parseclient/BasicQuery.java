package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface BasicQuery<T extends ParseObject> {

	BasicQuery<T> select(String... keys);

	BasicQuery<T> where(String where);

	BasicQuery<T> ascending(String ascending);

	BasicQuery<T> descending(String descending);

	BasicQuery<T> limit(int limit);

	BasicQuery<T> skip(int skip);

	BasicQuery<T> count();

	QueryResults<T> find();

	Future<QueryResults<T>> findAsync();

	Operation<QueryResults<T>> findOperation();

}
