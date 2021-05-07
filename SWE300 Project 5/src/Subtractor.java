import java.util.concurrent.Semaphore;

public class Subtractor implements MathBehavior{

	/*
	 * Subtractor does not need a constructor, because it gets its operation value from the semaphore.
	 * */
	
	/**
	 * @param Buffer inBuffer, Buffer outBuffer, int operand
	 * Step 5 in the math game. 
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer) {
		//critical section
		int start = inBuffer.read();
				
		int finished = start - inBuffer.getStartingValue();
				
		outBuffer.write(finished);
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "Subtractor";
	}
	
}
