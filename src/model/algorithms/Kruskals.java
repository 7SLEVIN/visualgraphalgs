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
import exceptions.CycleInAcyclicGraphException;
import exceptions.GraphComponentException;
import exceptions.GraphException;

public class Kruskals extends MSTAlgorithm {

	private PriorityQueue<Edge> edges;

	/**
	 * 
	 */
	public Kruskals() {
		super("Kruskal's");
	}

	@Override
	public void initialize(Graph graph) throws GraphException,
			GraphComponentException {
		super.initialize(graph);

		// Sort edges by weight
		this.edges = new PriorityQueue<Edge>(graph.getEdgesAsList().size(),
				new EdgeComparator(EdgeComparatorType.Weight));
		for (Vertex vertex : graph.getVertices().values()) {
			Collection<Edge> found = graph.getEdges(vertex);
			if (found == null)
				continue;
			for (Edge edge : found) {
				this.edges.add(edge);
			}
		}
	}

	@Override
	protected void iterate() throws GraphComponentException, GraphException,
			AlgorithmException {
		// Finished if all vertices are visited forming a MST
		boolean allVisited = true;
		for (Vertex vertex : this.graph.getVertices().values()) {
			if (!vertex.isVisited())
				allVisited = false;
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
				if (vertex.isVisited())
					vertex.found();
			}
			// Set fields and return
			this.result = String.valueOf(value);
			this.state = AlgorithmState.Finished;
			return;
		}

		// Quit
		if (this.edges.isEmpty()) {
			if (this.result == null)
				this.result = "0";
			this.state = AlgorithmState.Finished;
			return;
		}

		// Take cheapest edge
		Edge edge = this.edges.remove();
		Edge edgeClone = edge.clone();

		// Create temporary graph with visited components
		Graph graph = new Graph("Acyclic test", GraphType.DirectedAcyclic,
				GraphAttributeType.None);
		// Add cloned components
		for (Edge e : this.graph.getEdgesAsList()) {
			if (e.isVisited()) {
				Edge clone = e.clone();
				if (graph.getVertex(clone.getFrom().getName()) == null) {
					graph.addVertex(clone.getFrom());					
				} else {
					clone.setFrom(graph.getVertex(clone.getFrom().getName()));
				}
				if (graph.getVertex(clone.getTo().getName()) == null) {
					graph.addVertex(clone.getTo());					
				} else {
					clone.setTo(graph.getVertex(clone.getTo().getName()));
				}
				graph.addEdge(clone);
			}
		}

		// Try to add edge and see if cycle occurs
		try {
			if (graph.getVertex(edgeClone.getFrom().getName()) == null) {
				graph.addVertex(edgeClone.getFrom());
			} else {
				edgeClone.setFrom(graph.getVertex(edgeClone.getFrom().getName()));
			}
			if (graph.getVertex(edgeClone.getTo().getName()) == null) {
				graph.addVertex(edgeClone.getTo());
			} else {
				edgeClone.setTo(graph.getVertex(edgeClone.getTo().getName()));
			}
			
			graph.addEdge(edgeClone);
		} catch (CycleInAcyclicGraphException e) {
			// Didn't work out.
			edge.reset();
			this.iterate();
			return;
		}

		// Did work out!
		edge.visit();
		if (!edge.getTo().isVisited())
			edge.getTo().visit();
		if (!edge.getFrom().isVisited())
			edge.getFrom().visit();
	}

	@Override
	public void reset() {
		super.reset();
		if (this.edges != null)
			this.edges.clear();
	}
	

}
