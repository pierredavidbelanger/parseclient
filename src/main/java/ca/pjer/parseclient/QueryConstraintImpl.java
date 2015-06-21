package ca.pjer.parseclient;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

class QueryConstraintImpl extends LinkedHashMap<String, Object> implements QueryConstraint {

	QueryConstraintImpl() {
		super(5);
	}

	QueryConstraintImpl(QueryConstraint... queryConstraints) {
		this();
		put("$or", queryConstraints);
	}

	QueryConstraintImpl(QueryConstraintImpl that) {
		super(that);
	}

	public QueryConstraint containedIn(String key, Object... values) {
		return add(key, "$in", values);
	}

	public QueryConstraint contains(String key, String substring) {
		return matches(key, substring, null);
	}

	public QueryConstraint containsAll(String key, Object... values) {
		return add(key, "$all", values);
	}

	public QueryConstraint doesNotExist(String key) {
		return add(key, "$exists", false);
	}

	public QueryConstraint doesNotMatchKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	public QueryConstraint doesNotMatchQuery(String key, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	public QueryConstraint endsWith(String key, String suffix) {
		return matches(key, Pattern.quote(suffix) + "$");
	}

	public QueryConstraint equalTo(String key, Object value) {
		return set(key, value);
	}

	public QueryConstraint exists(String key) {
		return add(key, "$exists", true);
	}

	public QueryConstraint greaterThan(String key, Object value) {
		return add(key, "$gt", value);
	}

	public QueryConstraint greaterThanOrEqualTo(String key, Object value) {
		return add(key, "$gte", value);
	}

	public QueryConstraint lessThan(String key, Object value) {
		return add(key, "$lt", value);
	}

	public QueryConstraint lessThanOrEqualTo(String key, Object value) {
		return add(key, "$lte", value);
	}

	public QueryConstraint matches(String key, String regex) {
		return matches(key, regex, null);
	}

	public QueryConstraint matches(String key, String regex, String modifiers) {
		return add(key, "$regex", regex).addOptional(key, "$options", modifiers);
	}

	public QueryConstraint matchesKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	public QueryConstraint matchesQuery(String key, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	public QueryConstraint near(String key, ParseGeoPoint point) {
		return add(key, "$nearSphere", point);
	}

	public QueryConstraint notContainedIn(String key, Object... values) {
		return add(key, "$nin", values);
	}

	public QueryConstraint notEqualTo(String key, Object value) {
		return add(key, "$ne", value);
	}

	public QueryConstraint startsWith(String key, String prefix) {
		return matches(key, "^" + Pattern.quote(prefix));
	}

	public QueryConstraint withinGeoBox(String key, ParseGeoPoint southwest, ParseGeoPoint northeast) {
		return add(key, "$within", Collections.singletonMap("$box", Arrays.asList(southwest, northeast)));
	}

	public QueryConstraint withinKilometers(String key, ParseGeoPoint point, double maxDistance) {
		return add(key, "$nearSphere", point).add(key, "$maxDistanceInKilometers", maxDistance);
	}

	public QueryConstraint withinMiles(String key, ParseGeoPoint point, double maxDistance) {
		return add(key, "$nearSphere", point).add(key, "$maxDistanceInMiles", maxDistance);
	}

	public QueryConstraint withinRadians(String key, ParseGeoPoint point, double maxDistance) {
		return add(key, "$nearSphere", point).add(key, "$maxDistanceInRadians", maxDistance);
	}

	protected QueryConstraintImpl set(String key, Object value) {
		QueryConstraintImpl clone = new QueryConstraintImpl(this);
		clone.put(key, value);
		return clone;
	}

	protected QueryConstraintImpl addOptional(String key, String op, Object value) {
		if (value == null) return this;
		return add(key, op, value);
	}

	protected QueryConstraintImpl add(String key, String op, Object value) {
		QueryConstraintImpl clone = new QueryConstraintImpl(this);
		Map<String, Object> ops;
		Object o = clone.get(key);
		if (o != null && o instanceof Map)
			//noinspection unchecked
			ops = new LinkedHashMap<String, Object>((Map) o);
		else
			ops = new LinkedHashMap<String, Object>(5);
		ops.put(op, value);
		clone.put(key, ops);
		return clone;
	}
}
