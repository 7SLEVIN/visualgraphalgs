package model.algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;


import model.Edge;
import model.EdgeComparator;
import model.EdgeComparatorType;
import model.Graph;
import model.Vertex;

public class DFS extends SearchAlgorithm {
	
	private Stack<Vertex> stack;
	private PriorityQueue<Edge> edges;
	private Vertex currentVertex;
	private int depth;

	public DFS() {
		super("DFS");

		this.stack = new Stack<Vertex>();
	}
	
	@Override
	public void initialize(String find, Graph graph) {
		super.initialize(find, graph);
		
		this.currentVertex = graph.getFirst();
		this.currentVertex.visit();
		this.depth = 1;
		this.setAttribute(this.currentVertex);
				
		this.stack.push(this.currentVertex);
	}

	@Override
	public void run() {
		if (this.finished) {
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
				
		System.out.println("Current: " + this.currentVertex.getName());
		
		// Go through neighbours
		if (this.edges != null && !this.edges.isEmpty()) {
			Edge edge = this.edges.remove();
			
			Vertex toVertex = edge.getTo();
			
			System.out.println("Current edge: " + toVertex.getName());
			
			// Visit
			if (!toVertex.isVisited()) {
				toVertex.visit();
				this.setAttribute(toVertex);

				this.stack.push(toVertex);
				toVertex.setParent(this.currentVertex);
				
				this.edges.clear();
				
				// Check if correct
				if (toVertex.getName().equalsIgnoreCase(this.find)) {
					toVertex.setColor(Color.green);
					this.result = this.depth--;
					return;
				}
			} else {
				this.stack.push(this.currentVertex.getParent());
				System.out.println("Already visited: " + toVertex.getName());
				this.iterate();
				return;
			}
		}
		
		// Graph covered without finding anything
		if (this.stack.isEmpty()) {
			System.out.println("Stack empty");
			this.result = 0;
			return;
		} 
		
		// Populate queue with neighbours
		this.currentVertex = this.stack.pop();
		
		// Back at start
		if (this.currentVertex == null) {
			this.finished = true;
			return;
		}
		
		ArrayList<Edge> found = graph.getEdges(this.currentVertex); 
		if (found == null) {
			System.out.println("No edges found");
			this.stack.push(this.currentVertex.getParent());
			this.setAttribute(this.currentVertex);
			this.iterate(); // no edges
			return;
		}
		
		this.edges = new PriorityQueue<Edge>(found.size(), new EdgeComparator(EdgeComparatorType.Name));
		for (Edge edge : found) {
			if (!edge.getTo().isVisited()) this.edges.add(edge);
		}
		if (this.edges.isEmpty()) {
			this.stack.push(this.currentVertex.getParent());
			this.setAttribute(this.currentVertex);
			System.out.println("No unvisited edges found");
		}
	}

	@Override
	public void reset() {
		super.reset();
		this.stack.clear();
		if (this.edges != null) this.edges.clear();
		this.depth = 0;
		this.currentVertex = null;
	}
	
	private void setAttribute(Vertex vertex) {
		if (vertex.getAttribute() == null) {
			vertex.setAttribute(String.format("%d/", this.depth));
		} else {
			this.depth--;
			String old = vertex.getAttribute();
			vertex.setAttribute(String.format("%s%d", old, this.depth+1));
			this.depth++;
		}
		this.depth++;
	}

}
