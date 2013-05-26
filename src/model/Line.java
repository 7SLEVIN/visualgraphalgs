package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Line extends JComponent {
	
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
        g.setColor(Color.black);
        g.drawLine(this.from.x, this.from.y, this.to.x, this.to.y);
	}

}
