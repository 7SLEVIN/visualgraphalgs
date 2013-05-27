package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import utils.GraphicsUtils;

@SuppressWarnings("serial")
public class Edge extends GraphComponent {

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

		GraphicsUtils.drawArrow(g, this.color, 
				this.from.getPosition(), this.to.getPosition(), 
				new Coordinate(this.from.getRadius(), this.from.getRadius()));
		
		if (this.attribute != null && !this.attribute.equals("")) {
			Coordinate position = new Coordinate((this.from.getPosition().x + this.to.getPosition().x) / 2,
					(this.from.getPosition().y + this.to.getPosition().y) / 2);
			GraphicsUtils.drawText(g, this.color, new Font("Verdana", Font.PLAIN, 10), this.attribute, 
					position, new Coordinate(8 + this.from.getRadius(),this.from.getRadius()));
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

	public void setTo(Vertex to) {
		this.to = to;
	}

}
