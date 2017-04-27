/**
 * 
 */
package pso.test;

import pso.core.PSODriver;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 * Find best w, c1, c2
 *
 */
public class findParameters {

	
	public static void main(String args[]) {
		
		int bestDistance = Integer.MAX_VALUE;
		double[] params = new double[3];
		
		for (int i = 1; i<10; i++) {
			
			for (int j = 1; j<10; j++) {
				
				for (int k = 1; k<10; k++) {
					
					int distance = 0;			
					for (int n = 0; n<10; n++) {
						PSODriver job = new PSODriver(50, "Cities.txt", 50, 1000,
								i*0.1, j*0.1, k*0.1);
						distance += job.swarm.gBest.getTotalDistance();
					}
					distance = distance / 10;
					if (bestDistance > distance) {
						bestDistance = distance;
						params[0] = i*0.1;
						params[1] = j*0.1;
						params[2] = k*0.1;
					}
				}
			}
			
		}
		
		System.out.println("Shortest Distance: " + bestDistance);
		System.out.println("Best Parameters: " + params[0] + 
				"-" + params[1] + "-" + params[2]);
		
	}
	
	
	
}
