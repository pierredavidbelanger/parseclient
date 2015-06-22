package ca.pjer.parseclient;

public class ParseRole extends ParseObject {

	private String name;

	public ParseRole() {
	}

	public ParseRole(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}
}
