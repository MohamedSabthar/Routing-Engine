package com.smartcargo.engine.model;

import java.util.ArrayList;

/* This class used to map the in-coming JSON parameter into java object*/

public class EngineParams {
	
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Order> orders;
	private Location depot;

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public Location getDepot() {
		return depot;
	}

	public void setDepot(Location depot) {
		this.depot = depot;
	}

	@Override
	public String toString() {
		return "EngineParams [vehicles=" + vehicles + ", orders=" + orders + ", depot=" + depot + "]";
	}

}

/*****************JSON-Model*******************
 
{
    "vehicles": [
        {
            "_id": "5ef4cecb71dca30575d12a75",
            "vehicle_type": {
                "capacity": {
                    "volume": 200,
                    "max_load": 100
                }
            }
        }
    ],
    "orders": [
        {
            "location": {
                "lat": 1.234,
                "long": 4.566
            },
            "_id": "5edfb29a4581798d78e4f77a",
            "volume": 1,
            "load": 20
        }
    ],
    "depot": {
        "lat": 1.2345,
        "lang": 2.903
    }
}


**************************************************/
