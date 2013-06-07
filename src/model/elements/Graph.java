package model.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import model.algorithms.AlgorithmState;
import model.algorithms.elements.TopologicalSortAlgorithm;
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
	private HashMap<Vertex, Collection<Edge>> edgesFrom;
	private HashMap<Vertex, Collection<Edge>> edgesTo;
	private Vertex first;

	/**
	 * @param name
	 */
	public Graph(String name) {
		this(name, GraphType.Directed, GraphAttributeType.None);
	}

	/**
	 * @param name
	 * @param type
	 * @param attributeType
	 */
	public Graph(String name, GraphType type, GraphAttributeType attributeType) {
		this.name = name;
		this.type = type;
		this.attributeType = attributeType;
		this.vertices = new HashMap<String, Vertex>();
		this.edgesFrom = new HashMap<Vertex, Collection<Edge>>();
		this.edgesTo = new HashMap<Vertex, Collection<Edge>>();
	}

	public void reset() {
		for (Edge edge : this.getEdgesAsList()) {
			edge.reset();
		}

		for (Vertex vertex : this.getVertices().values()) {
			vertex.reset();
		}
	}

	public boolean isAcyclic() throws GraphException, GraphComponentException,
			AlgorithmException {
		TopologicalSortAlgorithm algorithm = new TopologicalSortAlgorithm();
		algorithm.initialize(this);
		while (algorithm.getState() != AlgorithmState.Finished) {
			algorithm.run();
		}
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
		for (Collection<Edge> coll : this.edgesFrom.values()) {
			found.addAll(coll);
		}
		return found;
	}

	public HashMap<Vertex, Collection<Edge>> getEdges() {
		return this.edgesFrom;
	}

	public ArrayList<Edge> getEdgesFrom(Vertex vertex) {
		return (ArrayList<Edge>) this.edgesFrom.get(vertex);
	}

	public ArrayList<Edge> getEdgesTo(Vertex vertex) {
		return (ArrayList<Edge>) this.edgesTo.get(vertex);
	}

	public void addEdge(Edge edge) throws GraphComponentException,
			GraphException, AlgorithmException {
		if (edge.attribute == null
				&& this.attributeType == GraphAttributeType.Weighted) {
			throw new UnsupportedGraphComponentException(
					"Cannot add unweighted edge to weighted graph", edge);
		}

		// Add from
		if (this.edgesFrom.get(edge.getFrom()) == null)
			this.edgesFrom.put(edge.getFrom(), new ArrayList<Edge>());
		this.edgesFrom.get(edge.getFrom()).add(edge);
		// Add to
		if (this.edgesTo.get(edge.getTo()) == null)
			this.edgesTo.put(edge.getTo(), new ArrayList<Edge>());
		this.edgesTo.get(edge.getTo()).add(edge);

		// Acyclic test
		if (this.type == GraphType.DirectedAcyclic && !this.isAcyclic()) {
			edge.reset();
			this.edgesFrom.get(edge.getFrom()).remove(edge);
			this.edgesTo.get(edge.getTo()).remove(edge);
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
			System.err.printf("WARNING: Overwriting vertex %s",
					vertex.getName());
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
		return String.format("%s name='%s'", this.getClass().getName(),
				this.name);
	}
}
