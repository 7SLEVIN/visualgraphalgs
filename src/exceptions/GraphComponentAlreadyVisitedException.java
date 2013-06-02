package exceptions;

import model.GraphComponent;

@SuppressWarnings("serial")
public class GraphComponentAlreadyVisitedException extends
		GraphComponentException {
	
	/**
	 * @param component
	 */
	public GraphComponentAlreadyVisitedException(GraphComponent component) {
		super("GraphComponent is already visited", component);
	}

}
