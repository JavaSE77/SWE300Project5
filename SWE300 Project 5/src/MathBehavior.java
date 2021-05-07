import java.util.concurrent.Semaphore;

public interface MathBehavior {

	public int doOperation(Buffer inBuffer, Buffer outBuffer);
	
	@Override
	public String toString();
	
}
