package ca.pjer.parseclient;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

class QueryConstraintImpl extends QueryConstraint {

	private final Map<String, Object> where;

	QueryConstraintImpl() {
		where = new LinkedHashMap<String, Object>(5);
	}

	QueryConstraintImpl(QueryConstraintImpl that) {
		this.where = new LinkedHashMap<String, Object>(that.where);
	}

	Map<String, Object> getWhere() {
		return where;
	}

	@Override
	public QueryConstraint containedIn(String key, Object... values) {
		return add(key, "$in", values);
	}

	@Override
	public QueryConstraint contains(String key, String substring) {
		return matches(key, substring, null);
	}

	@Override
	public QueryConstraint containsAll(String key, Object... values) {
		return add(key, "$all", values);
	}

	@Override
	public QueryConstraint doesNotExist(String key) {
		return add(key, "$exists", false);
	}

	@Override
	public QueryConstraint doesNotMatchKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public QueryConstraint doesNotMatchQuery(String key, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public QueryConstraint endsWith(String key, String suffix) {
		return matches(key, Pattern.quote(suffix) + "$");
	}

	@Override
	public QueryConstraint equalTo(String key, Object value) {
		return set(key, value);
	}

	@Override
	public QueryConstraint exists(String key) {
		return add(key, "$exists", true);
	}

	@Override
	public QueryConstraint greaterThan(String key, Object value) {
		return add(key, "$gt", value);
	}

	@Override
	public QueryConstraint greaterThanOrEqualTo(String key, Object value) {
		return add(key, "$gte", value);
	}

	@Override
	public QueryConstraint lessThan(String key, Object value) {
		return add(key, "$lt", value);
	}

	@Override
	public QueryConstraint lessThanOrEqualTo(String key, Object value) {
		return add(key, "$lte", value);
	}

	@Override
	public QueryConstraint matches(String key, String regex) {
		return matches(key, regex, null);
	}

	@Override
	public QueryConstraint matches(String key, String regex, String modifiers) {
		return add(key, "$regex", regex).addOptional(key, "$options", modifiers);
	}

	@Override
	public QueryConstraint matchesKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public QueryConstraint matchesQuery(String key, QueryConstraint queryConstraint) {
		throw new RuntimeException("Not implemented");
	}

	@Override
	public QueryConstraint near(String key, ParseGeoPoint point) {
		return add(key, "$nearSphere", point);
	}

	@Override
	public QueryConstraint notContainedIn(String key, Object... values) {
		return add(key, "$nin", values);
	}

	@Override
	public QueryConstraint notEqualTo(String key, Object value) {
		return add(key, "$ne", value);
	}

	@Override
	public QueryConstraint startsWith(String key, String prefix) {
		return matches(key, "^" + Pattern.quote(prefix));
	}

	@Override
	public QueryConstraint withinGeoBox(String key, ParseGeoPoint southwest, ParseGeoPoint northeast) {
		return add(key, "$within", Collections.singletonMap("$box", Arrays.asList(southwest, northeast)));
	}

	@Override
	public QueryConstraint withinKilometers(String key, ParseGeoPoint point, double maxDistance) {
		return add(key, "$nearSphere", point).add(key, "$maxDistanceInKilometers", maxDistance);
	}

	@Override
	public QueryConstraint withinMiles(String key, ParseGeoPoint point, double maxDistance) {
		return add(key, "$nearSphere", point).add(key, "$maxDistanceInMiles", maxDistance);
	}

	@Override
	public QueryConstraint withinRadians(String key, ParseGeoPoint point, double maxDistance) {
		return add(key, "$nearSphere", point).add(key, "$maxDistanceInRadians", maxDistance);
	}

	protected QueryConstraintImpl set(String key, Object value) {
		QueryConstraintImpl clone = new QueryConstraintImpl(this);
		clone.getWhere().put(key, value);
		return clone;
	}

	protected QueryConstraintImpl addOptional(String key, String op, Object value) {
		if (value == null) return this;
		return add(key, op, value);
	}

	protected QueryConstraintImpl add(String key, String op, Object value) {
		QueryConstraintImpl clone = new QueryConstraintImpl(this);
		Map<String, Object> ops;
		Object o = clone.getWhere().get(key);
		if (o != null && o instanceof Map)
			//noinspection unchecked
			ops = new LinkedHashMap<String, Object>((Map) o);
		else
			ops = new LinkedHashMap<String, Object>(5);
		clone.getWhere().put(key, ops);
		ops.put(op, value);
		return clone;
	}
}
