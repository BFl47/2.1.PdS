package es6;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket canale = new Socket("www.google.it", 80);
		Scanner s = new Scanner(canale.getInputStream()); 
		PrintWriter p1 = new PrintWriter(canale.getOutputStream());
		String r;
		
		FileWriter f = new FileWriter("google.html");
		PrintWriter p2 = new PrintWriter(f);
		
		p1.print("GET / HTTP/1.1\r\nHost: www.google.it\r\n\r\n");
		p1.flush();
		
		while(s.hasNextLine()) {
			r = s.nextLine();
			System.out.println(r);
			p2.println(r);
			p2.flush();
		}
		
		p2.close();
		canale.close();
	}

}

