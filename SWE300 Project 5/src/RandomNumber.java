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
	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore) {
		// TODO Auto-generated method stub
		
		while(true) {
			if( semaphore.tryAquire(this)) { 
				
				int rand = (int) Math.floor(Math.random()*(max)) +1;
				
				//System.out.println("Starting rand = " + rand);
				outBuffer.write(rand);
				semaphore.setStartingValue(rand);
				semaphore.release();
				break;
			} else {
				try {
					int rand = (int) Math.floor(Math.random()*Starter.MAX_WAIT_FOR_SEM);
					Thread.sleep(rand);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}

	@Override
	public String toString() {
		return "RandomNumber";
	}
	
}
