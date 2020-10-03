package com.smartcargo.engine.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.smartcargo.engine.model.ClusteredOrders;
import com.smartcargo.engine.model.GeneticAlgorithm;
import com.smartcargo.engine.model.Population;

@Service
public class RouteService {
	
	// this function returns ordered(routed) list of _ids
	public ArrayList<String> formRoute(ClusteredOrders orders) {
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE, orders.getOrders());
		population.sortRoutesByFitness();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(orders.getOrders());
		int generationNumber = 0;
		while (generationNumber < GeneticAlgorithm.NUMB_OF_GENERATIONS) {
			population = geneticAlgorithm.evolve(population);
			population.sortRoutesByFitness();
			generationNumber++;

		}
		ArrayList<String> generatedRoute = new ArrayList<String>();
		population.getRoutes().get(0).getOrders().forEach(x -> generatedRoute.add(x.get_id()));
		/*
		 * System.out.println(""); System.out.println("Best Route Found so far: " +
		 * generatedRoute); System.out.println("w/ a distance of: " +
		 * String.format("%.2f", population.getRoutes().get(0).calculateTotalDistance())
		 * + " miles");
		 */
		
		return generatedRoute;
	}
}
