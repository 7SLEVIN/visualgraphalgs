package exceptions;

@SuppressWarnings("serial")
public class AlgorithmNotInitializedException extends AlgorithmException {
	
	public AlgorithmNotInitializedException() {
		super("Algorithm not initialized");
	}

}
