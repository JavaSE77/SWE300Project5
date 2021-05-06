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
	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore) {
		// TODO Auto-generated method stub
		
		while(true) {
			if( semaphore.tryAquire(this)) { 
				  //critical section
				int in = inBuffer.read();
				int finished = in / operand;
				outBuffer.write(finished);
				//System.out.println("Division");
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
		return "Division";
	}
	
}
