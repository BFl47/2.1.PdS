
public class Eseguibile implements Runnable {
	int n;
	Contatore contatore;
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++)
			contatore.incrementa();
	}
}
/*
  	int in;
	int out;
	
	@Override
	public void run() {
		System.out.println("inizio eseguibile " + this.in);
		this.out = this.in + 1;
		System.out.println("fine eseguibile " + this.in);
	}
*/