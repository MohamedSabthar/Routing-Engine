package com.smartcargo.engine;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartcargo.engine.model.Capacity;
import com.smartcargo.engine.model.Order;
import com.smartcargo.engine.model.Vehicle;
import com.smartcargo.engine.model.VehicleType;
import com.smartcargo.engine.service.RoutingService;

@SpringBootTest
class RoutingEngineApplicationTests {
	
	@Test
	void sortVehicleByVolume() {
		Vehicle vOne = new Vehicle();
		vOne.setVehicle_type(new VehicleType());
		vOne.getVehicle_type().setCapacity(new Capacity());
		vOne.getVehicle_type().getCapacity().setVolume(320);
		vOne.set_id("1");
		
		Vehicle vTwo = new Vehicle();
		vTwo.setVehicle_type(new VehicleType());
		vTwo.getVehicle_type().setCapacity(new Capacity());
		vTwo.getVehicle_type().getCapacity().setVolume(220);
		vTwo.set_id("2");
		
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(vOne);
		vehicles.add(vTwo);
		
		RoutingService.sortVehicleByVolume(vehicles);
		
		Vehicle expectedOutput[] = {vOne,vTwo};
		
		assertArrayEquals(vehicles.toArray(), expectedOutput);
	}
	
	@Test
	void sortOrdersByDistance() {
		Order one = new Order();
		one.setDistanceFromDepot(100);
		one.set_id("1");
		
		Order two = new Order();
		two.setDistanceFromDepot(200);
		two.set_id("2");
		
		Order three = new Order();
		three.setDistanceFromDepot(150);
		three.set_id("3");
		
		Order four = new Order();
		four.setDistanceFromDepot(50);
		four.set_id("4");
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(one);
		orders.add(two);
		orders.add(three);
		orders.add(four);		
		
		RoutingService.sortByFartherstDistance(orders);
		
		Order expectedOutput[] = {two,three,one,four};

		assertArrayEquals(orders.toArray(), expectedOutput);
	}

}
