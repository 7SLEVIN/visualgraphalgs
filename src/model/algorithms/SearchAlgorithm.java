package model.algorithms;

import model.Graph;

public abstract class SearchAlgorithm extends Algorithm {
	
	protected String find;
	protected Integer result;

	/**
	 * @param name
	 */
	public SearchAlgorithm(String name) {
		super(name);
	}
	
	public void reset() {
		this.result = null;
		this.initialized = false;
		this.finished = false;
	}
	
	public void initialize(String find, Graph graph) {
		this.find = find;
		this.graph = graph;
		this.initialized = true;
	}

	public Integer getResult() {
		return result;
	}

}
