import java.util.concurrent.Semaphore;

public class Multiplier implements MathBehavior {

	/**
	 * @param int operand - number to multiply by
	 * */
	public Multiplier(int operand) {
		this.operand = operand;
	}

	private int operand = 0;
	
	/**
	 * @param Buffer inBuffer, Buffer outBuffer
	 * Multiplies the input Buffer by 3, and places it in the output buffer
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer) {

		//critical section
		int in = inBuffer.read();
		int finished = in * operand;
		outBuffer.write(finished);
		
		return 0;
	}

	@Override
	public String toString() {
		return "Multiplier";
	}
	
}
