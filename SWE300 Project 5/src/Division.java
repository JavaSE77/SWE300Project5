import java.util.concurrent.Semaphore;

public class Division implements MathBehavior {

	/**
	 * @param int operand - number to divide by
	 * */
	public Division(int operand) {
		this.operand = operand;
	}

	private int operand = 0;
	
	/**
	 * @param Buffer inBuffer, Buffer outBuffer
	 * Takes the value in inbuffer and devides it by 3, restoring the result in outBuffer
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer) {

		//critical section
		int in = inBuffer.read();
		int finished = in / operand;
		outBuffer.write(finished);
		
		return 0;
	}
	
	@Override
	public String toString() {
		return "Division";
	}
	
}
