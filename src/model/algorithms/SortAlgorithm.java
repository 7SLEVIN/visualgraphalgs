package model.algorithms;

import model.elements.Graph;
import exceptions.GraphComponentException;
import exceptions.GraphException;

public abstract class SortAlgorithm extends Algorithm {

	/**
	 * @param name
	 */
	public SortAlgorithm(String name) {
		super(name);
	}
	
	public void initialize(Graph graph) throws GraphException,
			GraphComponentException {
		this.graph = graph;
		this.state = AlgorithmState.Initialized;
	}

}
