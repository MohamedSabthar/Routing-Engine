package com.smartcargo.engine.model;

public class Capacity {

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