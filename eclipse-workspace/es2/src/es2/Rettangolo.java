package es2;

public class Rettangolo extends FiguraGeometrica {
	private double base;
	private double altezza;
	
	public Rettangolo(double b, double a) {
		if (b < 0 || a < 0) 
			throw new RuntimeException("lato negativo");
		this.base = b;
		this.altezza = a;
	}
	
	public double area() {
		return this.base * this.altezza;
	}
	
	public double perimetro() {
		return (this.base + this.altezza) * 2;
	}
	
	public String toString() {
		return super.toString() + " " + this.base + " " + this.altezza;
	}
}
