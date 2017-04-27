/**
 * 
 */
package pso.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import pso.basic.Cities;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class Swarm {
	
	// test for log
	private static final Logger log = Logger.getLogger(Swarm.class.getName());
	
	int num = 10; // #Particles
	
	double w = 0.6; // inertia
	double c1 = 0.4; // learning rate c1 for cognition
	double c2 = 0.4; // learning rate c2 for social
	
	Particle gBest;
	Particle[] swarm; 
	
	public Swarm(int num, Cities cities, int ePochs) {
		
		this.num = num;
		
		// generateSwarm
		generateSwarm(cities);
		
		// start to explore
		// iterate #ePochs times
		for (int i = 0; i < ePochs; i++)	{
			System.out.println("#Exploration: " + i + "...");
			System.out.println();
			// log.log(Level.INFO,"Exploration: " + i + "...");
			explore();
		}
		
		
	}
	
	//
	private void generateSwarm(Cities cities) {
		
		swarm = new Particle[num];
		
		// ###### Parallel Later ######
		System.out.println("Creating Particles...");
		System.out.println();
		
		// Create # Particles (Routes)
		for (int i = 0; i < num; i++) {
			swarm[i] = new Particle(i, cities);
		}
		
		updateBest();
		
		System.out.println("Swarm Created!");
		System.out.println();
	}
	
	// Swarm starts to iterate
	// Particles try to move and learn from others
	public void explore() {
		 
		// ###### Parallel Later ######
		for (Particle p : swarm) {

			// learn from pBest and gBest
			p.explore(gBest, w, c1, c2);
		}
		
		// after iteation update gBest
		updateBest();
	}
	
	// update gBest in Swarm
	public void updateBest() {
		
		for (Particle p: swarm) {
			if (gBest == null) gBest = new Particle(p);
			else if (p.valBest < gBest.valBest) {
				gBest = new Particle(p);
			}
		}
	}
	
	// Display the whole swarm (all Routes)
	// Route id
	// Route order
	// City order
	// TotalDistance
	public void displaySwarm() {
		
		System.out.println("Display all Routes in Paricles: ");
		System.out.println();
		
		for (Particle p : swarm) {
			p.display();
		}
	}
	
	// Display gBest
	public void displayBest() {
		System.out.println("Current gBest: " + gBest.valBest);
		System.out.println("Current Best Route: ");
		gBest.display();
	}
	
	public static void main(String args[]) {
		
		Cities cities = new Cities(6, "CitiesDemo.txt");
		Swarm swarm = new Swarm(10, cities, 10);
		
		// test for displaying swarm
		swarm.displaySwarm();
		
		// test for displaying gBest
		swarm.displayBest();
	}

}