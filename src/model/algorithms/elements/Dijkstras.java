package model.algorithms.elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import model.EdgeComparator;
import model.EdgeComparatorType;
import model.algorithms.AlgorithmState;
import model.algorithms.ShortestRouteAlgorithm;
import model.elements.Edge;
import model.elements.Graph;
import model.elements.Vertex;
import exceptions.AlgorithmException;
import exceptions.GraphComponentException;
import exceptions.GraphException;

public class Dijkstras extends ShortestRouteAlgorithm {

	private Queue<Vertex> queue;
	private PriorityQueue<Edge> edges;
	private Vertex currentVertex;

	public Dijkstras() {
		super("Dijkstra's");
		this.queue = new LinkedList<Vertex>();
	}

	@Override
	public void initialize(String destination, Graph graph)
			throws GraphComponentException, GraphException {
		super.initialize(destination, graph);

		this.graph.getFirst().setAttribute("0");
		this.queue.add(this.graph.getFirst());
	}

	@Override
	protected void iterate() throws GraphComponentException, GraphException,
			AlgorithmException {
		// Go through neighbours
		if (this.edges != null && !this.edges.isEmpty()) {
			Edge edge = this.edges.remove();
			Vertex toVertex = edge.getTo();
			
			// Set distance
			int length = Integer.parseInt(edge.getFrom().getAttribute())
					+ Integer.parseInt(edge.getAttribute());
			if (toVertex.getAttribute() == null ||
					length < Integer.parseInt(toVertex.getAttribute())) {
				toVertex.setAttribute(String.valueOf(length));
				edge.visit();
				ArrayList<Edge> otherEdges = (ArrayList<Edge>) this.graph.getEdgesTo(toVertex);
				if (otherEdges != null) {
					for (Edge e : otherEdges) {
						if (e != edge) e.reset();
					}
				}
			}
			
			// Unvisited vertex found
			if (!toVertex.isVisited()) {
				this.queue.add(toVertex);

				// Correct found
				if (toVertex.getName().equalsIgnoreCase(this.destination)) {
					toVertex.found();
					this.result = toVertex.getAttribute();
				}
			}

			return;
		}
		
		// Set vertex as visited/ended
		if (this.currentVertex != null && 
				!this.currentVertex.isVisited() &&
				!this.currentVertex.isFound()) {
			this.currentVertex.visit();
		}

		// Graph covered without finding anything
		if (this.queue.isEmpty()) {
			if (this.result == null) this.result = "0";
			this.state = AlgorithmState.Finished;
			return;
		}

		// Take new vertex
		Vertex vertex = this.queue.remove();
		this.currentVertex = vertex;

		// Populate edges
		ArrayList<Edge> found = graph.getEdgesFrom(vertex);
		if (found == null) {
			this.iterate(); // no edges
			return;
		}
		this.edges = new PriorityQueue<Edge>(found.size(), new EdgeComparator(
				EdgeComparatorType.Name));
		for (Edge edge : found) {
			this.edges.add(edge);
		}
	}

	@Override
	public void reset() {
		super.reset();
		if (this.queue != null)
			this.queue.clear();
		if (this.edges != null)
			this.edges.clear();
		this.currentVertex = null;
	}

}
