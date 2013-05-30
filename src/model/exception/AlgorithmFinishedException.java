package model.exception;

@SuppressWarnings("serial")
public class AlgorithmFinishedException extends Exception {
	
	public AlgorithmFinishedException() {
		super("Cannot run algorithm that is already finished");
	}

}
