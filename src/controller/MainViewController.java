package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import exceptions.AlgorithmAlreadyFinishedException;
import exceptions.AlgorithmException;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import exceptions.UnsupportedGraphException;

import model.Graph;
import model.algorithms.Algorithm;
import model.algorithms.AlgorithmState;
import model.algorithms.BFS;
import model.algorithms.DFS;
import model.algorithms.Kruskal;
import model.algorithms.MSTAlgorithm;
import model.algorithms.SearchAlgorithm;
import model.persistency.GraphsParser;
import utils.ActionUtils;
import utils.Dialog;
import view.MainView;

public class MainViewController {
	
	private static final int FIRE_RATE = 1000;

	protected final MainView view;
	protected ArrayList<Graph> graphs;
	protected ArrayList<Algorithm> algorithms;
//	private boolean ready;

	/**
	 * @param view
	 */
	public MainViewController(final MainView view) {
		this.view = view;
		this.graphs = new GraphsParser().parse();
		this.algorithms = new ArrayList<Algorithm>();
		this.algorithms.add(new BFS());
		this.algorithms.add(new DFS());
		this.algorithms.add(new Kruskal());
//		this.ready = true;

//		ActionUtils.addActionListener(this.view.getStepButton(), this, "stepAlgorithm");
//		ActionUtils.addActionListener(this.view.getRunButton(), this, "runAlgorithm");
		ActionUtils.addActionListener(this.view.getResetButton(), this, "reset");
		this.view.getStepButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runAlgorithm(true);
			}
		});
		this.view.getRunButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runAlgorithm(false);
			}
		});

		this.fillGraphs();
		this.fillAlgorits();
		
		this.view.getGraphComboBox().setSelectedIndex(0);
		this.view.getCanvas().setGraph(graphs.get(0));
		
//		ActionUtils.addItemListener(this.view.getGraphComboBox(), this, "setGraph", "e");
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
		}
		
		Algorithm algorithm = this.view.getSelectedAlgorithm();
		if (algorithm != null) {
			algorithm.reset();
		}
		
		this.view.setResultLabel("");
		this.setUIEnabled(true);
//		this.ready = true;
	}
	
	private void fillAlgorits() {
		this.view.setAlgorithms(this.algorithms);
	}

	private void fillGraphs() {
		this.view.setGraphs(this.graphs);
	}
	
	public void runAlgorithm(boolean step) {
		final Algorithm algorithm = this.view.getSelectedAlgorithm();
		Graph graph = this.view.getSelectedGraph();
		String find = null;
		if (algorithm instanceof SearchAlgorithm) {
			find = this.view.getFindInput().getText();
		}
		
		// Safety
		if (graph == null) {
			Dialog.message("No graph selected!");
			return;
		} else if (algorithm == null) {
			Dialog.message("No algorithm selected!");
			return;
		} else if (find != null) {
			if (find.equals("")) {
				Dialog.message("No vertex name entered!");
				return;
			}
//		} else if (!this.ready) {
//			Dialog.message("You must reset first!");
//			return;
		} 
		
		// Initiate
		if (algorithm instanceof SearchAlgorithm) {
			SearchAlgorithm searchAlgorithm = (SearchAlgorithm) algorithm;

			if (searchAlgorithm.getState() == AlgorithmState.Clean) {
				try {
					searchAlgorithm.initialize(find, graph);
				} catch (GraphComponentException e) {
					e.printStackTrace();
				}	
			}
		} else if (algorithm instanceof MSTAlgorithm) {
			MSTAlgorithm MSTAlgorithm = (MSTAlgorithm) algorithm;

			if (MSTAlgorithm.getState() == AlgorithmState.Clean) {
				try {
					MSTAlgorithm.initialize(graph);
				} catch (UnsupportedGraphException e) {
					Dialog.message("Graph must be weighted!");
					return;
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (step) {
			try {
				algorithm.run();
			} catch (AlgorithmAlreadyFinishedException e) {
				Dialog.message("Algorithm already finished!");
				return;
			} catch (AlgorithmException e) {
				e.printStackTrace();
			}
			
			this.view.getFindInput().setEditable(false);
			this.view.getCanvas().repaint();	

			if (algorithm.getResult() != null) {
				this.view.setResultLabel(String.format("%d", algorithm.getResult()));
			}
		} else {
			new Timer().scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					if (algorithm.getResult() != null) {
						view.setResultLabel(String.format("%d", algorithm.getResult()));
					} 
					if (algorithm.getState() == AlgorithmState.Finished) {
						this.cancel();
						setUIEnabled(true);
						return;
					}
					setUIEnabled(false);
					
					try {
						algorithm.run();
					} catch (AlgorithmAlreadyFinishedException e) {
						Dialog.message("Algorithm already finished!");
						return;
					} catch (AlgorithmException e) {
						e.printStackTrace();
					}
					view.getCanvas().repaint();
				}
			}, 0, FIRE_RATE);
		}
		
		if (algorithm.getState() == AlgorithmState.Clean ||
				algorithm.getState() == AlgorithmState.Finished) {
			this.setUIEnabled(true);			
		}
	}
	
	private void setUIEnabled(boolean editable) {
		this.view.getFindInput().setEditable(editable);
		this.view.getStepButton().setEnabled(editable);
		this.view.getRunButton().setEnabled(editable);
		this.view.getResetButton().setEnabled(editable);
	}

}
