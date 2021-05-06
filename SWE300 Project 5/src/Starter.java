import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Semaphore;

/**
 * @author Merlin
 *
 * 
 */
public class Starter
{

	/**
	 * The number of iterations each behavior should do
	 */
	public static final int NUMBER_OF_TRIALS = 10000;
	public static final int MAX_WAIT_FOR_SEM = 2;
	//This is static so other classes can see the table through our getter method. 
	private static MathBehavior[] behaviors =
		{ new RandomNumber(10000), new Multiplier(3), new Adder(6), new Division(3), new Subtractor()};

	private QueueSemaphore semaphore;

	/**
	 * spawn off all of the behaviors giving them appropriate input and output
	 * buffers
	 * 
	 * 
	 * @throws ClassNotFoundException
	 *             shouldn't
	 * @throws NoSuchMethodException
	 *             shouldn't
	 * @throws SecurityException
	 *             shouldn't
	 * @throws InstantiationException
	 *             shouldn't
	 * @throws IllegalAccessException
	 *             shouldn't
	 * @throws IllegalArgumentException
	 *             shouldn't
	 * @throws InvocationTargetException
	 *             shouldn't
	 * @throws InterruptedException
	 *             shouldn't
	 */
	public Starter() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InterruptedException
	{
		Thread threads[] = new Thread[behaviors.length];
		Buffer buffers[] = new Buffer[behaviors.length +1];
		buffers = fillBuffers(behaviors.length +1);
		this.semaphore = new QueueSemaphore(1);
		for (int i = 0; i < behaviors.length; i++)
		{

			
			threads[i] = new Modifier(i, buffers[i], buffers[i +1], behaviors[i], semaphore);
			threads[i].start();

		}
		for (int i = 0; i < threads.length; i++)
		{
			threads[i].join();
		}
		ConstantChecker checker = new ConstantChecker((buffers[behaviors.length +1]), 2);
		checker.check();

	}

	/**
	 * @param args
	 *            none
	 * @throws InvocationTargetException
	 *             shouldn't
	 * @throws IllegalArgumentException
	 *             shouldn't
	 * @throws IllegalAccessException
	 *             shouldn't
	 * @throws InstantiationException
	 *             shouldn't
	 * @throws SecurityException
	 *             shouldn't
	 * @throws NoSuchMethodException
	 *             shouldn't
	 * @throws ClassNotFoundException
	 *             shouldn't
	 * @throws InterruptedException
	 *             shouldn't
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InterruptedException
	{

		new Starter();

	}
	
	/**
	 * Returns the mathBehavior table. Sorry this is static
	 * */
	public static MathBehavior[] getBehaviorTable() {
		return behaviors;
	}
	
	/**
	 * @param int size - the size of the array of buffers.
	 * Returns an array of buffers of length size
	 * buffers is static so other methods can use it.
	 * */
	public static Buffer[] fillBuffers(int size) {
		Buffer[] buffers = new Buffer[size];
		for( int i = 0; i < size; i++) {
			buffers[i] = new Buffer();
		}
		return buffers;
	}

}
