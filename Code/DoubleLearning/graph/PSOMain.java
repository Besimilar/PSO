/**
 * 
 */
package pso.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import pso.basic.Cities;
import pso.basic.City;
import pso.core.PSODriver;
import pso.core.Swarm;
import tool.Stopwatch;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class PSOMain extends JPanel {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	static boolean started = false;

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		Dimension size = this.getSize();
		g.setColor(Color.RED);
		g.drawString("PSO USA MAP!", 10, 15);
		
		// Draw Cities
		// No AK or HI
		
		//System.out.println(size.getWidth());
		for (City c: Cities.getAll()){
			
			int x = (int)(c.getX()*size.getWidth());
			int y = (int)(c.getY()*size.getHeight());
			
			g.setColor(Color.WHITE);
			g.drawString(c.getStateAbbr(), x, y);
			g.setColor(Color.RED);
			g.drawOval(x, y, 3, 3);
		}
		
		// for test
		if (started) {
			
			//PSODriver job = new PSODriver(48, "Cities-clean.txt", 100, 5000,
			//		0.2, 0.7, 0.1);
			Stopwatch watch = new Stopwatch();
			PSODriver job = new PSODriver(Cities.num, Cities.fileName);
			double time = watch.elapsedTime();
			
			int total = job.swarm.gBest.getRoute().size();
			
			// For Boston
			int x0 = (int)(Cities.getCity(0).getX() * size.getWidth());
			int y0 = (int)(Cities.getCity(0).getY() * size.getHeight());
			
			int x1 = (int)(job.swarm.gBest.getRoute().get(0).getX() * size.getWidth());
			int y1 = (int)(job.swarm.gBest.getRoute().get(0).getY() * size.getHeight());
			g.drawLine(x0, y0, x1, y1);
			
			int x2,y2;
			for (int i = 0; i<total-1; i++) {
				
				x2 = x1;
				y2 = y1;
				
				x1 = (int)(job.swarm.gBest.getRoute().get(i+1).getX() * size.getWidth());
				y1 = (int)(job.swarm.gBest.getRoute().get(i+1).getY() * size.getHeight());
				
				g.drawLine(x2, y2, x1, y1);
			}
			
			g.drawLine(x0, y0, x1, y1);
			// System.out.println(job.swarm.gBest.getTotalDistance());
			String totalDistance = "Shortest Route: " + job.swarm.gBest.getTotalDistance();
			g.setColor(Color.WHITE);
			g.drawString(totalDistance, size.width/2, 15);

			String params = Swarm.w + "-" + Swarm.c1 + "-" + Swarm.c2;
			// System.out.println(Swarm.w + "-" + Swarm.c1 + "-" + Swarm.c2);
			g.drawString(params, size.width/2, 30);

			String ePochs = "Epochs: " + Swarm.ePochs;
			g.drawString(ePochs, size.width/2, 45);
			
			String numParticles = "#Particles: " + Swarm.num;
			g.drawString(numParticles, size.width/2, 60);
			
			// draw running time
			String rTime = "Running Time: " + time + "(" 
					+ Swarm.isParallel + ")";
			g.drawString(rTime, size.width*3/4, 30);
			
			//System.out.println(Swarm.isParallel);
		}
		
	}

}
