package exceptions;

import model.elements.GraphComponent;

@SuppressWarnings("serial")
public class GraphComponentException extends Exception {
	
	protected GraphComponent component;

	/**
	 * @param message
	 * @param component
	 */
	public GraphComponentException(String message, GraphComponent component) {
		super(message);
		this.component = component;
	}

	public GraphComponent getComponent() {
		return component;
	}
	
}
