package org.example;

public class Location {
	private String locationCode;
	private String locationDesc;
	public Location(String locationCode, String locationDesc) {
		super();
		this.locationCode = locationCode;
		this.locationDesc = locationDesc;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getLocationDesc() {
		return locationDesc;
	}
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	
	
}
