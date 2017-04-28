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
public class PSODriver {
	
	public Swarm swarm;

	public PSODriver(int num, String fileName, int numParticles, int ePochs,
			double w, double c1, double c2) {
		
		// load cities
		Cities cities = new Cities(num, fileName);
				
		// create swarm
		// swarm = new Swarm(numParticles, cities, ePochs);
		swarm = new Swarm(numParticles, cities, ePochs, w, c1, c2);
		
	}
	
	public PSODriver(int num, String fileName) {
		
		// load cities
		Cities cities = new Cities(num, fileName);
		
		// use default parameters
		swarm = new Swarm(cities);
		
	}
	
	private void run() {
	
		// test for displaying swarm
		swarm.displaySwarm();
				
		// test for displaying gBest
		swarm.displayBest();
		
	}
	
	public static void main(String args[]) {
		
		// num: num of cities
		// fileName
		// #Particles
		// Epochs
		
		// 0.1 / 0.3 / 0.4(0.3)
		/*PSODriver job = new PSODriver(10, "CitiesDemo-10.txt", 50, 100,
				0.1, 0.3, 0.3);*/
		
		PSODriver job = new PSODriver(48, "Cities-clean.txt", 100, 5000,
				0.1, 0.7, 0.1);
		// 0.2 - 0,3 - 0.5
		// 0.5, 0.7, 0.1
		// 0.5, 0.8, 0.1
		// 0.2, 0.7, 0.1
		// 0.2, 0.7, 0.1
		
		job.run();
	}
}
