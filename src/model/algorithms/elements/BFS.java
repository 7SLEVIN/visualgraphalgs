package model.algorithms.elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import model.EdgeComparator;
import model.EdgeComparatorType;
import model.algorithms.AlgorithmState;
import model.algorithms.SearchAlgorithm;
import model.elements.Edge;
import model.elements.Graph;
import model.elements.Vertex;
import exceptions.GraphComponentException;

public class BFS extends SearchAlgorithm {

	private Queue<Vertex> queue;
	private PriorityQueue<Edge> edges;
	private Vertex currentVertex;

	/**
	 * 
	 */
	public BFS() {
		super("BFS");
		this.queue = new LinkedList<Vertex>();
	}
	
	@Override
	public void initialize(String find, Graph graph) throws GraphComponentException {
		super.initialize(find, graph);
		
		this.currentVertex = graph.getFirst();
		this.currentVertex.visit();
		this.currentVertex.setAttribute(String.valueOf("0")); // depth
		
		this.queue.add(this.currentVertex);
	}

	@Override
	protected void iterate() throws GraphComponentException {
		// Go through neighbours
		if (this.edges != null && !this.edges.isEmpty()) {
			Edge edge = this.edges.remove();
			
			Vertex toVertex = edge.getTo();
			
			if (!toVertex.isVisited()) {
				toVertex.visit();
				toVertex.setAttribute(String.valueOf(Integer.parseInt(this.currentVertex.getAttribute())+1));

				this.queue.add(toVertex);
				
				// Correct found
				if (toVertex.getName().equalsIgnoreCase(this.find)) {
					toVertex.found();
					this.result = toVertex.getAttribute();
				}
			} else {
				this.iterate();
			}
			
			return;
		}
		
		// Graph covered without finding anything
		if (this.queue.isEmpty()) {
			if (this.result == null) this.result = "0";
			this.state = AlgorithmState.Finished;
			return;
		}

		// Populate queue with neighbours
		Vertex vertex = this.queue.remove();
		this.currentVertex = vertex;
		
		ArrayList<Edge> found = graph.getEdgesFrom(vertex); 
		if (found == null) {
			this.iterate(); // no edges
			return;
		}
		
		this.edges = new PriorityQueue<Edge>(found.size(), new EdgeComparator(EdgeComparatorType.Name));
		for (Edge edge : found) {
			this.edges.add(edge);
		}
		
		if (Integer.parseInt(this.currentVertex.getAttribute()) > 0) this.iterate();
	}

	@Override
	public void reset() {
		super.reset();
		if (this.queue != null) this.queue.clear();
		if (this.edges != null) this.edges.clear();
		this.currentVertex = null;
	}
	
}
