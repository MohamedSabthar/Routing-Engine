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
	
	public double getVolume() {
		return vehicle_type.getCapacity().getVolume();
	}
	
	public double getLoad() {
		return vehicle_type.getCapacity().getMax_load();
	}
	
	public void setVolume(double volume) {
		vehicle_type.getCapacity().setVolume(volume);
	}
	
	public void setLoad(double load) {
		vehicle_type.getCapacity().setMax_load(load);
	}

}


