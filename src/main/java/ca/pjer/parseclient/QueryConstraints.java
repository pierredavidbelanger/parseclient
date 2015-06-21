package ca.pjer.parseclient;

public abstract class QueryConstraints {

	QueryConstraints() {
	}

	public static QueryConstraint where() {
		return new QueryConstraintImpl();
	}

	public static QueryConstraint or(QueryConstraint... queryConstraints) {
		return new QueryConstraintImpl(queryConstraints);
	}
}
