package exceptions;

import model.elements.Graph;

@SuppressWarnings("serial")
public class UnsupportedGraphException extends GraphException {

	public UnsupportedGraphException(String message,
			Graph graph) {
		super(message, graph);
	}

}
