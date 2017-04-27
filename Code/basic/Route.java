/**
 * 
 */
package pso.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 * This class is to generate a Route.
 */
public class Route {
	
	protected int id;
	// int seed = 49;
	protected int num = 50; // 50 states
	
	private int startIndex = 0; // start: Boston 
	private int endIndex = 0; // end: Boston
	
	protected int totalDistance = 0;
	public int getTotalDistance() {
		return totalDistance;
	}
	public void setTotalDistance(int total) { this.totalDistance = total;}

	protected ArrayList<City> route = new ArrayList<>();
	public ArrayList<City> getRoute() {
		return route;
	}

	public Route(int index, Cities cities) {
		
		this.id = index;
		// get #Cities in one route
		this.num = cities.getNum();
		
		// generate a route
		generateRoute(cities);
		
		// calculate totalDistance of this route
		calculateRoute();
		
	}
	
	// copy constructors
	public Route(Route r) {
		
		this.id = r.id;
		this.num = r.num;
		this.totalDistance = r.totalDistance;
		
		// copy route
		for (City c : r.route) this.route.add(c);
		
	}
	
	// for test the best Route
	public Route(Cities cities, ArrayList<Integer> arr) {
		
		this.num = cities.getNum();
		
		for (int i = 0; i < arr.size(); i++) {
			// routeOrder[i] = i;
			route.add(cities.getCity(arr.get(i)));
			
			// test for route generation
			// System.out.println(route.get(i));
		}
		
		this.calculateRoute();
		
	}
	
	// Randomly generate a route:
	// Starting from Boston, and go through all cities(states).
	// Finally go back to Boston.
	private void generateRoute(Cities cities) {
		
		// a travel route of city index
		// int[] routeOrder = new int[num];
		for (int i = 1; i < num; i++) {
			// routeOrder[i] = i;
			route.add(cities.getCity(i));
			
			// test for route generation
			// System.out.println(route.get(i));
		}
		
		// randomly shuffle the order to get a random route 
		// Random rnd = new Random(seed);
		// Collections.shuffle(route, rnd);
		Collections.shuffle(route);
			
	}
	
	public void calculateRoute() {
		
		// the Route not include start point and end point
		// Both of them are Boston.
		// Calculating route distance should add them up.
		totalDistance = 0;
		totalDistance += route.get(0).distanceFromCity(startIndex); 
		// System.out.println(route.get(0).distanceFromCity(startIndex));
		
		for (int i = 1; i < route.size(); i++) {
			totalDistance += route.get(i).distanceFromCity(route.get(i-1).getIndex());
			// System.out.println(route.get(i).distanceFromCity(route.get(i-1).getIndex()));
		}
		
		totalDistance += route.get(route.size()-1).distanceFromCity(endIndex);
		// System.out.println(route.get(route.size()-1).distanceFromCity(endIndex));
	}
	
	
	// calculate the distance between two cities in one route.
	public int distanceBetween(int start, int end) {
		
		int distance = 0;
		
		if (end == start) return 0;
		
		if (end < start) {
			int tmp = start;
			start = end;
			end = tmp;
		}
			
		// add up distances between all cities within these two cities in one route 
		for (int i = start; i<end; i++) {
			distance+= route.get(i).distanceFromCity(route.get(i+1).getIndex());
		}

		return distance;
		
	}
	
	public void displayRoute() {

		System.out.println("Route " + id + ": ");
		
		// print out ID
		for (City c : route) {
			System.out.print(c.getIndex() + " ");
		}
		System.out.println();
		
		// print out City Name
		for (City c : route) {
			System.out.print(c + " ");
		}
		System.out.println();
		
	}

	public static void main(String args[]) {
		
		Cities cities = new Cities(6, "CitiesDemo.txt");
		Route route = new Route(0, cities);
		
		// test index in Route
		// cities.displayDistanceForCity(0);
		/*System.out.println();
		for (City c: cities.getAll()) { // display distance from all cities to Boston
			System.out.println("From " + c + ": " + c.distanceFromCity(0));
		}*/
		
		// display all cities's information
		for (City c: cities.getAll()) {
			cities.displayDistanceForCity(c.getIndex());
		}
		
		System.out.println();
		route.displayRoute();
		
		// test totalDistance calculation 
		System.out.println("TotalDistance: " + route.totalDistance);
		
		// test Distance between two cities
		System.out.println("Distance between 0 to 4: ");
		System.out.println(route.distanceBetween(0,4));
		
		
	}
	
}
