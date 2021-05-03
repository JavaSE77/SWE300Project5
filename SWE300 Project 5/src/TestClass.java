import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

public class TestClass {

//	@Test
//	public void randomNumber() {
//		Buffer inBuffer = new Buffer();
//		Buffer outBuffer = new Buffer();
//		MathBehavior mathBehavior = new RandomNumber();
//		QueueSemaphore semaphore = new QueueSemaphore(1);
//		
//		mathBehavior.doOperation(inBuffer, outBuffer, semaphore);
//		
//		int value = outBuffer.read();
//		System.out.println("Random number equals: " + value);
//		assertNotEquals(value, 0);
//		
//		
//	}
//	
//
//	@Test
//	public void MultiplyBy3() {
//		Buffer inBuffer = new Buffer();
//		inBuffer.write(10);
//		Buffer outBuffer = new Buffer();
//		MathBehavior mathBehavior = new Multiplier();
//		QueueSemaphore semaphore = new QueueSemaphore(1);
//		
//		mathBehavior.doOperation(inBuffer, outBuffer, semaphore);
//		
//		int value = outBuffer.read();
//		System.out.println("Multiply by 3 number equals: " + value);
//		assertEquals(value, 30);
//		
//		
//	}
//	
//
//	@Test
//	public void add6() {
//		Buffer inBuffer = new Buffer();
//		inBuffer.write(10);
//		Buffer outBuffer = new Buffer();
//		MathBehavior mathBehavior = new Adder();
//		QueueSemaphore semaphore = new QueueSemaphore(1);
//		
//		mathBehavior.doOperation(inBuffer, outBuffer, semaphore);
//		
//		int value = outBuffer.read();
//		System.out.println("Add 6 number equals: " + value);
//		assertEquals(value, 16);
//		
//		
//	}
//	
//	
//
//	@Test
//	public void devideBy3() {
//		Buffer inBuffer = new Buffer();
//		inBuffer.write(30);
//		Buffer outBuffer = new Buffer();
//		MathBehavior mathBehavior = new Division();
//		QueueSemaphore semaphore = new QueueSemaphore(1);
//		
//		mathBehavior.doOperation(inBuffer, outBuffer, semaphore);
//		
//		int value = outBuffer.read();
//		System.out.println("divide by 3 number equals: " + value);
//		assertEquals(value, 10);
//		
//		
//	}
//	
//	@Test
//	public void subtractBy0() {
//		Buffer inBuffer = new Buffer();
//		inBuffer.write(30);
//		Buffer outBuffer = new Buffer();
//		MathBehavior mathBehavior = new Subtractor();
//		QueueSemaphore semaphore = new QueueSemaphore(1);
//		
//		mathBehavior.doOperation(inBuffer, outBuffer, semaphore);
//		
//		int value = outBuffer.read();
//		System.out.println("subtractor number equals: " + value);
//		assertEquals(value, 30);
//		
//		
//	}
	
	@Test
	public void testSingleThread() {
		Buffer buffer = new Buffer();

		MathBehavior mathBehavior = new RandomNumber();
		QueueSemaphore semaphore = new QueueSemaphore(1);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		int startingValue = buffer.read();
		//multiply by 3
		mathBehavior = new Multiplier();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//add 6
		mathBehavior = new Adder();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//divide by 3
		mathBehavior = new Division();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//subtract 0
		mathBehavior = new Subtractor();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("We started with " + startingValue + " and ended with " + value);
		assertNotEquals(value, 0);
		
	}
	
	
	@Test
	public void testSingleThreadWithSemaphore() {
		Buffer buffer = new Buffer();

		MathBehavior mathBehavior = new RandomNumber();
		QueueSemaphore semaphore = new QueueSemaphore(1);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		int startingValue = buffer.read();
		//multiply by 3
		mathBehavior = new Multiplier();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//add 6
		mathBehavior = new Adder();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//divide by 3
		mathBehavior = new Division();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//subtract 0
		mathBehavior = new Subtractor();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("We started with " + startingValue + " and ended with " + value);
		assertNotEquals(value, 0);
		
	}
	
}
