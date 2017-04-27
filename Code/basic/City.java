/**
 * 
 */
package pso.basic;

import java.util.ArrayList;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 * This class stores information for one City
 * 
 */

public class City {
	
	private int index;
	private String city; // Boston
	private String state; // Massachusetts
	private String stateAbbr; // (MA)
		
	// double longitude; // also X axis: Long/Lon: -71.03
	// double latitude; // also Y axis: Lat: 42.23
	private int longitude; // for storage and computing efficiency
	private int latitude;  // set both Long & Lat to int
		
	// distances between any city and this city
	// index - distances
	// int[] distances = new int[num];
	private ArrayList<Integer> distances = new ArrayList<>();
		
	/*public void setDistances(ArrayList<Integer> distances) {
		this.distances = distances;
	}*/

	// inner Class constructor
	public City(int index, String city, String state, int lat, int lon) {
		this.index = index;
		this.city = city;
		this.state = state;
		this.latitude = lat;
		this.longitude = lon;
			
		// extract abbreviation from states
		int start = state.indexOf("(");
		// int end = state.indexOf(")");
		stateAbbr = this.state.substring(start);
	}
	
	
	// display Distance from this city to all other cities
	/*public void displayDistance() {
		
		// 20 for Boston
		System.out.println("######: " + this.toString());
		
		// display all distances from this other cities to this city
		for (int i = 0; i < distances.size(); i++) {
			System.out.println(index + " - "
					+ this.toString() + ": " + distances.get(i) );
		}
		
	}*/
	
	// return the distance between other cities and this city 
	public int distanceFromCity(int cityIndex) {
		return distances.get(cityIndex);
	}
				
	// print format: City-State
	public String toString() {
		//return city + "(" + stateAbbr + ")";
		return city + stateAbbr;
	}

	public int getIndex() {
		return index;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getStateAbbr() {
		return stateAbbr;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public ArrayList<Integer> getDistances() {
		return distances;
	}
		
		
}
	
