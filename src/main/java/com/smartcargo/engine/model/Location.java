package com.smartcargo.engine.model;

public class Location {
	
	private double lat;
	private double lang;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLang() {
		return lang;
	}

	public void setLang(double lang) {
		this.lang = lang;
	}

	@Override
	public String toString() {
		return "Location [lat=" + lat + ", lang=" + lang + "]";
	}
	
}
