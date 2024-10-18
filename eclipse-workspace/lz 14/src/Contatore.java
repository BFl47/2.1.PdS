
public class Contatore {
	int x;
	synchronized public void incrementa(){
		int temp = this.x;
		try {
			if (true)
				Thread.sleep(100);
		} catch (InterruptedException e) {}
		this.x = temp+1;
	}
}
/*	a)
 	int x;
	public void incrementa(){
		int temp = this.x;
		try {
			if (true)
				Thread.sleep(100);
		} catch (InterruptedException e) {}
		this.x = temp+1;
	}
	
	b)
	public void incrementa(){
		synchronized ("x") {
			int temp = this.x;
			try {
				if (true)
					Thread.sleep(100);
			} catch (InterruptedException e) {}
			this.x = temp+1;
		}
	}
*/