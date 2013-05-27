package model.algorithms;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

import model.Edge;
import model.Graph;
import model.Vertex;

public class BFS extends Algorithm {

	private Queue<Vertex> queue;

	public BFS(String name) {
		super(name);

		this.queue = new LinkedList<Vertex>();
	}

	public int run(String vertexName, Graph graph) {

		Vertex first = graph.getFirst();
		first.visit();
		first.setAttribute("0"); // depth

		this.queue.add(first);

		while (!this.queue.isEmpty()) {
			Vertex fromVertex = this.queue.remove();

			for (Edge edge : graph.getEdges(fromVertex)) {
				Vertex toVertex = edge.getTo();
				if (!toVertex.isVisisted()) {
					toVertex.visit();
					toVertex.setAttribute(String.valueOf(Integer.parseInt(fromVertex.getAttribute())+1));

					this.queue.add(toVertex);
					
					if (toVertex.getName().equals(vertexName)) {
						toVertex.setColor(Color.green);
						return Integer.parseInt(toVertex.getAttribute());
					}
				}
			}
		}
		return 0;
	}
	
}
