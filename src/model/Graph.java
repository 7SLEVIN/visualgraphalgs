package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import exceptions.GraphComponentException;
import exceptions.UnsupportedGraphComponentException;

public class Graph {
	
	private String name;
	private GraphAttributeType attributeType;
	private HashMap<String, Vertex> vertices;
	private HashMap<Vertex, Collection<Edge>> edges;
	private Vertex first;
	
	/**
	 * @param name
	 */
	public Graph(String name) {
		this(name, GraphAttributeType.None);
	}
	
	/**
	 * @param name
	 * @param attributeType
	 */
	public Graph(String name, GraphAttributeType attributeType) {
		this.name = name;
		this.attributeType = attributeType;
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

	public void addEdge(Edge edge) throws GraphComponentException {
		if (edge.attribute == null &&
				this.attributeType == GraphAttributeType.Weighted) {
			throw new UnsupportedGraphComponentException("Cannot add unweighted edge to weighted graph", edge);
		}
		
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
		if (this.vertices.size() == 0) this.first = vertex;
		
		if (this.vertices.get(vertex.getName()) != null) {
			System.err.println("WARNING: Overwriting vertex " + vertex.getName());
		}
		this.vertices.put(vertex.getName(), vertex);
	}
	
	public Vertex getFirst() {
		return this.first;
	}

	public ArrayList<Edge> getEdges(Vertex vertex) {
		return (ArrayList<Edge>) this.edges.get(vertex);
	}

	public void reset() {
		for (Collection<Edge> values : this.getEdges().values()) {
			for (Edge edge : values) {
				edge.reset();
			}
		}
		
		for (Vertex vertex : this.getVertices().values()) {
			vertex.reset();
		}
	}

	public GraphAttributeType getAttributeType() {
		return attributeType;
	}
	
}
