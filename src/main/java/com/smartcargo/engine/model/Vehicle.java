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


