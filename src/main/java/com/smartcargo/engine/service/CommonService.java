package com.smartcargo.engine.service;

import java.util.ArrayList;


import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.smartcargo.engine.model.Location;
import com.smartcargo.engine.model.Order;
import com.smartcargo.engine.model.Vehicle;

@Service
public class CommonService {

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
		Comparator<Vehicle> c = (c1,
				c2) -> c1.getVehicle_type().getCapacity().getVolume() > c2.getVehicle_type().getCapacity().getVolume()
						? -1
						: 1;
		// sort the vehicles in descending order of the volumes field
		Collections.sort(vehicles, c);
		return;
	}

	public static void removeVehicle(ArrayList<Vehicle> vehicles, Vehicle vehicle) {
		// remove the vehicle from the arrayList
		vehicles.remove(vehicle);
		return;
	}

	public static void removeOrder(ArrayList<Order> orders, Order order) {
		// remove the given order from the arrayList
		orders.remove(order);
		return;
	}

	public static Order nearestOrder(final ArrayList<Order> orders, final Order currentOrder) {

		double minDistance = caculateHarvasine(currentOrder.getLocation(), orders.get(0).getLocation());
		Order nearestOrder = orders.get(0);
		double distance;
		for (Order i : orders) {
			distance = caculateHarvasine(currentOrder.getLocation(), i.getLocation());
			if (minDistance > distance) {
				minDistance = distance;
				nearestOrder = i;
			}
		}

		return nearestOrder;
	}

	/** helper functions and variables **/

	private static final int earthRadius = 6371;

	public static double caculateHarvasine(final Location source, final Location dest) {

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
