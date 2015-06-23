package ca.pjer.parseclient;

public class ParseFile {

	private String name;
	private String url;

	public ParseFile() {
	}

	public ParseFile(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
	}
}
