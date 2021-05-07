import java.util.concurrent.Semaphore;

public class RandomNumber implements MathBehavior {
	
	/**
	 * @param int max - max amount the random number can be. 
	 * */
	public RandomNumber(int max) {
		this.max = max;
	}

	private int max = 0;
	
	/**
	 * @param Buffer inBuffer, Buffer outBuffer
	 * Generates a random number and stores it in output buffer.
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer) {

		int rand = (int) Math.floor(Math.random()*(max)) +1;
				
		//System.out.println("Starting rand = " + rand);
		inBuffer.setStartingValue(rand);
		outBuffer.write(rand);


		return 0;
	}

	@Override
	public String toString() {
		return "RandomNumber";
	}
	
}
