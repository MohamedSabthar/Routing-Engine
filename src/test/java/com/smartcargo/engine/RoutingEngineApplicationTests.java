package com.smartcargo.engine;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.smartcargo.engine.model.Capacity;
import com.smartcargo.engine.model.Location;
import com.smartcargo.engine.model.Order;
import com.smartcargo.engine.model.Vehicle;
import com.smartcargo.engine.model.VehicleType;
import com.smartcargo.engine.service.RoutingService;


@SpringBootTest
class RoutingEngineApplicationTests {
	
	@Test
	void sortVehicleByVolumeTest() {
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
	void sortOrdersByDistanceTest() {
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
	
	
	@Test 
	void removeVehicleFromArrayListTest() {
		Vehicle one = new Vehicle();
		one.set_id("1");
		
		Vehicle two = new Vehicle();
		two.set_id("2");
		
		Vehicle three = new Vehicle();
		three.set_id("3");
		
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		vehicles.add(one);
		vehicles.add(two);
		vehicles.add(three);
		
		Vehicle expectedOutput[] = {one,two,three};
		assertArrayEquals(vehicles.toArray(),expectedOutput);
		
		RoutingService.removeVehicle(vehicles, two);
		expectedOutput = new Vehicle[2];
		expectedOutput[0] = one;
		expectedOutput[1] = three;
		assertArrayEquals(vehicles.toArray(),expectedOutput);
	}
	
	
	@Test
	void nearestOrderTest() {
		Order currentOrder = new Order();
		currentOrder.set_id("kolonnawa");
		Location currentOrderLocation = new Location();
		currentOrderLocation.setLang(79.892230);
		currentOrderLocation.setLat(6.930586);
		currentOrder.setLocation(currentOrderLocation);
		
		Order one = new Order();
		one.set_id("wellampitiya");
		Location locOne = new Location();
		locOne.setLang(79.896879);  
		locOne.setLat(6.937293);
		one.setLocation(locOne);
		
		
		Order two = new Order();
		two.set_id("ucsc");
		Location locTwo= new Location();
		locTwo.setLang(79.861107); 
		locTwo.setLat(6.902425 );
		two.setLocation(locTwo);
		
		Order three = new Order();
		three.set_id("dehiwala zoo");
		Location locThree = new Location();
		locThree.setLang(79.874434);
		locThree.setLat(6.857130 );
		three.setLocation(locThree);
		
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(three);
		orders.add(one);
		orders.add(two);
				
		
		Order expected = RoutingService.nearestOrder(orders, currentOrder);
		Order actual = one;
		
		assertEquals(expected, actual);
		
		RoutingService.removeOrder(orders, actual); //should return wellampitiya object
		
		actual = two;
		expected = RoutingService.nearestOrder(orders, currentOrder); 
		RoutingService.removeOrder(orders, actual);		//should return ucsc object
		
		

	}

}
