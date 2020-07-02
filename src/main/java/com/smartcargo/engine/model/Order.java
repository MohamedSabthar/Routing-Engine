package com.smartcargo.engine.model;

public class Order {

	private String _id;
	private double load;
	private double volume;
	private Location location;
	private double distance_from_depot; // this field will used to hold the distance from depot

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		this._id = id;
	}

	public double getLoad() {
		return load;
	}

	public void setLoad(double load) {
		this.load = load;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getDistanceFromDepot() {
		return distance_from_depot;
	}

	public void setDistanceFromDepot(double distanceFromDepot) {
		this.distance_from_depot = distanceFromDepot;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Order [_id=" + _id + ", load=" + load + ", volume=" + volume + ", location=" + location
				+ ", distance_from_depot=" + distance_from_depot + "]";
	}

	

}
