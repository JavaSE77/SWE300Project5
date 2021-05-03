import java.util.concurrent.Semaphore;

public class Increment implements MathBehavior {

	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore) {
		// TODO Auto-generated method stub
		synchronized(inBuffer) {

		int current = inBuffer.read();
		outBuffer.write(current + 1);
		}
		
		return 0;
	}

}
