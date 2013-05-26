package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Edge extends JComponent {

	private Vertex from;
	private Vertex to;
	private Color color;
	
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
		g.setColor(this.color);
		g.drawLine(this.from.getPosition().x, this.from.getPosition().y,
				this.to.getPosition().x, this.to.getPosition().y);
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
