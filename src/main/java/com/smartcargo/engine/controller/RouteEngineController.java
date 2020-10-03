package com.smartcargo.engine.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcargo.engine.model.ClusteredOrders;
import com.smartcargo.engine.model.EngineParams;
import com.smartcargo.engine.model.Route;
import com.smartcargo.engine.service.ClusterService;
import com.smartcargo.engine.service.RouteService;

import exception.ClusterException;

@RequestMapping
@RestController
public class RouteEngineController {

	@Autowired
	private ClusterService clusterService;
	@Autowired
	private RouteService routeService;

	@PostMapping("/make-cluster")
	public ResponseEntity<Object> makeCluster(@RequestBody EngineParams parameters) throws InterruptedException {
		ArrayList<Route> schdule = new ArrayList<Route>();
		try {
			schdule = clusterService.generateCluster(parameters);
		} catch (ClusterException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(schdule, HttpStatus.OK);
	}

	@PostMapping("/generate-route")
	public ResponseEntity<Object> generateRoute(@RequestBody ClusteredOrders orders) throws InterruptedException {

		ArrayList<String> generatedRoute = new ArrayList<String>();
		generatedRoute = routeService.formRoute(orders);
		return new ResponseEntity<>(generatedRoute, HttpStatus.OK);
	};

}
