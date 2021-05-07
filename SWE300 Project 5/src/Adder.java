import java.util.concurrent.Semaphore;

public class Adder implements MathBehavior{

	/**
	 * @param int operand - number to multiply by
	 * */
	public Adder(int operand) {
		this.operand = operand;
	}

	private int operand = 0;
	
	/**
	 * @param Buffer inBuffer, Buffer outBuffer
	 * Takes the value in the input buffer and adds 6 to it, then stores the result in output buffer
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer) {
	 
		//critical section
		int start = inBuffer.read();
						
		int finished = start + operand;
						
		outBuffer.write(finished);
				
		return 0;
	}
	
	@Override
	public String toString() {
		return "Adder";
	}
}
