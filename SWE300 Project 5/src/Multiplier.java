import java.util.concurrent.Semaphore;

public class Multiplier implements MathBehavior {

	/**
	 * @param Buffer inBuffer, Buffer outBuffer
	 * Multiplies the input Buffer by 3, and places it in the output buffer
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore) {

		while(true) {
			if( semaphore.tryAquire(this)) { 
				  //critical section
				int in = inBuffer.read();
				int finished = in * 3;
				outBuffer.write(finished);
				//System.out.println("Multiplier");
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
		return "Multiplier";
	}
	
}
