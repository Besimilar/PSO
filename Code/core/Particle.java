/**
 * 
 */
package pso.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pso.basic.Cities;
import pso.basic.City;
import pso.basic.Route;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class Particle extends Route {

	Route pBest;
	int valBest;
	int valMax; // Vmax

	/**
	 * @param index
	 * @param cities
	 */
	public Particle(int index, Cities cities) {
		super(index, cities);
		// TODO Auto-generated constructor stub
		
		// save the best Route
		pBest = new Route(this);
		
		valBest = pBest.getTotalDistance();
	}
	
	// copy Constructor
	public Particle(Particle p) {
		
		super(p);
		this.pBest = new Route(p);
		this.valBest = this.pBest.getTotalDistance();
		
	}
	
	// particle explore
	public void explore(Route gBest, double w, double c1, double c2) {
		
		// Self-adjustment
		learnFromSelf(w);
		
		// Learn From pBest
		learnFromExperience(pBest, c1);
		
		// Learn From gBest
		learnFromSocial(gBest, c2);
		
		// after exploration, update pBest
		updateBest();
		
		// for test: is better than Shuffle? Of course
		// Collections.shuffle(this.getRoute());
	}
	
	// Self-adjustment (Cognition Part 1)
	// Inertia
	// 1. w: 60%(default): keep part(w) of Route unchanged
	// 2. find which part is better:
	// 	  a. first half?
	//    b. second half?
	// 3. shuffle the rest
	private void learnFromSelf(double w) {
		
		int numUnchanged = (int) (this.getRoute().size() * w);
		if (numUnchanged < 1) numUnchanged =1;
		// System.out.println(numUnchanged);
		
		int start = 0;
		int end = this.getRoute().size()-1;
		
		// compare total distance between first half and second half
		// distance includes the route linked to start or end point (Boston)
		// which one is better, keep which part unchanged
		// shuffle the other part
		int firstHalf = this.distanceBetween(start, start+numUnchanged-1);
		firstHalf += this.getRoute().get(start).distanceFromCity(0);
		// System.out.println("First: " + firstHalf);
		
		int secondHalf = this.distanceBetween(end-numUnchanged+1, end);
		secondHalf += this.getRoute().get(end).distanceFromCity(0);
		// System.out.println("Second: " + secondHalf);
		
		// ****** whether to apply this to 
		// learnFromExperience and learnFromSocial ???
		if (firstHalf <= secondHalf) {
			Collections.shuffle(this.getRoute().subList(start+numUnchanged, end+1));
		}
		else {
			Collections.shuffle(this.getRoute().subList(start, end-numUnchanged+1));
		}
		
		// update TotalDistance for this route
		this.calculateRoute();
	}
	
	// Learn from one's experience (Coginition Part 2)
	// pBest
	// 1. c1: (1-w)/2: 20%(default): pick part from pBest
	// 2. delete same elements from original route
 	// 3. insert this piece of route to original route
	private void learnFromExperience(Route pBest, double c1) {
		
		// set range for the piece which will be picked
		int numPicked = (int) (pBest.getRoute().size() * c1 + 0.5);
		// System.out.println("numPicked: " + numPicked);
		
		if (numPicked < 1) numPicked = 1;
		int startIndex = (int)(Math.random()*(pBest.getRoute().size()- numPicked + 1));
		// int startIndex = 2;
		// System.out.println("Pick From " + startIndex);
		ArrayList<City> pickedRoute = new ArrayList<>(numPicked);
		
		for (int i = startIndex; i < (startIndex + numPicked); i++) {
			pickedRoute.add(pBest.getRoute().get(i));
			// System.out.println(pBest.getRoute().get(i));
		}
		
		// delete same elements from original route
		for (City c: pickedRoute) {
			if (this.getRoute().contains(c)) this.getRoute().remove(c);
			// System.out.println(c);
		}
		
		// insert pickedRoute back into original route
		for (int i = startIndex, j = 0; i<(startIndex+numPicked); i++, j++) {
			this.getRoute().add(i, pickedRoute.get(j));
		}
		
		// update TotalDistance for this route
		this.calculateRoute();
		
	}
	
	// learn from others' experience (Social)
	// gBest
	// 1. c2: (1-w)/2: 20%(default): pick part from gBest
	// 2. delete same elements from original route
	// 3. insert this piece of route to original route
	private void learnFromSocial(Route gBest, double c2) {
		
		// set range for the piece which will be picked
		int numPicked = (int) (gBest.getRoute().size() * c2 + 0.5);
		// System.out.println("numPicked: " + numPicked);
				
		if (numPicked < 1) numPicked = 1;
		int startIndex = (int)(Math.random()*(gBest.getRoute().size()- numPicked + 1));
		// int startIndex = 2;
		// System.out.println("Pick From " + startIndex);
		ArrayList<City> pickedRoute = new ArrayList<>(numPicked);
		
		for (int i = startIndex; i < (startIndex + numPicked); i++) {
			pickedRoute.add(gBest.getRoute().get(i));
			// System.out.println(gBest.getRoute().get(i));
		}
				
		// delete same elements from original route
		for (City c: pickedRoute) {
			if (this.getRoute().contains(c)) this.getRoute().remove(c);
			// System.out.println(c);
		}
				
		// insert pickedRoute back into original route
		for (int i = startIndex, j = 0; i<(startIndex+numPicked); i++, j++) {
			this.getRoute().add(i, pickedRoute.get(j));
		}
				
		// update TotalDistance for this route
		this.calculateRoute();
		
	}
	
	
	// update pBest after each iteration
	public void updateBest() {
		
		if (this.getTotalDistance() < valBest) {
		
			pBest = new Route(this);
			
			valBest = this.getTotalDistance();
		}
	}
	
	// display current Route
	public void display() {
		super.displayRoute();
		System.out.println("TotalDistance: " + super.getTotalDistance());
		System.out.println();
	}
	
	// for test
	public static void main(String args[]) {
		
		Cities cities = new Cities(6, "CitiesDemo.txt");
		Particle p = new Particle(1, cities);
		p.display();
		
		// test for learnFromSelf()
		/*p.learnFromSelf(0.6);
		p.display();
		System.out.println("pBest: ");
		p.pBest.displayRoute();
		System.out.println();
		
		// test for learnFromExperience()
		System.out.println("learnFromExperience: ");
		p.learnFromExperience(p.pBest, 0.4);
		p.display();*/
		
		// test for copying pBest
		/*p.pBest.displayRoute();
		p.getRoute().remove(0);
		System.out.println("Afer modifed: ");
		p.display();
		System.out.println("pBest: ");
		p.pBest.displayRoute();*/
		
		// test for learnFromSocial()
		Particle gBest = new Particle(2, cities);
		/*p.learnFromSocial(gBest, 0.4);
		System.out.println("gBest: ");
		gBest.display();
		p.display();*/
		
		// test for exploring
		System.out.println("Before Exploration: ");
		System.out.println("pBest: ");
		p.pBest.displayRoute();
		p.explore(gBest, 0.6, 0.4, 0.4);
		System.out.println("After Exploration: ");
		System.out.println("pBest: ");
		p.pBest.displayRoute();
		p.display();
	}

}
