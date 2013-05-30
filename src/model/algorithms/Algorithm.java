package model.algorithms;

import model.Graph;
import model.exception.AlgorithmFinishedException;


abstract public class Algorithm implements Runnable {
	
	protected String name;
	protected Graph graph;
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
