package lz10;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class Principale2 {
	
	public static void main(String[] args) {
		JFrame finestra = new JFrame("prova");
		
		
		JPanel pannello = new JPanel(new GridLayout(3, 3));
		finestra.add(pannello);
		//finestra.setLayout(new GridLayout(3,3));
		JButton a = new JButton("a");
		pannello.add(a);
		JButton b = new JButton("b");
		pannello.add(b);
		JButton c = new JButton("c");
		pannello.add(c);
		JButton d = new JButton("d");
		pannello.add(d);
		JButton e = new JButton("e");
		pannello.add(e);
		JButton f = new JButton("f");
		pannello.add(f);
		JButton g = new JButton("g");
		pannello.add(g);
		JButton h = new JButton("h");
		pannello.add(h);
		JButton i = new JButton("i");
		pannello.add(i);
		
		JTextArea area = new JTextArea(3, 2);
		area.append("area testo");
		finestra.add(area, BorderLayout.NORTH);
		
		finestra.pack();
		finestra.setVisible(true);
		
		Agisci o = new Agisci();
		o.testo = area;
		a.addActionListener(o);
		b.addActionListener(o);
		c.addActionListener(o);
		d.addActionListener(o);
		e.addActionListener(o);
		f.addActionListener(o);
		g.addActionListener(o);
		h.addActionListener(o);
		i.addActionListener(o);
		
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("fine main");
	}
}
