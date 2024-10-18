package es2;

abstract public class FiguraGeometrica {
	String descrizione;
	
	abstract public double area(); 
	/*
	public double area() {
		throw new RuntimeException("area f. geometrica");
		//return -1;
	}
	*/
	public String toString() {
		return this.descrizione;
	}
}
