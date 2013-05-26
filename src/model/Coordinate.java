package model;

public class Coordinate {
	public int x;
	public int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Coordinate() {
		this(0, 0);
	}
	
	public void normalize(){
		int length = (int) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
		this.x = this.x/length;
		this.y = this.y/length;
	}
	
	public Coordinate clone() {
		return new Coordinate(this.x, this.y);
	}
}