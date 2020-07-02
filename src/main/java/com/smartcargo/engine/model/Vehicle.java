package com.smartcargo.engine.model;

public class Vehicle {

	private String _id;
	private VehicleType vehicle_type;


	public VehicleType getVehicle_type() {
		return vehicle_type;
	}

	public void setVehicle_type(VehicleType vehicle_type) {
		this.vehicle_type = vehicle_type;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		this._id = id;
	}

	@Override
	public String toString() {
		return "Vehicle [_id=" + _id + ", vehicle_type=" + vehicle_type + "]";
	}

}

class VehicleType {
	
	private Capacity capacity;

	public Capacity getCapacity() {
		return capacity;
	}

	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "VehicleType [capacity=" + capacity + "]";
	}

}

class Capacity {

	private double volume;
	private double max_load;

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getMax_load() {
		return max_load;
	}

	public void setMax_load(double max_load) {
		this.max_load = max_load;
	}

	@Override
	public String toString() {
		return "Capacity [volume=" + volume + ", max_load=" + max_load + "]";
	}

}
