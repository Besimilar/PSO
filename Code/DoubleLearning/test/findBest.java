/**
 * 
 */
package pso.test;

import java.util.ArrayList;
import java.util.Collections;

import pso.basic.Cities;
import pso.basic.Route;

/**
 * Test Class
 * Modified from Permute() 
 * http://stackoverflow.com/questions/2920315/permutation-of-array
 */
public class findBest{
	
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>(); 
	
    static void permute(ArrayList<Integer> arr, int k){
    	
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            permute(arr, k+1);
            Collections.swap(arr, k, i);
        }
        
        if (k == arr.size() -1){
            
        		ArrayList<Integer> one = new ArrayList<>();
            for (int i = 0; i<arr.size(); i++) {
            		// System.out.print(arr.get(i));
            		one.add(arr.get(i));
            }
            list.add(one);
        }
    }
    
    public static void main(String[] args){
    	
    		int n = 20; // how many cities (not include Boston)
    		String fileName = "CitiesDemo-20.txt";
    		
    		ArrayList<Integer> raw = new ArrayList<>();
    		for (int i = 1; i<=n; i++) raw.add(i);
        
    		findBest.permute(raw, 0);
        
        /*for (ArrayList<Integer> arr : list) {
        		for (Integer num : arr) {
        			 System.out.print(num);
        		}
        		System.out.println();
        }*/
    		
    		// find Best Route
    		Route best = null;
    		int distanceForBest = Integer.MAX_VALUE;
    		
    		Cities cities = new Cities(n+1, fileName);
    		for (ArrayList<Integer> arr : list) {
    			Route r = new Route(cities, arr);
    			
    			if (distanceForBest > r.getTotalDistance()) {
    				distanceForBest = r.getTotalDistance();
    				best = r;
    			}
    		}
    		
    		System.out.println("Best Route: ");
    		best.displayRoute();
    		System.out.println("Total Distance: " + distanceForBest);
    		
    		
    }
}
