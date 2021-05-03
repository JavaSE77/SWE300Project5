import java.util.concurrent.Semaphore;

public class Adder implements MathBehavior{

	/**
	 * @param Buffer inBuffer, Buffer outBuffer
	 * Takes the value in the input buffer and adds 6 to it, then stores the result in output buffer
	 * */
	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore) {
		
		while(true) {
			if( semaphore.tryAquire(this)) { 
				  //critical section
				int start = inBuffer.read();
				
				int finished = start + 6;
				
				outBuffer.write(finished);

				//System.out.println("Adder");
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
		return "Adder";
	}
}
