package model.algorithms;

import model.elements.Graph;
import exceptions.GraphComponentException;

public abstract class SearchAlgorithm extends Algorithm {

	protected String find;

	/**
	 * @param name
	 */
	public SearchAlgorithm(String name) {
		super(name);
	}

	@Override
	public void reset() {
		super.reset();
		this.find = null;
	}

	public void initialize(String find, Graph graph) throws GraphComponentException {
		this.find = find;
		this.graph = graph;
		this.state = AlgorithmState.Initialized;
	}

}
