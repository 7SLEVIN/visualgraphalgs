package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import utils.GraphicsUtils;

@SuppressWarnings("serial")
public class Edge extends GraphComponent {
	
	private final static int ARROW_SIZE = 6;

	private Vertex from;
	private Vertex to;

	/**
	 * @param from
	 * @param to
	 */
	public Edge(Vertex from, Vertex to) {
		super("");

		this.from = from;
		this.to = to;
		this.color = Color.black;
	}

	@Override
	public void paintComponent(Graphics g) {
//		GraphicsUtils.drawLine(g, this.color, this.from.getPosition(), this.to.getPosition(), 
//				new Coordinate(this.from.getRadius(), this.from.getRadius()));
		
		double radius = this.from.getRadius();
		
//		double length = this.to.getPosition().length(this.from.getPosition()) - radius;
		
		Coordinate vector = this.from.getPosition().clone();
		vector.normalize();
//		System.out.println(String.format("%s: (%s,%s)", this.from.getName(), vector.x, vector.y));
		vector.x = vector.x * radius; vector.y = vector.y * radius;
//		System.out.println(String.format("New %s: (%s,%s)", this.from.getName(), vector.x, vector.y));
		
		Coordinate to = new Coordinate(this.to.getPosition().x - vector.x,
				this.to.getPosition().y - vector.y);
		
		GraphicsUtils.drawArrow(g, this.color, ARROW_SIZE,
				this.from.getPosition(), to, 
				new Coordinate(radius, radius));
		
		if (this.attribute != null && !this.attribute.equals("")) {
			Coordinate position = new Coordinate(
					(this.from.getPosition().x + this.to.getPosition().x) / 2,
					(this.from.getPosition().y + this.to.getPosition().y) / 2);
			GraphicsUtils.drawText(g, this.color, new Font("Verdana", Font.PLAIN, 10),
					this.attribute, position, 
					new Coordinate(8 + radius, radius));
		}
	}

	public void reset() {
		this.color = Color.black;
		this.visisted = false;
	}
	
	@Override
	public String toString() {
		return String.format("%s from='%s' to='%s'", this.getClass().getName(), 
				this.from.getName(), this.to.getName());
	}

	public Vertex getFrom() {
		return from;
	}

	public void setFrom(Vertex from) {
		this.from = from;
	}

	public Vertex getTo() {
		return to;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

}
