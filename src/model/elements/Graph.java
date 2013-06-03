package model.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import model.algorithms.TopologicalSortAlgorithm;

import exceptions.AlgorithmException;
import exceptions.CycleInAcyclicGraphException;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import exceptions.UnsupportedGraphComponentException;

public class Graph {

	private String name;
	private GraphType type;
	private GraphAttributeType attributeType;
	private HashMap<String, Vertex> vertices;
	private HashMap<Vertex, Collection<Edge>> edges;
	private Vertex first;

	/**
	 * @param name
	 */
	public Graph(String name) {
		this(name, GraphType.Directed, GraphAttributeType.None);
	}

	/**
	 * @param name
	 * @param attributeType
	 */
	public Graph(String name, GraphType type, GraphAttributeType attributeType) {
		this.name = name;
		this.type = type;
		this.attributeType = attributeType;
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Vertex, Collection<Edge>>();
	}

	public void reset() {
		for (Collection<Edge> values : this.edges.values()) {
			for (Edge edge : values) {
				edge.reset();
			}
		}

		for (Vertex vertex : this.getVertices().values()) {
			vertex.reset();
		}
	}

	public boolean isAcyclic() throws GraphException, GraphComponentException,
			AlgorithmException {
		TopologicalSortAlgorithm algorithm = new TopologicalSortAlgorithm();
		algorithm.initialize(this);
		algorithm.run();
		return algorithm.getResult() != null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Edge> getEdgesAsList() {
		ArrayList<Edge> found = new ArrayList<Edge>();
		for (Collection<Edge> coll : this.edges.values()) {
			found.addAll(coll);
		}
		return found;
	}

	public HashMap<Vertex, Collection<Edge>> getEdges() {
		return this.edges;
	}

	public ArrayList<Edge> getEdges(Vertex vertex) {
		return (ArrayList<Edge>) this.edges.get(vertex);
	}

	public void addEdge(Edge edge) throws GraphComponentException,
			GraphException, AlgorithmException {
		if (edge.attribute == null
				&& this.attributeType == GraphAttributeType.Weighted) {
			throw new UnsupportedGraphComponentException(
					"Cannot add unweighted edge to weighted graph", edge);
		}

		Collection<Edge> values = this.edges.get(edge.getFrom());
		if (values == null) {
			values = new ArrayList<Edge>();
			this.edges.put(edge.getFrom(), values);
		}
		values.add(edge);

		if (this.type == GraphType.DirectedAcyclic && !this.isAcyclic()) {
			this.edges.get(edge.getFrom()).remove(edge);
			throw new CycleInAcyclicGraphException(this);
		}
	}

	public HashMap<String, Vertex> getVertices() {
		return vertices;
	}

	public Vertex getVertex(String name) {
		return this.vertices.get(name);
	}

	public void addVertex(Vertex vertex) {
		if (this.vertices.size() == 0)
			this.first = vertex;

		if (this.vertices.get(vertex.getName()) != null) {
			System.err.println("WARNING: Overwriting vertex "
					+ vertex.getName());
		}
		this.vertices.put(vertex.getName(), vertex);
	}

	public Vertex getFirst() {
		return this.first;
	}

	public GraphAttributeType getAttributeType() {
		return attributeType;
	}

	public GraphType getType() {
		return this.type;
	}

	@Override
	public String toString() {
		return String.format("%s name=''", this.getClass(), this.name);
	}
}
