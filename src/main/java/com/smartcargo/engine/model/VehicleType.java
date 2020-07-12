package com.smartcargo.engine.model;

public class VehicleType {
	
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