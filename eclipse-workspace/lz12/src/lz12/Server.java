package lz12;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		System.out.println("Server");
		ServerSocket ss = new ServerSocket(8080);
		
		System.out.println("attesa connessione");
		/*
		Socket canale = ss.accept();
		System.out.println("connesso");
		Scanner s = new Scanner(canale.getInputStream());
		PrintWriter p = new PrintWriter(canale.getOutputStream());
		String r = s.nextLine();
		System.out.println("ricevuto dal client: " + r);
		p.println("riciao");
		p.flush();
		ss.close();
		*/
		
		Socket canale;
		Scanner s;
		PrintWriter p;
		String r;
		
		while (true) {
			canale = ss.accept();
			System.out.println("connesso");
			s = new Scanner(canale.getInputStream());
			p = new PrintWriter(canale.getOutputStream());
			r = s.nextLine();
			System.out.println("ricevuto dal client: " + r);
			p.println("riciao");
			p.flush();
		}

	}

}
