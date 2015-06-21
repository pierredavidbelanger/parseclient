package ca.pjer.parseclient;

public interface QueryConstraint {

	/**
	 * Add a constraint to the query that requires a particular key's value to be contained in the provided list of values.
	 */
	QueryConstraint containedIn(String key, Object... values);

	/**
	 * Add a constraint for finding string values that contain a provided string.
	 */
	QueryConstraint contains(String key, String substring);

	/**
	 * Add a constraint to the query that requires a particular key's value to contain each one of the provided list of values.
	 */
	QueryConstraint containsAll(String key, Object... values);

	/**
	 * Add a constraint for finding objects that do not contain a given key.
	 */
	QueryConstraint doesNotExist(String key);

	/**
	 * Add a constraint that requires that a key's value not match a value in an object returned by a different Parse.Query.
	 */
	QueryConstraint doesNotMatchKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint);

	/**
	 * Add a constraint that requires that a key's value not matches a Parse.Query constraint.
	 */
	QueryConstraint doesNotMatchQuery(String key, QueryConstraint queryConstraint);

	/**
	 * Add a constraint for finding string values that end with a provided string.
	 */
	QueryConstraint endsWith(String key, String suffix);

	/**
	 * Add a constraint to the query that requires a particular key's value to be equal to the provided value.
	 */
	QueryConstraint equalTo(String key, Object value);

	/**
	 * Add a constraint for finding objects that contain the given key.
	 */
	QueryConstraint exists(String key);

	/**
	 * Add a constraint to the query that requires a particular key's value to be greater than the provided value.
	 */
	QueryConstraint greaterThan(String key, Object value);

	/**
	 * Add a constraint to the query that requires a particular key's value to be greater than or equal to the provided value.
	 */
	QueryConstraint greaterThanOrEqualTo(String key, Object value);

	/**
	 * Add a constraint to the query that requires a particular key's value to be less than the provided value.
	 */
	QueryConstraint lessThan(String key, Object value);

	/**
	 * Add a constraint to the query that requires a particular key's value to be less than or equal to the provided value.
	 */
	QueryConstraint lessThanOrEqualTo(String key, Object value);

	/**
	 * Add a regular expression constraint for finding string values that match the provided regular expression.
	 */
	QueryConstraint matches(String key, String regex);

	/**
	 * Add a regular expression constraint for finding string values that match the provided regular expression.
	 */
	QueryConstraint matches(String key, String regex, String modifiers);

	/**
	 * Add a constraint that requires that a key's value matches a value in an object returned by a different Parse.Query.
	 */
	QueryConstraint matchesKeyInQuery(String key, String queryKey, QueryConstraint queryConstraint);

	/**
	 * Add a constraint that requires that a key's value matches a Parse.Query constraint.
	 */
	QueryConstraint matchesQuery(String key, QueryConstraint queryConstraint);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given.
	 */
	QueryConstraint near(String key, ParseGeoPoint point);

	/**
	 * Add a constraint to the query that requires a particular key's value to not be contained in the provided list of values.
	 */
	QueryConstraint notContainedIn(String key, Object... values);

	/**
	 * Add a constraint to the query that requires a particular key's value to be not equal to the provided value.
	 */
	QueryConstraint notEqualTo(String key, Object value);

	/**
	 * Add a constraint for finding string values that start with a provided string.
	 */
	QueryConstraint startsWith(String key, String prefix);

	/**
	 * Add a constraint to the query that requires a particular key's coordinates be contained within a given rectangular geographic bounding box.
	 */
	QueryConstraint withinGeoBox(String key, ParseGeoPoint southwest, ParseGeoPoint northeast);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given and within the maximum distance given.
	 */
	QueryConstraint withinKilometers(String key, ParseGeoPoint point, double maxDistance);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given and within the maximum distance given.
	 */
	QueryConstraint withinMiles(String key, ParseGeoPoint point, double maxDistance);

	/**
	 * Add a proximity based constraint for finding objects with key point values near the point given and within the maximum distance given.
	 */
	QueryConstraint withinRadians(String key, ParseGeoPoint point, double maxDistance);

}
