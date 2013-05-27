package model.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import model.Graph;
import model.Vertex;

public class BFS extends Algorithm {
	
    public BFS(String name) {
		super(name);
	}

	public void run(Graph graph) {
    	Queue<Vertex> queue = new LinkedList<Vertex>();
    	System.out.println("Run BFS");
    }

}
