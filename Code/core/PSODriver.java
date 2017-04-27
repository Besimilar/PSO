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
	
	Swarm swarm;

	public PSODriver(int num, String fileName, int numParticles, int ePochs) {
		
		// load cities
		Cities cities = new Cities(num, fileName);
				
		// create swarm
		swarm = new Swarm(numParticles, cities, ePochs);
		
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
		PSODriver job = new PSODriver(21, "CitiesDemo-20.txt", 10, 10);
		
		job.run();
	}
}
