package ca.pjer.parseclient;

public class ParseGeoPoint {

	private final double latitude;
	private final double longitude;

	public ParseGeoPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
}
