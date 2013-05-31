package model.algorithms;

import model.Graph;

public class Kruskal extends MSTAlgorithm {

	public Kruskal() {
		super("Kruskal");
	}
	
	@Override
	public void initialize(Graph graph) {
		super.initialize(graph);
		
		
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
		// TODO Auto-generated method stub

	}
	
	@Override
	public void reset() {
		super.reset();
	}

}
