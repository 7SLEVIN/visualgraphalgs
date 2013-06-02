package exceptions;

@SuppressWarnings("serial")
public class AlgorithmAlreadyFinishedException extends AlgorithmException {
	
	public AlgorithmAlreadyFinishedException() {
		super("Algorithm already finished");
	}

}
