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
	private int startingValue;
	
	
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
		

		this.x = x;
		semaphore.release();

	}

	/**
	 * @return the next int in the buffer
	 */
	public int read()
	{
		while(true) {
			if(semaphore.tryAcquire()) {
				return x;
			} else {
				try {
					//We have to delay it, otherwise it breaks. Not sure why
					Thread.sleep(Starter.MAX_WAIT_FOR_SEM);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

	
	/**
	 * @param int startingValue - this is the value given by the random number class
	 * */
	public void setStartingValue(int startingValue) {
		this.startingValue = startingValue;
	}
	
	
	/**
	 * Returns the value initially set by the random number class
	 * */
	public int getStartingValue() {
		return this.startingValue;
	}
	
	
}
