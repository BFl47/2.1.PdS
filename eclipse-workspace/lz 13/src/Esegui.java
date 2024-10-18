
public class Esegui implements Runnable {
	int i;
	boolean termina;
	@Override
	public void run() {
		System.out.println("partito");
		try {
			for (i = 0; i < 5; i++) {
				System.out.println(i);
				Thread.sleep(5000);
				if (termina)
					return;
			}
		} catch (InterruptedException e) {
			System.out.println("interrotto, sleep n.: " + i);
		}
		System.out.println("finito");

	}

}
