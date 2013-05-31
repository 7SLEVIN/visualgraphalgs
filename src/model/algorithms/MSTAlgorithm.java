package model.algorithms;

import model.Graph;

public abstract class MSTAlgorithm extends Algorithm {
	
	protected Integer result;

	/**
	 * @param name
	 */
	public MSTAlgorithm(String name) {
		super(name);
	}
	
	public void reset() {
		this.result = null;
		this.initialized = false;
		this.finished = false;
	}
	
	public void initialize(Graph graph) {
		this.graph = graph;
		this.initialized = true;
	}

	public Integer getResult() {
		return result;
	}

}
