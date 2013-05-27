package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

@SuppressWarnings("serial")
public class Edge extends GraphComponent {

	private Vertex from;
	private Vertex to;
		
	/**
	 * @param from
	 * @param to
	 */
	public Edge(Vertex from, Vertex to) {
		this.from = from;
		this.to = to;
		this.color = Color.black;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
		g2.setColor(this.color);
		g2.draw(new Line2D.Float(this.from.getPosition().x + this.from.getRadius(),
				this.from.getPosition().y + this.from.getRadius(),
				this.to.getPosition().x + this.from.getRadius(),
				this.to.getPosition().y + this.from.getRadius()));
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
