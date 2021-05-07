import java.util.concurrent.Semaphore;

/**
 * Reads from an input buffer, increments and writes to an output buffer unless
 * it is number 0. In that case, it just writes increasing integers to the
 * output buffer
 * 
 * @author Merlin
 *
 */
public class Modifier extends Thread
{
	private Integer myNum;
	private Buffer inBuffer;
	private Buffer outBuffer;
	private MathBehavior mathBehavior;
	   //do something in here.

	/**
	 * Create an incrementer
	 * 
	 * @param myNum
	 *            ignored unless it is zero
	 * @param inBuffer
	 *            the buffer to read from
	 * @param outBuffer
	 *            the buffer to write to
	 */
	public Modifier(Integer myNum, Buffer inBuffer, Buffer outBuffer, MathBehavior mathBehavior)
	{
		synchronized(this) {
			this.myNum = myNum;
			this.inBuffer = inBuffer;
			this.outBuffer = outBuffer;
			this.mathBehavior = mathBehavior;
		}
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{

		for (int i = 0; i < Starter.NUMBER_OF_TRIALS; i++)
		{
			if(i % 100 == 0 && mathBehavior.toString() == "RandomNumber") {
				System.out.println("Starting trial: " + i);
			}
			
			mathBehavior.doOperation(inBuffer, outBuffer);
			outBuffer.setStartingValue(inBuffer.getStartingValue());
		}

	}

}
