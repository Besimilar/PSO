/**
 * 
 */
package pso.core;

import java.util.ArrayList;

import pso.basic.Cities;
import tool.Stopwatch;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class Swarm {
	
	// test for log
	// private static final Logger log = Logger.getLogger(Swarm.class.getName());
	
	public static int num = 100; // #Particles
	public static int ePochs = 5000;
	
	public static double w = 0.4; // inertia
	public static double c1 = 0.9; // learning rate c1 for cognition
	public static double c2 = 0.05; // learning rate c2 for social
	
	public Particle gBest;
	// Particle[] swarm;
	ArrayList<Particle> swarm; // for parallel
	
	public static boolean isParallel = true; 
	
	public Swarm(int num, Cities cities, int ePochs) {
		
		Swarm.num = num;
		Swarm.ePochs = ePochs;
		
		// generateSwarm
		generateSwarm(cities);
		
		// start to explore
		// iterate #ePochs times
		for (int i = 0; i < Swarm.ePochs; i++)	{
			//System.out.println("#Exploration: " + i + "...");
			//System.out.println();
			// log.log(Level.INFO,"Exploration: " + i + "...");
			explore();
		}
			
	}
	
	public Swarm(int num, Cities cities, int ePochs,
			double w, double c1, double c2) {
		
		Swarm.num = num;
		Swarm.ePochs = ePochs;
		Swarm.w = w;
		Swarm.c1 = c1;
		Swarm.c2 = c2;
		
		// generateSwarm
		generateSwarm(cities);
		
		// start to explore
		// iterate #ePochs times
		for (int i = 0; i < Swarm.ePochs; i++)	{
			// System.out.println("#Exploration: " + i + "...");
			// System.out.println();
			// log.log(Level.INFO,"Exploration: " + i + "...");
			explore();
		}
		
	}
	
	public Swarm(Cities cities) {
		
		// generateSwarm
		generateSwarm(cities);
				
		// start to explore
		// iterate #ePochs times
		for (int i = 0; i < Swarm.ePochs; i++) {
			//System.out.println("#Exploration: " + i + "...");
			//System.out.println();
			explore();
		}
		
	}
	
	//
	private void generateSwarm(Cities cities) {
		
		// swarm = new Particle[num];
		swarm = new ArrayList<Particle>(num);
		
		// ###### Parallel Later ######
		// System.out.println("Creating Particles...");
		// System.out.println();
		
		// Create # Particles (Routes)
		for (int i = 0; i < num; i++) {
			// swarm[i] = new Particle(i, cities);
			swarm.add(i, new Particle(i,cities));
		}
		
		updateBest();
		
		// System.out.println("Swarm Created!");
		// System.out.println();
	}
	
	// Swarm starts to iterate
	// Particles try to move and learn from others
	public void explore() {
		 
		// ###### Parallel Later ######
		// https://docs.oracle.com/javase/tutorial/collections/streams/parallelism.html
		
		if (isParallel) {
			// Parallel exploration
			swarm.parallelStream().forEach(p -> {
				p.explore(gBest, w, c1, c2);
			});
		
			//System.out.println("Parallel...");
		}
		else {
			for (Particle p : swarm) {
				// learn from pBest and gBest
				p.explore(gBest, w, c1, c2);
			}
			//System.out.println("Not Parallel...");
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
		
		Cities cities = new Cities(48, "Cities-clean.txt");
		
		// test for parallel
		Stopwatch time = new Stopwatch();
		Swarm swarm = new Swarm(100, cities, 5000);
		System.out.println("Time: " + time.elapsedTime());
		
		// test for displaying swarm
		swarm.displaySwarm();
		
		// test for displaying gBest
		swarm.displayBest();
	}

}
