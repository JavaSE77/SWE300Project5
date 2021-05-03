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
	private MathBehavior[] behaviors =
	{ new RandomNumber(), new Multiplier(), new Adder(), new Division(), new Subtractor()};
	private Buffer buffer;
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
		buffer = new Buffer();
		buffer.write(1);
		this.semaphore = new QueueSemaphore(1);
		for (int i = 0; i < behaviors.length; i++)
		{

			

			threads[i] = new Modifier(i, buffer, buffer, behaviors[i], semaphore);
			threads[i].start();

		}
		for (int i = 0; i < threads.length; i++)
		{
			threads[i].join();
		}
		ConstantChecker checker = new ConstantChecker((buffer), 2);
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

}
