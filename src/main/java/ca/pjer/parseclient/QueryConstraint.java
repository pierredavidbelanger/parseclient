package ca.pjer.parseclient;

public abstract class QueryConstraint {

	QueryConstraint() {
	}

	public static QueryConstraint where() {
		return new QueryConstraintImpl();
	}

	/**
	 * Add a constraint to the query that requires a particular key's value to be contained in the provided list of values.
	 */
	public abstract QueryConstraint containedIn(String key, Object... values);

	/**
	 * Add a constraint for finding string values that contain a provided string.
	 */
	public abstract QueryConstraint contains(String key, String substring);

	/**
	 * Add a constraint to the query that requires a particular key's value to contain each one of the provided list of values.
	 */
	public abstract QueryConstraint containsAll(String key, Object... values);

	/**
	 * Add a constraint for finding objects that do not contain a given key.
	 */
	public abstract QueryConstraint doesNotExist(String key);

	/**
	 * Add a constraint that requires that a key's value not match a value in an object returned by a different Parse.Query.
	 */
	public abstract QueryConstraint doesNotMatchKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint);

	/**
	 * Add a constraint that requires that a key's value not matches a Parse.Query constraint.
	 */
	public abstract QueryConstraint doesNotMatchQuery(String key, QueryConstraint queryConstraint);

	/**
	 * Add a constraint for finding string values that end with a provided string.
	 */
	public abstract QueryConstraint endsWith(String key, String suffix);

	/**
	 * Add a constraint to the query that requires a particular key's value to be equal to the provided value.
	 */
	public abstract QueryConstraint equalTo(String key, Object value);

	/**
	 * Add a constraint for finding objects that contain the given key.
	 */
	public abstract QueryConstraint exists(String key);

	/**
	 * Add a constraint to the query that requires a particular key's value to be greater than the provided value.
	 */
	public abstract QueryConstraint greaterThan(String key, Object value);

	/**
	 * Add a constraint to the query that requires a particular key's value to be greater than or equal to the provided value.
	 */
	public abstract QueryConstraint greaterThanOrEqualTo(String key, Object value);

	/**
	 * Add a constraint to the query that requires a particular key's value to be less than the provided value.
	 */
	public abstract QueryConstraint lessThan(String key, Object value);

	/**
	 * Add a constraint to the query that requires a particular key's value to be less than or equal to the provided value.
	 */
	public abstract QueryConstraint lessThanOrEqualTo(String key, Object value);

	/**
	 * Add a regular expression constraint for finding string values that match the provided regular expression.
	 */
	public abstract QueryConstraint matches(String key, String regex);

	/**
	 * Add a regular expression constraint for finding string values that match the provided regular expression.
	 */
	public abstract QueryConstraint matches(String key, String regex, String modifiers);

	/**
	 * Add a constraint that requires that a key's value matches a value in an object returned by a different Parse.Query.
	 */
	public abstract QueryConstraint matchesKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint);

	/**
	 * Add a constraint that requires that a key's value matches a Parse.Query constraint.
	 */
	public abstract QueryConstraint matchesQuery(String key, QueryConstraint queryConstraint);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given.
	 */
	public abstract QueryConstraint near(String key, ParseGeoPoint point);

	/**
	 * Add a constraint to the query that requires a particular key's value to not be contained in the provided list of values.
	 */
	public abstract QueryConstraint notContainedIn(String key, Object... values);

	/**
	 * Add a constraint to the query that requires a particular key's value to be not equal to the provided value.
	 */
	public abstract QueryConstraint notEqualTo(String key, Object value);

	/**
	 * Add a constraint for finding string values that start with a provided string.
	 */
	public abstract QueryConstraint startsWith(String key, String prefix);

	/**
	 * Add a constraint to the query that requires a particular key's coordinates be contained within a given rectangular geographic bounding box.
	 */
	public abstract QueryConstraint withinGeoBox(String key, ParseGeoPoint southwest, ParseGeoPoint northeast);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given and within the maximum distance given.
	 */
	public abstract QueryConstraint withinKilometers(String key, ParseGeoPoint point, double maxDistance);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given and within the maximum distance given.
	 */
	public abstract QueryConstraint withinMiles(String key, ParseGeoPoint point, double maxDistance);


	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given and within the maximum distance given.
	 */
	public abstract QueryConstraint withinRadians(String key, ParseGeoPoint point, double maxDistance);

}
