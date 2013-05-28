package model.algorithms;

import model.Graph;


abstract public class Algorithm implements Runnable {
	
	protected String name;
	protected Graph graph;
	public static final Class[] ALGORITHM_TYPES = {SearchAlgorithm.class};
	protected boolean initialized;
	
	/**
	 * @param name
	 */
	public Algorithm(String name) {
		this.name = name;
		this.initialized = false;
	}
	
	abstract protected void iterate();
	
	abstract public void reset();

	public boolean isInitialized() {
		return initialized;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
