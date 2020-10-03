package com.smartcargo.engine.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.smartcargo.engine.model.EngineParams;
import com.smartcargo.engine.model.Location;
import com.smartcargo.engine.model.Order;
import com.smartcargo.engine.model.Route;
import com.smartcargo.engine.model.Vehicle;

import exception.ClusterException;

@Service
public class ClusterService {
	
	//this function returns list of clusters , each cluster will have list of _ids
	public ArrayList<Route> generateCluster(EngineParams parameters) throws ClusterException {
		ArrayList<Order> orders = parameters.getOrders();
		ArrayList<Vehicle> vehicles = parameters.getVehicles();
		Location depot = parameters.getDepot();

		if (depot == null || depot.getLang() == 0 && depot.getLat() == 0)
			throw new ClusterException();
		if (vehicles == null || vehicles.size() == 0)
			throw new ClusterException();
		if (orders == null || orders.size() == 0)
			throw new ClusterException();

		// calculate all orders distance from depot
		CommonService.calculateDistanceFromDepot(orders, depot);
		/*
		 * System.out.println(orders);
		 */
		// sort all the orders in descending order of distance from depot
		CommonService.sortByFartherstDistance(orders);
		/*
		 * System.out.println(orders);
		 */
		// sort all the vehicles in descending order of volume
		CommonService.sortVehicleByVolume(vehicles);
		/*
		 * System.out.println(vehicles);
		 */
		// creating a schedule to store Routes/cluster
		ArrayList<Route> schdule = new ArrayList<Route>();

		int cluster = 0;

		Order farthersOrder, currentOrder;
		Vehicle currentVehicle, tempVehicle = null;

		double totalClusterVolume = 0, totalClusterLoad = 0;

		// need to check & remove orders where load/volume greater than maximum capacity
		// vehicle
		/*
		 * System.out.println("\n\tRunning Make cluster.......\n\n");
		 */
		main: while (vehicles.size() > 0 && orders.size() > 0) {
			/*
			 * System.out.println("\t Cluster : " + cluster);
			 */
			farthersOrder = orders.get(0); // get the farthest order from the orders
			currentVehicle = vehicles.get(0); // get the largest vehicle

			currentOrder = farthersOrder;
			tempVehicle = currentVehicle;

			Route route = new Route();
			schdule.add(route); // add a new route (cluster) to the schedule

			schdule.get(cluster).setVehicle(currentVehicle.get_id()); // assign the vehicle to the cluster
			CommonService.removeVehicle(vehicles, currentVehicle); // remove the assigned vehicle from the vehicle list

			totalClusterVolume = 0;
			totalClusterLoad = 0; // initialize the cluster load and volume to zero

			while (currentVehicle.getLoad() > 0 && currentVehicle.getVolume() > 0) {

				double reducedVehicleLoad = currentVehicle.getLoad() - currentOrder.getLoad();
				double reducedVehicleVolume = currentVehicle.getVolume() - currentOrder.getVolume();
				/*
				 * System.out.println("**********************");
				 * System.out.println(currentVehicle); System.out.println(currentOrder);
				 * System.out.println("reducedLoad : " + reducedVehicleLoad +
				 * "\t reducedVolume : " + reducedVehicleVolume);
				 * System.out.println("**********************");
				 */
				if (reducedVehicleLoad < 0 || reducedVehicleVolume < 0)
					break; // if load/volume constrain failed then go for a new cluster

				schdule.get(cluster).addOrder(currentOrder.get_id()); // add the order to the cluster
				currentVehicle.setLoad(reducedVehicleLoad); // update vehicle capacities after adding the order
				currentVehicle.setVolume(reducedVehicleVolume);

				totalClusterLoad += currentOrder.getLoad(); // increase the cluster load & volume by current order
				totalClusterVolume += currentOrder.getVolume();
				/*
				 * System.out.println( "total cluster volume: " + totalClusterVolume +
				 * "\t total cluster load : " + totalClusterLoad);
				 * System.out.println("**********************\n");
				 */
				CommonService.removeOrder(orders, currentOrder); // remove the order (which is added to the cluster)

				if (orders.size() <= 0)
					break main; // if all orders scheduled then break out of the main loop

				currentOrder = CommonService.nearestOrder(orders, currentOrder); // get the nearest order to the
																					// current order and assign it as
																					// current order

			}

			cluster++; // increment the cluster

		}

		if (totalClusterVolume < tempVehicle.getVolume() && vehicles.size() > 0) {
			int index = vehicles.size() - 1; // get the minimum volume vehicles index from list
			Vehicle minCapacityVehicle = vehicles.get(index);

			while (totalClusterVolume > minCapacityVehicle.getVolume()
					|| totalClusterLoad > minCapacityVehicle.getLoad()) {
				/*
				 * System.out.println("**********************");
				 * System.out.println(minCapacityVehicle);
				 * System.out.println("**********************\n");
				 */
				index--;
				if (index < 0)
					break;
				minCapacityVehicle = vehicles.get(index);
			}

			// check the constraints before assign a minimum capacity vehicle (for index==0
			// then need to check the constraints again)
			if (minCapacityVehicle.getLoad() >= totalClusterLoad
					&& minCapacityVehicle.getVolume() >= totalClusterVolume)
				schdule.get(cluster).setVehicle(minCapacityVehicle.get_id());

		}

		return schdule;

	}
}
