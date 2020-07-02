package com.smartcargo.engine.service;

import java.util.ArrayList;

import com.smartcargo.engine.model.Location;
import com.smartcargo.engine.model.Order;

public class RoutingService {

	public static void calculateDistanceFromDepot(ArrayList<Order> orders, Location depot) {

		double distanceFromDepot;
		for (Order order : orders) {
			distanceFromDepot = caculateHarvasine(order.getLocation(), depot);
			// System.out.println(distanceFromDepot);
			order.setDistanceFromDepot(distanceFromDepot);
		}
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
