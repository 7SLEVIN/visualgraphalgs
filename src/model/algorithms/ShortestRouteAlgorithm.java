package model.algorithms;

import model.elements.Graph;
import model.elements.GraphAttributeType;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import exceptions.UnsupportedGraphException;

public abstract class ShortestRouteAlgorithm extends Algorithm {
	
	protected String destination;

	/**
	 * @param name
	 */
	public ShortestRouteAlgorithm(String name) {
		super(name);
	}
	
	public void initialize(String destination, Graph graph) throws GraphException,
			GraphComponentException {
		if (graph.getAttributeType() != GraphAttributeType.Weighted) {
			throw new UnsupportedGraphException("Cannot initialize ShortestRouteAlgorithm with unweighted graph", graph);
		}
		this.destination = destination;
		this.graph = graph;
		this.state = AlgorithmState.Initialized;
	}
}
