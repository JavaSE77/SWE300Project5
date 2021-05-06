import java.util.concurrent.Semaphore;

/**
 * a data store between two threads
 * 
 * @author Merlin
 *
 */
public class Buffer
{
//	We might not need this....
//	/* int controls how many readers/writers the buffer can have. 
//	 * If set to zero, then the buffer must be unlocked before it can be written to. 
//	 * By default, reading from a buffer will unlock it. 
//	 */
//	public Buffer(int i) {
//		
//	}
	
	private int x;
	
	
	/*
	 * These semaphores control the reading and writing of the buffers.
	 * */
	private Semaphore semaphore = new Semaphore(0);


	/**
	 * write an int into this buffer
	 * 
	 * @param x
	 *            the int we should store
	 */
	public void write(int x)
	{

		System.out.println("Number of semaphore locks held is: " + semaphore.availablePermits());
		semaphore.release();
		System.out.println("\nNumber of semaphore locks held is: " + semaphore.availablePermits());
		  this.x = x;

	}

	/**
	 * @return the next int in the buffer
	 */
	public int read()
	{
		while(true) {
			if(semaphore.tryAcquire()) {
				return x;
			}
		}
			
		
	}
	
	
	/**
	 * @return the number of available permits.
	 * */
	public int getPermits() {
		return semaphore.availablePermits();
	}
	
	/**
	 * ONLY FOR JUNIT TESTS
	 * Unlocks the buffer to allow 100 actions of reading and writing with no syncing!
	 * */
	public void unlockBuffer() {
		semaphore.release(100);
	}

	
}
