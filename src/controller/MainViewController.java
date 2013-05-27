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

		this.fillGraphs();
		this.fillAlgorits();
		
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
		
		algorithm.run(graph);
	}

//	public void addRandomVertex() {
//		int x = (int) (Math.random() * MainView.WINDOW_WIDTH);
//		int y = (int) (Math.random() * MainView.WINDOW_HEIGHT);
//		// Color randomColor = new Color((float) Math.random(),
//		// (float) Math.random(), (float) Math.random());
//		this.view.getCanvas().addComponent(new Vertex("A", new Coordinate(x, y)));
//	}
//
//	public void addRandomEdge() {
//		// Color randomColor = new Color((float) Math.random(),
//		// (float) Math.random(), (float) Math.random());
//		this.view.getCanvas().getVertices();
//		this.view.getCanvas().addComponent(new Line(from, to));
//	}

}
