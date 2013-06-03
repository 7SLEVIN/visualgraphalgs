package model.algorithms;

import java.util.Collection;
import java.util.PriorityQueue;

import model.EdgeComparator;
import model.EdgeComparatorType;
import model.elements.Edge;
import model.elements.Graph;
import model.elements.GraphAttributeType;
import model.elements.GraphType;
import model.elements.Vertex;
import exceptions.AlgorithmException;
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
	public void initialize(Graph graph) 
			throws GraphException, GraphComponentException {
		super.initialize(graph);
		
		// Sort edges by weight
		this.edges = new PriorityQueue<Edge>(graph.getEdgesAsList().size(), new EdgeComparator(EdgeComparatorType.Weight));
		for (Vertex vertex : graph.getVertices().values()) {
			Collection<Edge> found = graph.getEdges(vertex);
			if (found == null) continue;
			for (Edge edge : found) {
				this.edges.add(edge);
			}
		}
	}

	@Override
	protected void iterate() throws GraphComponentException, GraphException, AlgorithmException {
		// Finished if all vertices are visited forming a MST
		boolean allVisited = true;
		for (Vertex vertex : this.graph.getVertices().values()) {
			if (!vertex.isVisited()) allVisited = false;
		}
		if (allVisited) {
			// Find value of MST
			int value = 0;
			for (Edge edge : this.graph.getEdgesAsList()) {
				if (edge.isVisited()) {
					value += Integer.parseInt(edge.getAttribute());
					edge.found();
				}
			}
			for (Vertex vertex : this.graph.getVertices().values()) {
				if (vertex.isVisited()) vertex.found();
			}
			// Set fields and return
			this.result = String.valueOf(value);
			this.state = AlgorithmState.Finished;
			return;
		}
		
		// Quit
		if (this.edges.isEmpty()) {
			if (this.result == null) this.result = "0";
			this.state = AlgorithmState.Finished;
			return;
		}
		
		Edge edge = this.edges.remove();
		Graph graph = new Graph("Acyclic test", GraphType.DirectedAcyclic, GraphAttributeType.None);
		for (Vertex vertex : this.graph.getVertices().values()) {
			if (vertex.isVisited()) graph.addVertex(vertex);
			Collection<Edge> found = graph.getEdges(vertex);
			if (found == null) continue;
			for (Edge e : found) {
				if (e.isVisited()) graph.addEdge(e);
			}
		}
		try {
			graph.addEdge(edge);
			edge.visit();	
			edge.getTo().visit();
			edge.getFrom().visit();
		} catch (GraphException e) {
			// Didn't work out.
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		if (this.edges != null) this.edges.clear();
	}

}
