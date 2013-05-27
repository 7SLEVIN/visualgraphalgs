package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import model.Graph;
import model.algorithms.Algorithm;
import model.algorithms.BFS;
import persistency.GraphsParser;
import utils.ActionUtils;
import utils.Dialog;
import view.MainView;

public class MainViewController {

	protected final MainView view;
	protected ArrayList<Graph> graphs;
	protected ArrayList<Algorithm> algorithms;

	/**
	 * @param view
	 */
	public MainViewController(final MainView view) {
		this.view = view;
		this.graphs = new GraphsParser().parse();
		this.algorithms = new ArrayList<Algorithm>();
		this.algorithms.add(new BFS("BFS"));

		ActionUtils.addListener(this.view.getRunButton(), this, "runAlgorithm");
		ActionUtils.addListener(this.view.getResetButton(), this, "reset");

		this.fillGraphs();
		this.fillAlgorits();
		
		this.view.getGraphComboBox().setSelectedIndex(0);
		this.view.getCanvas().setGraph(graphs.get(0));
		
		ItemListener graphComboBoxListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					view.getCanvas().setGraph(view.getSelectedGraph());
				}
			}	
		};
		this.view.getGraphComboBox().addItemListener(graphComboBoxListener);
	}
	
	public void reset() {
		Graph graph = this.view.getSelectedGraph();
		graph.reset();
		this.view.getCanvas().repaint();
	}
	
	private void fillAlgorits() {
		this.view.setAlgorithms(this.algorithms);
	}

	private void fillGraphs() {
		this.view.setGraphs(this.graphs);
	}
	
	public void runAlgorithm() {
		Graph graph = this.view.getSelectedGraph();
		if (graph == null) {
			Dialog.message("No graph selected!");
			return;
		}
		
		Algorithm algorithm = this.view.getSelectedAlgorithm();
		if (algorithm == null) {
			Dialog.message("No algorithm selected!");
			return;
		}
		
		String vertexName = this.view.getVertexNameInput();
		if (vertexName == "") {
			Dialog.message("No vertex name entered!");
			return;
		}
		
		this.view.setResultLabel(String.valueOf(algorithm.run(vertexName, graph)));
		this.view.getCanvas().repaint();
	}

}
