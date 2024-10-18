package lz11;

import java.io.IOException;
import java.io.InputStream;

public class MioInputStream extends InputStream {
	InputStream in;
	
	public MioInputStream(InputStream i) {
		this.in = i;
	}
	
	@Override
	//filtro aggiungere lettere
	/*
	public int read() throws IOException {
		if (Math.random() > 0.5) 
			return 'a';
		return this.in.read();
	}
	*/
	
	//filtro rimuovere lettere
	public int read() throws IOException {
		int c = this.in.read();
		if (Math.random() > 0.9 && c != -1 && c != ' ')
			c = this.in.read();
		return c;	
	}
}
/*
	public int read() throws IOException {
	if (Math.random() > 0.9) 
		return -1; 		
		//return ' ';
	return 'a';
	}
*/