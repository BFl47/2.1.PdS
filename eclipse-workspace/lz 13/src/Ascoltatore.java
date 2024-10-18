import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ascoltatore implements ActionListener {
	Esegui es = new Esegui();
	Thread t = new Thread(es);
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("scarica")) {
			System.out.println("scarica");
			//es.run();
			if (t.getState() == Thread.State.NEW)
				t.start();
		}
		if (e.getActionCommand().equals("stato")) {
			System.out.println("stato: " + t.getState() + " " + es.i);
		}
		if (e.getActionCommand().equals("congela")) {
			try {
				System.out.println("congela");
				t.join();
				
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("ferma")) {
			System.out.println("ferma");
			es.termina = true;
			t.interrupt();			
		}
	}
}
/*
if (e.getActinCommand().equals("scarica") {
	try {
		Socket s = new Socket("www.google.it", 80);
		PrintWriter r = new PrintWriter(s.getOutputStream());
		Scanner sc = new Scanner(s.getInputStream());
		r.print("GET / HTTP/1.1 \r\n");
		r.print("\r\n");
		r.flush();
		String o = sc.nextLine();
		System.out.println(o);
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}
*/