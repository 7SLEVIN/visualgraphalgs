package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import model.Graph;
import model.algorithms.Algorithm;
import model.algorithms.BFS;
import model.algorithms.SearchAlgorithm;
import persistency.GraphsParser;
import utils.ActionUtils;
import utils.Dialog;
import view.MainView;

public class MainViewController {

	protected final MainView view;
	protected ArrayList<Graph> graphs;
	protected ArrayList<Algorithm> algorithms;
	private boolean ready;

	/**
	 * @param view
	 */
	public MainViewController(final MainView view) {
		this.view = view;
		this.graphs = new GraphsParser().parse();
		this.algorithms = new ArrayList<Algorithm>();
		this.algorithms.add(new BFS("BFS"));
		this.ready = true;

		ActionUtils.addListener(this.view.getStepButton(), this, "stepAlgorithm");
		ActionUtils.addListener(this.view.getRunButton(), this, "runAlgorithm");
		ActionUtils.addListener(this.view.getResetButton(), this, "reset");

		this.fillGraphs();
		this.fillAlgorits();
		
		this.view.getGraphComboBox().setSelectedIndex(0);
		this.view.getCanvas().setGraph(graphs.get(0));
		
		this.view.getGraphComboBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					view.getCanvas().setGraph(view.getSelectedGraph());
					reset();
				}
			}	
		});
		
		this.view.getAlgoComboBox().addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (view.getSelectedAlgorithm() instanceof SearchAlgorithm) {
						view.getFindLabel().setVisible(true);
						view.getFindInput().setVisible(true);
					} else {
						view.getFindLabel().setVisible(false);
						view.getFindInput().setVisible(false);
					}
				}
			}	
		});
	}
	
	public void reset() {
		Graph graph = this.view.getSelectedGraph();
		if (graph != null) {
			graph.reset();
			this.view.getCanvas().setGraph(graph);
//			Dialog.message("No graph selected!");
//			return;
		}
		
		Algorithm algorithm = this.view.getSelectedAlgorithm();
		if (algorithm != null) {
			algorithm.reset();
//			Dialog.message("No algorithm selected!");
//			return;
		}
		
		this.ready = true;
	}
	
	private void fillAlgorits() {
		this.view.setAlgorithms(this.algorithms);
	}

	private void fillGraphs() {
		this.view.setGraphs(this.graphs);
	}
	
	public void runAlgorithm() throws Exception {
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
		
		String find = this.view.getFindInput().getText();
		if (find.equals("")) {
			Dialog.message("No vertex name entered!");
			return;
		}
		
		if (!this.ready) {
			Dialog.message("You must reset first!");
			return;
		}
		
		if (algorithm instanceof SearchAlgorithm) {
			final SearchAlgorithm searchAlgorithm = (SearchAlgorithm) algorithm;


			if (!searchAlgorithm.isInitialized()) {
				searchAlgorithm.initialize(find, graph);	
			}
			
			new Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					if (searchAlgorithm.getResult() != null) {
						view.setResultLabel(String.format("%d", searchAlgorithm.getResult()));
						ready = false;
						this.cancel();
						return;
					} 
					
					searchAlgorithm.run();
					view.getCanvas().repaint();
				}
			}, 0, 1000);	
		}
	}
	
	public void stepAlgorithm() {
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
		
		String find = this.view.getFindInput().getText();
		if (find.equals("")) {
			Dialog.message("No vertex name entered!");
			return;
		}
		
		if (!this.ready) {
			Dialog.message("You must reset first!");
			return;
		}
		
		if (algorithm instanceof SearchAlgorithm) {
			SearchAlgorithm searchAlgorithm = (SearchAlgorithm) algorithm;


			if (!searchAlgorithm.isInitialized()) {
				searchAlgorithm.initialize(find, graph);	
			}
			
			searchAlgorithm.run();
			this.view.getCanvas().repaint();	

			if (searchAlgorithm.getResult() != null) {
				this.view.setResultLabel(String.format("%d", searchAlgorithm.getResult()));
				this.ready = false;
			} 
		}
	}

}
