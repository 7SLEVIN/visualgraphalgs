package model.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import model.Coordinate;

import utils.GraphicsUtils;

@SuppressWarnings("serial")
public class Edge extends GraphComponent {
	
	private final static int ARROW_SIZE = 6;

	private Vertex from;
	private Vertex to;
	private EdgeType type;

	/**
	 * @param from
	 * @param to
	 */
	public Edge(Vertex from, Vertex to) {
		this(from, to, null);
	}

	/**
	 * @param from
	 * @param to
	 * @param attribute
	 */
	public Edge(Vertex from, Vertex to, String attribute) {
		super("");

		this.from = from;
		this.to = to;
		this.attribute = attribute;
		this.color = Color.black;
		this.type = EdgeType.Directed; // TODO
	}

	@Override
	public void paintComponent(Graphics g) {
		double r = Vertex.VERTEX_RADIUS;
		double d = this.to.getPosition().length(this.from.getPosition());
		double t = ((d-r)/d);
		
		Coordinate to = new Coordinate(
				this.from.getPosition().x + t * (this.to.getPosition().x - this.from.getPosition().x),
				this.from.getPosition().y + t * (this.to.getPosition().y - this.from.getPosition().y));
		
		GraphicsUtils.drawArrow(g, this.color, ARROW_SIZE,
				this.from.getPosition(), to, 
				new Coordinate(r, r));
		
		if (this.attribute != null && !this.attribute.equals("")) {
			double offset = 8;
			if (this.from.getPosition().x - this.to.getPosition().x > 0) offset = offset * -1;
			
			Coordinate position = new Coordinate(
					(this.from.getPosition().x + this.to.getPosition().x) / 2,
					(this.from.getPosition().y + this.to.getPosition().y) / 2);
			
			GraphicsUtils.drawText(g, this.color, new Font("Verdana", Font.PLAIN, 10),
					this.attribute, position, 
					new Coordinate(offset + r, offset + r));
		}
	}

	public void reset() {
		this.color = Color.black;
		this.visisted = false;
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

	public EdgeType getType() {
		return type;
	}

	public void setTo(Vertex to) {
		this.to = to;
	}

	@Override
	public Edge clone() {
		return new Edge(this.from.clone(), this.to.clone(), this.attribute);
	}
	
	@Override
	public String toString() {
		return String.format("%s from='%s' to='%s'", this.getClass().getName(), 
				this.from == null ? "" : this.from.getName(), 
						this.to == null ? "" : this.to.getName());
	}
	
}
