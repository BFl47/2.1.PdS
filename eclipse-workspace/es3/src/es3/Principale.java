package es3;

import java.util.Comparator;
import java.util.TreeSet;

import javax.swing.JOptionPane;

public class Principale {

	public static void main(String[] args) {
		Banca b = new Banca("UniCredit");
		
		Credito c = new Credito("C", "Mario", "R");
		Debito d = new Debito("D", "Gigi", "S");
		Credito e = new Credito("E", "Luca", "T");
		
		e.setCommissione(0.2);
		
		//b.conti.add(c);
		//c.banca = b;
		b.addConto(c);
		b.addConto(d);
		b.addConto(e);
	
		c.deposito(20);
		d.deposito(30);
		e.deposito(10);
		System.out.println(b);
		
		//b.conti.remove(c);
		//b.conti.add(c);
		c.deposito(15);
		d.deposito(12);
		d.riconosciInteresse(0.2);
		e.prelievo(2);
		e.prelievo(1);
		e.deposito(13);
		e.reset();
		e.deposito(5);
		System.out.println(b);
		
		Credito f = new Credito("F", "Franco", "T");
		b.addConto(f);
		f.deposito(35);
		System.out.println(b);
		
		TreeSet<ContoCorrente> t = new TreeSet<ContoCorrente>();
		t.add(c);
		t.add(d);
		t.add(e);
		t.add(f);
		System.out.println(t);
		
		ConfrontaConto m = new ConfrontaConto();
		int x = m.compare(c, d);		//-1
		
		TreeSet<ContoCorrente> t1 = new TreeSet<ContoCorrente>(m);
		t1.add(c);
		t1.add(d);
		t1.add(e);
		t1.add(f);
		System.out.println(t1);
		
		ConfrontaInverso m1 = new ConfrontaInverso();
		//m1.x = 35;
		TreeSet<ContoCorrente> t2 = new TreeSet<ContoCorrente>(m1);
		t2.add(c);
		t2.add(d);
		t2.add(e);
		t2.add(f);
		System.out.println(t2);
		
		Comparator<ContoCorrente> m2;
		
		String s = JOptionPane.showInputDialog("saldo inverso o codice?");
		
		if (s.equalsIgnoreCase("codice"))
			m2 = new ConfrontaConto();
		else {
			m2 = new ConfrontaInverso();
		}
		
		TreeSet<ContoCorrente> t3 = new TreeSet<ContoCorrente>(m2);
		t3.add(c);
		t3.add(d);
		t3.add(e);
		t3.add(f);
		System.out.println(t3);
		
		
		
	}

}
