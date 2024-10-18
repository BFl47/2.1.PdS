package prova2;

import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;

public class Eseguibile implements Runnable {
	//PrintWriter p;
	//Scanner s;
	JTextArea area_info;
	JButton disconnect, get, stop;
	
	@Override
	public void run() {
		String r = Ascoltatore.s.nextLine();
		area_info.append("--DOWNLOAD STARTED--");
		area_info.append("\n");
		
		while (!r.equals("END")) {
			r = Ascoltatore.s.nextLine();
			System.out.println(r);
			if (r.equals("END")) {
				area_info.append("--DOWNLOAD ENDED--");
				area_info.append("\n");
				break;
			}
			if (r.equals("INTERRUPTED")) {
				area_info.append("--DOWNLOAD INTERRUPTED--");
				area_info.append("\n");
				break;
			}
			if (!r.equals("START")) {
				area_info.append(r);
				area_info.append("\n");
			}
		}
		
		disconnect.setEnabled(true);
		get.setEnabled(true);
		stop.setEnabled(false);

	}

}
