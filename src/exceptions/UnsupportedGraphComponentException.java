package exceptions;

import model.elements.GraphComponent;

@SuppressWarnings("serial")
public class UnsupportedGraphComponentException extends GraphComponentException {

	public UnsupportedGraphComponentException(String message,
			GraphComponent component) {
		super(message, component);
	}

}
