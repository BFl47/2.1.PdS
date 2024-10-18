package lz10;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
//import java.awt.*;
import javax.swing.*;

public class Principale {

	public static void main(String[] args) {
		JFrame finestra = new JFrame("titolo");
		finestra.getContentPane().setLayout(new FlowLayout());
		//finestra.setLayout(new GridLayout(2,1));
		JButton b = new JButton("ok");
		finestra.add(b, BorderLayout.CENTER);
		JButton c = new JButton("no");
		finestra.add(c, BorderLayout.SOUTH);
		
		JPanel pannello = new JPanel();
		finestra.add(pannello, BorderLayout.SOUTH);
		//pannello.setLayout(new BorderLayout());
		pannello.add(b, BorderLayout.NORTH);
		pannello.add(c, BorderLayout.SOUTH);
		
		//finestra.add(new JButton("a"), BorderLayout.EAST);
		//finestra.add(new JButton("b"), BorderLayout.WEST);
		//finestra.add(new JButton("c"), BorderLayout.NORTH);
		//finestra.add(new JButton("d"), BorderLayout.SOUTH);
		
		JTextArea a = new JTextArea(10,10);
		finestra.add(a, BorderLayout.NORTH);
		a.append("inizio");		
		
		finestra.pack();
		finestra.setVisible(true);
		
		Agisci o = new Agisci();
		o.testo = a;
		//b.addActionListener(null);
		b.addActionListener(o);
		
		AgisciNo n = new AgisciNo();
		c.addActionListener(n);
		c.addActionListener(o);
		
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("fine main");

	}

}
