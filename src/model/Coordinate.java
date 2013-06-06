package model;

public class Coordinate {
	
	public double x;
	public double y;

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate() {
		this(0, 0);
	}
	
	public double length(Coordinate origin) {
		double x = this.x - origin.x;
		double y = this.y - origin.y;
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public void normalize() {
		double length = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		this.x = this.x/length;
		this.y = this.y/length;
	}
	
	public Coordinate clone() {
		return new Coordinate(this.x, this.y);
	}
}
