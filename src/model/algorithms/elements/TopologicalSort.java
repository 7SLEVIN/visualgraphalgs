package model.algorithms.elements;

import java.util.ArrayList;
import java.util.HashMap;

import model.algorithms.AlgorithmState;
import model.algorithms.SortAlgorithm;
import model.elements.Edge;
import model.elements.Graph;
import model.elements.GraphType;
import model.elements.Vertex;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import exceptions.UnsupportedGraphException;

public class TopologicalSort extends SortAlgorithm {
	
	private ArrayList<Vertex> sorted;
	private HashMap<String, Vertex> vertices;
	private ArrayList<Edge> edges;

	/**
	 * 
	 */
	public TopologicalSort() {
		super("Topological Sort");
		this.sorted = new ArrayList<Vertex>();
		this.edges = new ArrayList<Edge>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(Graph graph) throws GraphException,
			GraphComponentException {
		super.initialize(graph);
		if (this.graph.getType() == GraphType.Undirected) {
			throw new UnsupportedGraphException("Cannot initialize Topological Sort with a undirected graph", graph);
		}
		
		this.vertices = (HashMap<String, Vertex>) graph.getVertices().clone();
		for (Vertex vertex : this.vertices.values()) {
			vertex.reset();
		}
		
		this.edges = (ArrayList<Edge>) graph.getEdgesAsList().clone();
		for (Edge edge : this.edges) {
			edge.reset();
		}
	}

	@Override
	protected void iterate() throws GraphComponentException {
		// Sort all vertices
		if (!this.vertices.isEmpty()) {
			
			// Find ophan
			Vertex orphan = null;
			for (Vertex vertex : this.vertices.values()) {
				boolean noneFound = true;
				for (Edge edge : this.edges) {
					if (edge.getTo().getName().equals(vertex.getName())) {
						noneFound = false;
					}
				}
				if (noneFound) {
					orphan = vertex;
					break;
				}
			}
			
			if (orphan == null) { // None found then quit
				this.state = AlgorithmState.Finished;
			} else { // Add to sorted
				orphan.visit();
				this.sorted.add(orphan);
				// Remove vertex and all edges from 
				this.vertices.remove(orphan.getName());
				ArrayList<Edge> found = new ArrayList<Edge>();
				for (Edge edge : this.edges) {
					if (edge.getFrom().getName().equals(orphan.getName())) {
						found.add(edge);
					}
				}
				for (Edge edge : found) {
					this.edges.remove(edge);
				}
			}
			
		// When done sorting all vertices
		} else {
			// Finished
			String tmpResult = "";
			for (Vertex vertex : this.sorted) {
				vertex.found();
				tmpResult = String.format("%s %s", tmpResult, vertex.getName());
			}
			this.result = tmpResult;
			this.state = AlgorithmState.Finished;
		}
	}

	@Override
	public void reset() {
		super.reset();
		if (this.sorted != null) this.sorted.clear();
		if (this.vertices != null) this.vertices.clear();
		if (this.edges != null) this.edges.clear();
	}

}
