package ca.pjer.parseclient;

import java.util.concurrent.Future;

public interface Query<T extends ParseObject> {

	Query<T> select(String... keys);

	Query<T> where(String where);

	Query<T> constrain(QueryConstraint queryConstraint);

	Query<T> ascending(String ascending);

	Query<T> descending(String descending);

	Query<T> limit(int limit);

	Query<T> skip(int skip);

	Query<T> count();

	QueryResults<T> find();

	Future<QueryResults<T>> findAsync();

	Operation<QueryResults<T>> findOperation();

}
