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
 * (name, state, longitude, latitude, etc.): 
 * also stores the distances from this city to all other cities.
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
	
	// Coordinated are used in drawing map
	private double x; // x coordinate
	private double y; // y coordinate
	
	public void setX(double x) {this.x = x;}
	public void setY(double y) {this.y = y;}
	public double getX() {return x;}
	public double getY() {return y;}
		
	// distances between any city and this city
	// index - distances
	private ArrayList<Integer> distances = new ArrayList<>();

	// Class constructor
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
	
