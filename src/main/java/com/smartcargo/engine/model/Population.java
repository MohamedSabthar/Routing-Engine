package com.smartcargo.engine.model;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

	private ArrayList<Chromosome> routes = new ArrayList<Chromosome>(GeneticAlgorithm.POPULATION_SIZE);
	public ArrayList<Chromosome> getRoutes(){ return routes;}
	
	public Population(int populationSize, GeneticAlgorithm geneticAlgorithm) {
		IntStream.range(0, populationSize).forEach(x -> routes.add(new Chromosome(geneticAlgorithm.getInitialRoute())));
	}
	
	public Population(int populationSize, ArrayList<Order> orders) {
		IntStream.range(0, populationSize).forEach(x -> routes.add(new Chromosome(orders)));
	}
	public void sortRoutesByFitness() {
		routes.sort((route1, route2) -> {
			int flag = 0;
			if(route1.getFitness() > route2.getFitness()) flag = -1;
			else if (route1.getFitness() < route2.getFitness()) flag = 1;
			return flag;
		});
	}
}
