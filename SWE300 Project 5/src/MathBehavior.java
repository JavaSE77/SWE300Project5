import java.util.concurrent.Semaphore;

public interface MathBehavior {

	public int doOperation(Buffer inBuffer, Buffer outBuffer, QueueSemaphore semaphore);
	
	@Override
	public String toString();
	
}
