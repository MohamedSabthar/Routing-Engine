package com.smartcargo.engine.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.smartcargo.engine.model.Location;
import com.smartcargo.engine.model.Order;
import com.smartcargo.engine.model.Vehicle;

public class RoutingService {

	public static void calculateDistanceFromDepot(ArrayList<Order> orders, Location depot) {

		double distanceFromDepot;
		for (Order order : orders) {
			distanceFromDepot = caculateHarvasine(order.getLocation(), depot);
			// System.out.println(distanceFromDepot);
			order.setDistanceFromDepot(distanceFromDepot);
		}
	}

	public static void sortByFartherstDistance(ArrayList<Order> orders) {
		// compare two order objects using distance field
		Comparator<Order> c = (o1, o2) -> o1.getDistanceFromDepot() > o2.getDistanceFromDepot() ? -1 : 1;
		// sort the orders in descending order of the distant field
		Collections.sort(orders, c);
		return;
	}
	
	public static void sortVehicleByVolume(ArrayList<Vehicle> vehicles) {
		// compare two vehicle objects using volume
		Comparator<Vehicle> c = (c1,c2) -> 
		c1.getVehicle_type().getCapacity().getVolume() > c2.getVehicle_type().getCapacity().getVolume() ? -1 : 1;
		// sort the vehicles in descending order of the volumes field
		Collections.sort(vehicles, c);
		return;
	}
	
	

	/** helper functions and variables **/

	private static final int earthRadius = 6371;

	private static double caculateHarvasine(final Location source, final Location dest) {

		double diffLat = toRad(dest.getLat() - source.getLat());
		double diffLang = toRad(dest.getLang() - source.getLang());
		double a = Math.sin(diffLat / 2) * Math.sin(diffLat / 2) + Math.cos(toRad(dest.getLat()))
				* Math.cos(toRad(source.getLat())) * Math.sin(diffLang / 2) * Math.sin(diffLang / 2);

		return 2 * earthRadius * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	}

	private static double toRad(double d) {
		return (Math.PI / 180) * d;
	}

}
