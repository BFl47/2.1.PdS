
public class Principale {

	public static void main(String[] args) throws InterruptedException {
		Eseguibile e1 = new Eseguibile();
		Eseguibile e2 = new Eseguibile();
		
		//	1
		/*
		e1.in = 12;
		e1.run();
		e2.run();
		System.out.println(e1.out);			//13
		*/
		
		//	2
		/*
		e1.in = 1;
		e2.in = 2;
		Thread t1 = new Thread(e1);
		Thread t2 = new Thread(e2);
		//System.out.println(e1.out);		//0
		t1.start();
		t2.start();
		System.out.println(t1.getState());
		t1.join();
		//System.out.println(e1.out);		//2
		*/
		
		//3
		Contatore c = new Contatore();
		e1.contatore = c;
		e2.contatore = c;
		Thread t1 = new Thread(e1);
		Thread t2 = new Thread(e2);
		t1.start();
		t2.start();
		//System.out.println(c.x);
		t1.join();
		t2.join();
		System.out.println(c.x);	
		//a) true -> 10, false -> 20
		//b), c) -> 20
	}
}
