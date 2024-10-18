import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CounterTest {
	private Counter counter;
	
	@Before
	public void setup() {
		counter = new Counter();
	}
	@Test
	public void testCounter() {
		int expected = 0;
		assertEquals(expected, counter.getValue());
	}

	@Test
	public void testInc() {
		int expected = 2;
		counter.inc();
		counter.inc();
		assertEquals(expected, counter.getValue());
	}

	@Test
	public void testReset() {
		int expected = 0;
		counter.inc();
		counter.reset();
		assertEquals(expected, counter.getValue());
	}

}
