package com.smartcargo.engine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.smartcargo.engine.service.RoutingService;

public class Chromosome {

	private boolean isFitnessChanged = true;
	private double fitness = 0;
	
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public Chromosome(GeneticAlgorithm geneticAlgorithm) {
		geneticAlgorithm.getInitialRoute().forEach(x -> orders.add(null));
	}
	
	public Chromosome(ArrayList<Order> orders) {
		
		this.orders.addAll(orders);
		Collections.shuffle(this.orders.subList(1,this.orders.size() - 1));
	}
	
	public ArrayList<Order> getOrders(){
		isFitnessChanged = true;
		return orders;
	}
	public double getFitness() {
		if(isFitnessChanged == true) {
			fitness  = (1/calculateTotalDistance())*10000;
			isFitnessChanged = false;
		}
		return fitness;
	}
	
	public double calculateTotalDistance() {
		int ordersSize = this.orders.size();
		return (int) (this.orders.stream().mapToDouble(x -> {
			int orderIndex = this.orders.indexOf(x);
			double returnValue = 0;
			if(orderIndex < ordersSize - 1) returnValue = RoutingService.caculateHarvasine(x.getLocation(), this.orders.get(orderIndex + 1).getLocation());
//			if(orderIndex < ordersSize - 1) returnValue = x.measureDistance(this.cities.get(cityIndex + 1));
			return returnValue;
		}).sum() + RoutingService.caculateHarvasine(this.orders.get(0).getLocation(), this.orders.get(ordersSize - 1).getLocation()));
//	}).sum() + this.orders.get(0).measureDistance(this.cities.get(citiesSize - 1)));
	}
	
	public String toString() { return Arrays.toString(orders.toArray());}
}
