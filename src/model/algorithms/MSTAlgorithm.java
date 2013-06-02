package model.algorithms;

import exceptions.GraphException;
import exceptions.UnsupportedGraphException;
import model.Graph;
import model.GraphAttributeType;

public abstract class MSTAlgorithm extends Algorithm {

	/**
	 * @param name
	 */
	public MSTAlgorithm(String name) {
		super(name);
	}
	
	public void initialize(Graph graph) throws GraphException {
		if (graph.getAttributeType() != GraphAttributeType.Weighted) {
			throw new UnsupportedGraphException("Cannot initialize MSTAlgorithm with unweighted graph", graph);
		}
		this.graph = graph;
		this.state = AlgorithmState.Initialized;
	}

}
