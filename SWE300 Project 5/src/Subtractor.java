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
	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore) {
		
		while(true) {
			if( semaphore.tryAquire(this)) { 
				  //critical section
				int start = inBuffer.read();
				
				int finished = start - semaphore.getStartingValue();
				
				outBuffer.write(finished);

				//System.out.println("Subtractor");
				semaphore.release();
				break;
			}  else {
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
		return "Subtractor";
	}
	
}
