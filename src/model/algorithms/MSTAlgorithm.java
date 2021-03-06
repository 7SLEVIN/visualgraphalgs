package model.algorithms;

import model.elements.Graph;
import model.elements.GraphAttributeType;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import exceptions.UnsupportedGraphException;

public abstract class MSTAlgorithm extends Algorithm {

	/**
	 * @param name
	 */
	public MSTAlgorithm(String name) {
		super(name);
	}
	
	public void initialize(Graph graph) throws GraphException, GraphComponentException {
		if (graph.getAttributeType() != GraphAttributeType.Weighted) {
			throw new UnsupportedGraphException("Cannot initialize MSTAlgorithm with unweighted graph", graph);
		}
		this.graph = graph;
		this.state = AlgorithmState.Initialized;
	}

}
