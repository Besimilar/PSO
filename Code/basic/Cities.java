/**
 * 
 */
package pso.basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 * This Class "Cities" stores all city information into an Array:
 * The index for Array is the city's id in input file.
 */
public class Cities {

	public static int num = 50; // 50 states
	public static String fileName = "Cities.txt";
	
	// Array<City> to store indexes - cities
	// private ArrayList<City> cities = new ArrayList<>(); 
	private static City[] cities;
	
	public static City getCity(int cityIndex) {
		return cities[cityIndex]; 
	}
	public static City[] getAll () {
		return cities;
	}
	
	// Coordinated are used in Drawing Map
	int east = Integer.MIN_VALUE;
	int west = Integer.MAX_VALUE;
	int north = Integer.MIN_VALUE;
	int south = Integer.MAX_VALUE;
	
	// Constructor for Cities: read all data from "cities.txt"
	public Cities(int num, String fileName) {
		this.num = num;
		this.fileName = fileName;
		cities = new City[num];
		
		readData();
		updataCoordinates();
		calculate(); // calculate distances and store them
	}
	
	// read Data from "cities.txt"
	private void readData() {
		
		// read data from input file
		try {
			FileInputStream fis = new FileInputStream(fileName);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			// read Line by Line (Store all cities into Array-cities)
			for (int i = 0; i<num; i++) {
				String data = br.readLine();
				// input format: 
				// Cities States(stateAbbr) Latitude Longitude
				String[] cityInfo = data.split(" ");
				String city = cityInfo[0];
				String state = cityInfo[1];
				int latitude = (int) Double.parseDouble(cityInfo[2]);
				int longitude = (int) Double.parseDouble(cityInfo[3]);
				
				// save Ranges for Coordinates
				if (longitude < west) west = longitude;
				if (longitude > east) east = longitude;
				if (latitude > north) north = latitude;
				if (latitude < south) south = latitude;
				
				// store cities into cities 
				cities[i] = new City(i, city, state, latitude, longitude);
			}
			
			// after reading data
			br.close();
			isr.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find: " + fileName + "!");
		} catch (IOException e) {
			System.out.println("IOException when processing: " + fileName);
		}
		
	}
	
	/**
	 *  Calculate Coordinated based on loading data
	 *  it's used in Drawing map
	 */
	private void updataCoordinates() {
		// TODO Auto-generated method stub
		
		for (City c : cities) {
			double xLocation = c.getLongitude() - west + 1;
			double yLocation = north - c.getLatitude() + 1;
			
			c.setX(xLocation/(east-west + 5));
			c.setY(yLocation/(north-south + 2));
		}
		
	}
	
	
	// ****** Calculate distances between any two cities(states)
	// for simplifying calculation: use Longitude & latitude of cities as 
	// their coordinates in 2D rather than calculating based on angles and
	// radius of the Earth
	private void calculate() {
		
		 for (City c : cities) {
			 for (int i = 0; i<num; i++) {
				 int x = (c.getLongitude() - cities[i].getLongitude());
				 int y = (c.getLatitude() - cities[i].getLatitude());
				 double sqDistance = Math.pow(x, 2) + Math.pow(y, 2);
				 int distance = (int)(Math.sqrt(sqDistance) + 0.5);
				 c.getDistances().add(i, distance);
			 }
		 }
		 
	}
	
	// display all the cities and their information
	public void displayCities() {
		/*for (int i = 0; i<num; i++) {
			System.out.println(cities[i].index + " - " + cities[i] + ": " + cities[i].longitude
					+ " " + cities[i].latitude);
		}*/
		for (City c: cities) {
			System.out.println(c.getIndex() + " - " + c + ": " + c.getLongitude()
					+ " " + c.getLatitude());
		}
	}
	
	// display Distance Information for one city
	public void displayDistanceForCity(int cityIndex) {
		
		// 20 for Boston
		System.out.println("######: " + getCity(cityIndex).getIndex()
				+ " " + getCity(cityIndex));
		
		// display all distances from other cities to this city
		for (int i = 0; i < cities.length; i++) {
			System.out.println(cities[i].getIndex() + " - "
					+ cities[i] + ": " + getCity(cityIndex).getDistances().get(i) );
		}
		
	}
	
	public int getNum() {
		return num;
	}
	
	// test Code
	public static void main(String args[]) {
	
		// System.out.println(Cities.getCity(20));
		Cities cities = new Cities(48, "Cities-clean.txt");
		// System.out.println(Cities.getCity(20));
		
		// test for City print
		/*City city = .new City("Boston","Massachusetts(MA)", 42.23, -71.03);
		System.out.println(city);
		System.out.println(city.state);
		System.out.println("Long: " + city.longitude);
		System.out.println("Lat: " + city.latitude);
		
		// test for input-Processing
		// cities.displayCities();
		
		// test for distance calculation
		System.out.println("######: " + cities.cities[20]);
		for (int i = 0; i<50; i++) {
			System.out.println(cities.cities[i].index + " - "
					+ cities.cities[i] + ": " + cities.cities[20].distances[i] );
		}*/
		
		// cities.displayDistanceForCity(0);;
		// System.out.println(cities.getCity(0).getDistances().size());
		
		// test for Coordinates Calculation
		cities.displayCities();
		System.out.println("East: " + cities.east);
		System.out.println("West: " + cities.west);
		System.out.println("North: " + cities.north);
		System.out.println("South: " + cities.south);
		
	}
	
	
}


