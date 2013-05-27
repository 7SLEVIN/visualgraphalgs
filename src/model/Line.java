package model;

import java.awt.Graphics;

@SuppressWarnings("serial")
public class Line extends GraphComponent {
	
	private Coordinate from;
	private Coordinate to;
	
	/**
	 * @param from
	 * @param to
	 */
	public Line(Coordinate from, Coordinate to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(this.color);
        g.drawLine(this.from.x, this.from.y, this.to.x, this.to.y);
	}

	public Coordinate getFrom() {
		return from;
	}

	public Coordinate getTo() {
		return to;
	}
	

}
