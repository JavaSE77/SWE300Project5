import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.Test;

public class TestClass {

		
	@Test
	public void randomNumber() {
		Buffer buffer = new Buffer();
		MathBehavior mathBehavior = new RandomNumber(10000);
		QueueSemaphore semaphore = new QueueSemaphore(1);
		semaphore.setSemaphorePlace(0);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("Random number equals: " + value);
		assertNotEquals(value, 0);
		
		
	}
	

	@Test
	public void MultiplyBy3() {
		Buffer buffer = new Buffer();
		buffer.write(10);
		MathBehavior mathBehavior = new Multiplier(3);
		QueueSemaphore semaphore = new QueueSemaphore(1);
		semaphore.setSemaphorePlace(1);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("Multiply by 3 number equals: " + value);
		assertEquals(value, 30);
		
		
	}
	

	@Test
	public void add6() {
		Buffer buffer = new Buffer();
		buffer.write(10);
		MathBehavior mathBehavior = new Adder(6);
		QueueSemaphore semaphore = new QueueSemaphore(1);
		semaphore.setSemaphorePlace(2);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("Add 6 number equals: " + value);
		assertEquals(value, 16);
		
		
	}
	
	

	@Test
	public void devideBy3() {
		Buffer buffer = new Buffer();
		buffer.write(30);
		MathBehavior mathBehavior = new Division(3);
		QueueSemaphore semaphore = new QueueSemaphore(1);
		semaphore.setSemaphorePlace(3);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("divide by 3 number equals: " + value);
		assertEquals(value, 10);
		
		
	}
	
	@Test
	public void subtractBy0() {
		Buffer buffer = new Buffer();
		buffer.write(30);
		MathBehavior mathBehavior = new Subtractor();
		QueueSemaphore semaphore = new QueueSemaphore(1);
		semaphore.setSemaphorePlace(4);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("subtractor number equals: " + value);
		assertEquals(value, 30);
		
		
	}
	
	@Test
	public void testBuffer() {
		Buffer Buffer = new Buffer();
		Buffer.write(30);
		assertEquals(Buffer.read(), 30);
		
	}
	
	@Test
	public void fillBuffers() {
		int size = 10;
		Buffer buffers[] = new Buffer[size];
		buffers = Starter.fillBuffers(size);
		for(int i = 0; i < size; i++) {
			assertNotNull(buffers[i]);
		}
	}
	
	@Test
	public void testGroupOfBuffers() {
		
		Buffer buffers[] = new Buffer[10];
		assertEquals(buffers.length, 10);
		for(int i = 0; i < 10; i++) {
			buffers[i] = new Buffer(); 
		}
		buffers[0].write(30);
		for(int i = 1; i < 10; i++) {
			int value = buffers[i-1].read();
			assertNotEquals(value, 0);
			buffers[i].write(value);
		}
		assertEquals(buffers[9].read(), 30);
	}
	
	@Test
	public void testBufferSemaphore() {
		Buffer buffer = new Buffer();
		assertEquals(buffer.getPermits(), 0);
		buffer.write(1);
		assertEquals(buffer.getPermits(), 1);
		
	}
	
	
	@Test
	public void testSingleThread() {
		Buffer buffer = new Buffer();
		buffer.unlockBuffer();

		MathBehavior mathBehavior = new RandomNumber(10000);
		QueueSemaphore semaphore = new QueueSemaphore(1);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		int startingValue = buffer.read();
		//multiply by 3
		mathBehavior = new Multiplier(3);
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//add 6
		mathBehavior = new Adder(6);
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//divide by 3
		mathBehavior = new Division(3);
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
		buffer.unlockBuffer();

		MathBehavior mathBehavior = new RandomNumber(10000);
		QueueSemaphore semaphore = new QueueSemaphore(1);
		
		mathBehavior.doOperation(buffer, buffer, semaphore);
		int startingValue = buffer.read();
		//multiply by 3
		mathBehavior = new Multiplier(3);
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//add 6
		mathBehavior = new Adder(6);
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//divide by 3
		mathBehavior = new Division(3);
		mathBehavior.doOperation(buffer, buffer, semaphore);
		//subtract 0
		mathBehavior = new Subtractor();
		mathBehavior.doOperation(buffer, buffer, semaphore);
		
		int value = buffer.read();
		System.out.println("We started with " + startingValue + " and ended with " + value);
		assertNotEquals(value, 0);
		
	}
	
}
