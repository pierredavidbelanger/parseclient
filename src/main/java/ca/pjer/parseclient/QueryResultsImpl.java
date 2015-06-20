package ca.pjer.parseclient;

import java.util.Iterator;
import java.util.List;

class QueryResultsImpl<T extends ParseObject> implements QueryResults<T> {

	private List<T> results;
	private Integer count;

	List<T> getResults() {
		return results;
	}

	void setResults(List<T> results) {
		this.results = results;
	}

	Integer getCount() {
		return count;
	}

	void setCount(Integer count) {
		this.count = count;
	}

	public Iterator<T> iterator() {
		return results.iterator();
	}

	public Integer count() {
		return getCount();
	}

	public T first() {
		Iterator<T> iterator = iterator();
		if (!iterator.hasNext()) return null;
		return iterator.next();
	}
}
