package exceptions;


@SuppressWarnings("serial")
public class MalformedGraphException extends GraphException {

	/**
	 * @param message
	 */
	public MalformedGraphException(String message) {
		super(message, null);
	}

}
