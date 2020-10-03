package com.smartcargo.engine.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class GeneticAlgorithm {
	
	public static final double MUTATION_RATE = 0.25;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;
	public static final int POPULATION_SIZE = 8;
	public static final int NUMB_OF_ELITE_ROUTES = 1;
	public static final int NUMB_OF_GENERATIONS = 100;
	private ArrayList<Order> initialRoute = null;
	public GeneticAlgorithm(ArrayList<Order> initialRoute) {this.initialRoute = initialRoute;}
	public ArrayList<Order> getInitialRoute(){return initialRoute;}
	
	public Population evolve(Population population) {return mutatePopulation(crossoverPopulation(population));}

	Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getRoutes().size(),this);
		IntStream.range(0, NUMB_OF_ELITE_ROUTES).forEach(x -> crossoverPopulation.getRoutes().set(x, population.getRoutes().get(x)));;
		IntStream.range(NUMB_OF_ELITE_ROUTES, crossoverPopulation.getRoutes().size()).forEach(x -> {
		Chromosome route1 = selectTournamentPopulation(population).getRoutes().get(0);
		Chromosome route2 = selectTournamentPopulation(population).getRoutes().get(0);
		crossoverPopulation.getRoutes().set(x, crossoverRoute(route1, route2));
		});
		return crossoverPopulation;
	}
	
	
	Population mutatePopulation(Population population) {
		population.getRoutes().stream().filter(x -> population.getRoutes().indexOf(x) >= NUMB_OF_ELITE_ROUTES).forEach(x-> mutateRoute(x));
		return population;
	}
	//
	Chromosome crossoverRoute(Chromosome route1, Chromosome route2) {
		Chromosome crossoverRoute = new Chromosome(this);
		Chromosome tempRoute1 = route1;
		Chromosome tempRoute2 = route2;
		if(Math.random() < 0.5) {
			tempRoute1 = route2;
			tempRoute2 = route1;
		}
		for(int x = 0; x < crossoverRoute.getOrders().size()/2;x++)
			crossoverRoute.getOrders().set(x, tempRoute1.getOrders().get(x));
		
		return fillNullsInCrossoverRoute(crossoverRoute, tempRoute2);
	}
	
	
	private Chromosome fillNullsInCrossoverRoute(Chromosome crossoverRoute, Chromosome route) {
		route.getOrders().stream().filter(x -> !crossoverRoute.getOrders().contains(x)).forEach(cityX -> {
			for(int y = 0;y < route.getOrders().size();y++) {
				if(crossoverRoute.getOrders().get(y) == null) {
					crossoverRoute.getOrders().set(y, cityX);
					break;
				}
			}
		});
		
		return crossoverRoute;
	}
	
	Chromosome mutateRoute(Chromosome route) {
		route.getOrders().stream().filter(x -> Math.random() < MUTATION_RATE && route.getOrders().indexOf(x) != 0).forEach(cityX -> {
			int y = (int) (route.getOrders().size() * Math.random());
			//
			//if(route.getCities().indexOf(cityX)!=0 || route.getCities().indexOf(cityX)!=route.getCities().size()-1) {
			if(y!=0) {
			Order cityY = route.getOrders().get(y);
			route.getOrders().set(route.getOrders().indexOf(cityX), cityY);
			route.getOrders().set(y, cityX);
			}
		});
		return route;
	}
	
	Population selectTournamentPopulation(Population population) {
		
		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE,this);
		IntStream.range(0, TOURNAMENT_SELECTION_SIZE).forEach(x -> tournamentPopulation.getRoutes().set(x, 
		population.getRoutes().get((int) (Math.random() * population.getRoutes().size()))));
		tournamentPopulation.sortRoutesByFitness();
		return tournamentPopulation;
	}
}
