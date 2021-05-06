import java.util.concurrent.Semaphore;

public class QueueSemaphore {

	private Semaphore semaphore;
	private MathBehavior[] behaviors = Starter.getBehaviorTable();
	private int place = 0;
	private int startingValue = 0;
	
	public QueueSemaphore(int maxHolders) {
		this.semaphore = new Semaphore(maxHolders);
	}
	
	boolean tryAquire(MathBehavior mathBehavior) {
		
		//System.out.println(behaviors[place].toString() + " " + mathBehavior.toString() + " current place: " + place);
		//We have to do the mod here, otherwise, the threads could run out of order, and cause an out of bounds index.
		if(!(behaviors[place % 5].toString() == mathBehavior.toString()))
			return false;
		
		if(semaphore.tryAcquire()) {
			return true;
		}

		return false;
	}
	
	void release() {
		place++;
		semaphore.release();
		//System.out.println("Released semaphore, slots avail: " + semaphore.availablePermits());
	}
	
	int getAvailableHolders() {
		return semaphore.availablePermits();
	}
	
	int getStartingValue() {
		return startingValue;
	}
	
	public void setStartingValue(int rand) {
		this.startingValue = rand;
	}
	
	public void setSemaphorePlace(int place) {
		this.place = place;
	}
}
