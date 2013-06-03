package exceptions;

import model.elements.Graph;

@SuppressWarnings("serial")
public class CycleInAcyclicGraphException extends GraphException {

	/**
	 * @param graph
	 */
	public CycleInAcyclicGraphException(Graph graph) {
		super("Tried to add edge creating a cycle in acyclic graph", graph);
	}

}
