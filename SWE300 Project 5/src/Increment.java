
public class Increment implements MathBehavior{

	@Override
	public int doOperation(Buffer inBuffer, Buffer outBuffer) {
		// TODO Auto-generated method stub
		synchronized(inBuffer) {

		int current = inBuffer.read();
		outBuffer.write(current + 1);
		}
		
		return 0;
	}

}
