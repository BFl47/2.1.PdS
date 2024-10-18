
public class Counter {
	private int value;
	public Counter() {
		value = 0;
	}
	public int getValue() {
		return value;
	}
	public void inc() {
		value++;
	}
	public void reset() {
		value = 0;
	}
	//New -> JUnit Test Case -> impostare JUnit 4
	//run as -> JUnit Test (Fixture che si intende eseguire)
}
