package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Graph;
import model.algorithms.Algorithm;
import model.gui.AlgorithmComboBoxModel;
import model.gui.AlgorithmListRenderer;
import model.gui.GraphComboBoxModel;
import model.gui.GraphListRenderer;


@SuppressWarnings("serial")
public class MainView extends JFrame {

	public final static int WINDOW_WIDTH = 600;
	public final static int WINDOW_HEIGHT = 400;
	
	private Canvas canvas;
	private JComboBox<Graph> graphComboBox;
	private JComboBox<Algorithm> algoComboBox;
	private JButton runButton;
			
	/**
	 * 
	 */
	public MainView() {
		super("Visual Graph Algorithms");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.getContentPane().add(this.canvas, BorderLayout.CENTER);

		JPanel graphPanel = new JPanel();
		this.graphComboBox = new JComboBox<Graph>();
		this.algoComboBox = new JComboBox<Algorithm>();
		this.runButton = new JButton("Run");
		graphPanel.add(new JLabel("Select graph:"));
		graphPanel.add(this.graphComboBox);
		graphPanel.add(new JLabel("Select algorithm:"));
		graphPanel.add(this.algoComboBox);
		graphPanel.add(this.runButton);
		this.getContentPane().add(graphPanel, BorderLayout.NORTH);
		
		this.pack();
		this.setVisible(true);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setGraphs(ArrayList<Graph> graphs) {
		this.graphComboBox.setModel(new GraphComboBoxModel(graphs));
		this.graphComboBox.setRenderer(new GraphListRenderer());
	}

	public void setAlgorithms(ArrayList<Algorithm> algorithms) {
		this.algoComboBox.setModel(new AlgorithmComboBoxModel(algorithms));
		this.algoComboBox.setRenderer(new AlgorithmListRenderer());
	}

	public JComboBox<Graph> getGraphComboBox() {
		return this.graphComboBox;
	}

	public Graph getSelectedGraph() {
		return (Graph) this.graphComboBox.getSelectedItem();
	}
	
	public Algorithm getSelectedAlgorithm() {
		return (Algorithm) this.algoComboBox.getSelectedItem();
	}
	
	public JButton getRunButton() {
		return this.runButton;
	}
	
}
