package model.algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import model.Edge;
import model.Graph;
import model.Vertex;
import utils.EdgeComparator;

public class BFS extends SearchAlgorithm {

	private Queue<Vertex> queue;
	private PriorityQueue<Edge> edges;
	private Vertex currentVertex;

	/**
	 * @param name
	 */
	public BFS(String name) {
		super(name);

		this.queue = new LinkedList<Vertex>();
	}
	
	@Override
	public void initialize(String find, Graph graph) {
		super.initialize(find, graph);
		
		this.currentVertex = graph.getFirst();
		this.currentVertex.visit();
		this.currentVertex.setAttribute(String.valueOf("0")); // depth
		
		this.queue.add(this.currentVertex);
	}

	@Override
	public void run() {
		if (this.result != null) {
			System.err.println("WARNING: Algorithm already finished");
			return;
		} else if (!this.initialized) {
			System.err.println("WARNING: Algorithm not initialized");
			return;
		}
		
		this.iterate();
	}

	@Override
	protected void iterate() {
		// Go through neighbours
		if (this.edges != null && !this.edges.isEmpty()) {
			Edge edge = this.edges.remove();
			
			Vertex toVertex = edge.getTo();
			
			if (!toVertex.isVisited()) {
				toVertex.visit();
				toVertex.setAttribute(String.valueOf(Integer.parseInt(this.currentVertex.getAttribute())+1));

				this.queue.add(toVertex);
				
				if (toVertex.getName().equalsIgnoreCase(this.find)) {
					toVertex.setColor(Color.green);
					this.result = Integer.parseInt(toVertex.getAttribute());
				}
			} else {
				this.iterate();
			}
			
			return;
		}
		
		// Graph covered without finding anything
		if (this.queue.isEmpty()) {
			this.result = 0;
			return;
		}

		// Populate queue with neighbours
		Vertex vertex = this.queue.remove();
		this.currentVertex = vertex;
		
		ArrayList<Edge> found = graph.getEdges(vertex); 
		if (found == null) {
			this.iterate(); // no edges
			return;
		}
		
		this.edges = new PriorityQueue<Edge>(found.size(), new EdgeComparator());
		for (Edge edge : found) {
			this.edges.add(edge);
		}
		
		if (Integer.parseInt(this.currentVertex.getAttribute()) > 0) this.iterate();
	}

	@Override
	public void reset() {
		super.reset();
		this.queue.clear();
		this.edges.clear();
		this.currentVertex = null;
	}
	
}
