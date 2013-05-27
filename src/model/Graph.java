package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Graph {
	
	private String name;
	private HashMap<String, Vertex> vertices;
	private HashMap<Vertex, Collection<Edge>> edges;
	
	/**
	 * @param name
	 */
	public Graph(String name) {
		this.name = name;
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Vertex, Collection<Edge>>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<Vertex, Collection<Edge>> getEdges() {
		return edges;
	}

	public void addEdge(Edge edge) {
		Collection<Edge> values = this.edges.get(edge.getFrom());
		if (values == null) {
			values = new ArrayList<Edge>();
			this.edges.put(edge.getFrom(), values);
		}
		values.add(edge);
	}

	public HashMap<String, Vertex> getVertices() {
		return vertices;
	}
	
	public Vertex getVertex(String name) {
		return this.vertices.get(name);
	}

	public void addVertex(Vertex vertex) {
		if (this.vertices.get(vertex.getName()) != null) {
			System.err.println("WARNING: Overwriting vertex " + vertex.getName());
		}
		this.vertices.put(vertex.getName(), vertex);
	}
	
}
