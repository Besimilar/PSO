/**
 * 
 */
package pso.graph;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import pso.basic.Cities;
import pso.core.Particle;
import pso.core.Swarm;

/**
 * @author Hongwei Hu
 * NUID 001677683
 *
 */
public class PSO2D implements ActionListener {
	
	JButton start;
	PSOMain main;
	JFrame frame;
	JTextField w;
	JTextField c1;
	JTextField c2;
	JTextField ePochs;
	JTextField numParticles;
	JComboBox<Boolean> isParallel;
	JComboBox<Boolean> isConverge;
	
	public PSO2D(int num, String fileName) {
		
		Cities cities = new Cities(num, fileName);
		
		// create frame
		frame = new JFrame("PSO");
		frame.setSize(1200, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		main = new PSOMain(); // MainFrame
		JPanel control = new JPanel();
		
		// input Layer
		JPanel input = new JPanel();
		generateInputLayer(input);
		
		// control layer
		start = new JButton("start");
		start.addActionListener(this);
		// control.setBackground(Color.GRAY);
		control.setLayout(new BorderLayout());
		control.add(input, BorderLayout.NORTH);
		control.add(start, BorderLayout.SOUTH);
		
		// main layer
		frame.setLayout(new BorderLayout());
		frame.add(control, BorderLayout.NORTH);
		frame.add(main, BorderLayout.CENTER);

		frame.setVisible(true);
		
	}
	
	// Generate Input Layer
	private void generateInputLayer(JPanel input) {
		
		// input layer
		JPanel wPanel = new JPanel();
		w = new JTextField("0.3", 5);
		JLabel wLabel = new JLabel("w: ");
		wPanel.add(wLabel); wPanel.add(w);
				
		JPanel c1Panel = new JPanel();
		JPanel c2Panel = new JPanel();
		JPanel iterPanel = new JPanel();
		JPanel pPanel = new JPanel();
		
		c1 = new JTextField("0.85", 5);
		JLabel c1Label = new JLabel("c1:");
		c2 = new JTextField("0.05", 5);
		JLabel c2Label = new JLabel("c2:");
		ePochs = new JTextField("5000", 5);
		JLabel iterLabel = new JLabel("Epochs:");
		numParticles = new JTextField("100", 5);
		JLabel pLabel = new JLabel("#Particles: ");
		
		c1Panel.add(c1Label); c1Panel.add(c1);
		c2Panel.add(c2Label); c2Panel.add(c2);
		iterPanel.add(iterLabel); iterPanel.add(ePochs);
		pPanel.add(pLabel); pPanel.add(numParticles);
		
		// Parallel choice
		JPanel parallelPanel = new JPanel();
		JLabel parallelLabel = new JLabel("isParallel: ");
		Boolean[] choice = {true, false};
		isParallel = new JComboBox<>(choice);
		isParallel.setSelectedIndex(0);
		isParallel.addActionListener(ae -> { Swarm.isParallel = choice[isParallel.getSelectedIndex()]; });
		parallelPanel.add(parallelLabel); parallelPanel.add(isParallel);
		
		// Converge choice
		JPanel convergePanel = new JPanel();
		JLabel convergeLabel = new JLabel("isConverge: ");
		isConverge = new JComboBox<>(choice);
		isConverge.setSelectedIndex(1);
		isConverge.addActionListener(ae -> { Particle.isConverge = choice[isConverge.getSelectedIndex()]; });
		convergePanel.add(convergeLabel); convergePanel.add(isConverge);
		
		input.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		
		input.add(wPanel);
		input.add(c1Panel);
		input.add(c2Panel);
		input.add(pPanel);
		input.add(iterPanel);
		input.add(parallelPanel);
		input.add(convergePanel);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource() == start) {
		
			// draw Route flag
			PSOMain.started = true;
			// System.out.println("Button inner.");
			
			// modify learning parameters
			Swarm.w = Double.parseDouble(w.getText());
			Swarm.c1 = Double.parseDouble(c1.getText());
			Swarm.c2 = Double.parseDouble(c2.getText());
			Swarm.ePochs = Integer.parseInt(ePochs.getText());
			Swarm.num = Integer.parseInt(numParticles.getText());
			
			// start drawing map
			main.repaint();
			
		}
		
	}
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PSO2D(48, "Cities-clean.txt");
			}
		});
		
	}

}
