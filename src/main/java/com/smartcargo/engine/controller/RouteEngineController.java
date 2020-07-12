package com.smartcargo.engine.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcargo.engine.model.EngineParams;
import com.smartcargo.engine.model.Route;
import com.smartcargo.engine.service.RoutingService;

@RequestMapping
@RestController
public class RouteEngineController {

	@PostMapping("/make-cluster")
	public ArrayList<Route> test(@RequestBody EngineParams parameters) throws InterruptedException {

		RoutingService.calculateDistanceFromDepot(parameters.getOrders(), parameters.getDepot());
		System.out.println(parameters.getOrders());

		ArrayList<Route> schdule = new ArrayList<Route>();
		Route r = new Route();
		Route r1 = new Route();
		r.addOrder("0");
		r1.addOrder("1");
		r.setVehicle("vehicle one");
//		schdule.remove(o);

		schdule.add(r);
		schdule.add(r1);

		schdule.remove(r1);

		System.out.println(schdule);
//		TimeUnit.SECONDS.sleep(4*60);
		return schdule;
	}

}
