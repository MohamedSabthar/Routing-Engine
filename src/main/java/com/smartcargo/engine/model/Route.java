package com.smartcargo.engine.model;

import java.util.ArrayList;

public class Route {
	
	private ArrayList<String> orders; // this field used to store orders id
	private String vehicle; // this field used store the selected vehicle id

	public Route() {
		this.orders = new ArrayList<String>();
	}

	public void addOrder(String id) {
		orders.add(id);
	}

	public ArrayList<String> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<String> orders) {
		this.orders = orders;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Route [orders=" + orders + ", vehicle=" + vehicle + "]";
	}
	
	

}
