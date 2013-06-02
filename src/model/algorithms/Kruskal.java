package model.algorithms;

import java.util.PriorityQueue;

import model.Edge;
import model.EdgeComparator;
import model.EdgeComparatorType;
import model.Graph;
import model.Vertex;
import exceptions.GraphComponentException;
import exceptions.GraphException;

public class Kruskal extends MSTAlgorithm {

	private PriorityQueue<Edge> edges;
	
	/**
	 * 
	 */
	public Kruskal() {
		super("Kruskal");
	}
	
	@Override
	public void initialize(Graph graph) throws GraphException {
		super.initialize(graph);
	}

	@Override
	protected void iterate() throws GraphComponentException {
		// Find all edges and sort lazily
		if (this.edges == null) {
			this.edges = new PriorityQueue<Edge>(this.graph.getEdges().size(), new EdgeComparator(EdgeComparatorType.Weight));
			for (Vertex vertex : this.graph.getVertices().values()) {
				for (Edge edge : this.graph.getEdges(vertex)) {
					this.edges.add(edge);
				}
			}
		}
		
		// Finished
		if (this.edges.isEmpty()) {
			if (this.result == null) this.result = 0;
			this.state = AlgorithmState.Finished;
			return;
		}
		
		Edge edge = this.edges.remove();
		edge.visit();
	}
	
	@Override
	public void reset() {
		super.reset();
	}

}
