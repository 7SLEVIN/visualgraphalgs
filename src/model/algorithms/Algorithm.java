package model.algorithms;

import exceptions.AlgorithmAlreadyFinishedException;
import exceptions.AlgorithmException;
import exceptions.AlgorithmNotInitializedException;
import exceptions.GraphComponentException;
import exceptions.GraphException;
import model.elements.Graph;


abstract public class Algorithm {
	
	protected String name;
	protected Graph graph;
	protected String result;
	protected AlgorithmState state;
	
	/**
	 * @param name
	 */
	public Algorithm(String name) {
		this.name = name;
		this.state = AlgorithmState.Clean;
	}
	
	abstract protected void iterate() throws GraphComponentException, GraphException, AlgorithmException;

	public void run() throws AlgorithmException {
		if (this.state == AlgorithmState.Clean) throw new AlgorithmNotInitializedException();
		if (this.state == AlgorithmState.Finished) throw new AlgorithmAlreadyFinishedException();

		// Thread
		new Runnable() {
			@Override
			public void run() {
				try {
					iterate();
				} catch (GraphComponentException | GraphException | AlgorithmException e) {
					e.printStackTrace();
				}
			}
		}.run();
	}
	
	public void reset() {
		this.graph = null;
		this.result = null;
		this.state = AlgorithmState.Clean;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public AlgorithmState getState() {
		return state;
	}

}
