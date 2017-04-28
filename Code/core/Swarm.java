/**
 * 
 */
package pso.core;

import pso.basic.Cities;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class Swarm {
	
	// test for log
	// private static final Logger log = Logger.getLogger(Swarm.class.getName());
	
	public static int num = 50; // #Particles
	public static int ePochs = 500;
	
	public static double w = 0.6; // inertia
	public static double c1 = 0.4; // learning rate c1 for cognition
	public static double c2 = 0.4; // learning rate c2 for social
	
	public Particle gBest;
	Particle[] swarm; 
	
	public Swarm(int num, Cities cities, int ePochs) {
		
		this.num = num;
		this.ePochs = ePochs;
		
		// generateSwarm
		generateSwarm(cities);
		
		// start to explore
		// iterate #ePochs times
		for (int i = 0; i < this.ePochs; i++)	{
			//System.out.println("#Exploration: " + i + "...");
			//System.out.println();
			// log.log(Level.INFO,"Exploration: " + i + "...");
			explore();
		}
			
	}
	
	public Swarm(int num, Cities cities, int ePochs,
			double w, double c1, double c2) {
		
		this.num = num;
		this.ePochs = ePochs;
		this.w = w;
		this.c1 = c1;
		this.c2 = c2;
		
		// generateSwarm
		generateSwarm(cities);
		
		// start to explore
		// iterate #ePochs times
		for (int i = 0; i < this.ePochs; i++)	{
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
		for (int i = 0; i < this.ePochs; i++) {
			//System.out.println("#Exploration: " + i + "...");
			//System.out.println();
			explore();
		}
		
	}
	
	//
	private void generateSwarm(Cities cities) {
		
		swarm = new Particle[num];
		
		// ###### Parallel Later ######
		// System.out.println("Creating Particles...");
		// System.out.println();
		
		// Create # Particles (Routes)
		for (int i = 0; i < num; i++) {
			swarm[i] = new Particle(i, cities);
		}
		
		updateBest();
		
		// System.out.println("Swarm Created!");
		// System.out.println();
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
