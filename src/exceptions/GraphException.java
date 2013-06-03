package exceptions;

import model.elements.Graph;

@SuppressWarnings("serial")
public class GraphException extends Exception {
	
	protected Graph graph;

	/**
	 * @param message
	 * @param graph
	 */
	public GraphException(String message, Graph graph) {
		super(message);
		this.graph = graph;
	}

	public Graph getGraph() {
		return graph;
	}
	
}
