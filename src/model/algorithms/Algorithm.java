package model.algorithms;

import model.Graph;


abstract public class Algorithm {
	
	private String name;
	
	/**
	 * @param name
	 */
	public Algorithm(String name) {
		this.name = name;
	}
	
	abstract public int run(String vertexName, Graph graph);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
