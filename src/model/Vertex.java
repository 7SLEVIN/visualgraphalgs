package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;

@SuppressWarnings("serial")
public class Vertex extends GraphComponent {

	private String name;
	private Coordinate position;
	private int radius;
	
	/**
	 * @param name
	 * @param position
	 */
	public Vertex(String name, Coordinate position) {
		this.name = name;
		this.position = position;
		this.radius = 20;
		this.color = Color.lightGray;
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;
    	
    	Shape circle = new Ellipse2D.Float(this.position.x, this.position.y,
    			this.radius*2, this.radius*2);
    	g2d.draw(circle);
    	g2d.setPaint(this.color);
    	g2d.fill(circle);
    	
    	Font font = new Font("Verdana", Font.PLAIN, 20);
    	g2d.setFont(font);
    	g2d.setColor(Color.black);
		int width = g2d.getFontMetrics().stringWidth(this.name);
		FontRenderContext fr = g2d.getFontRenderContext();
		LineMetrics lm = font.getLineMetrics(this.name, fr);
		int height = (int) (lm.getAscent() * .76 + .5); // Compensate for overstating
		// x,y is bottom left corner of text
		g2d.drawString(this.name, this.position.x + this.radius - width / 2,
				this.position.y + this.radius + height / 2);
    }

//	private static void drawCenteredString(final Graphics2D g2d,
//			final String text, final int x, final int y) {
//		final Font font = g2d.getFont();
//		int width = g2d.getFontMetrics().stringWidth(text);
//		final FontRenderContext fr = g2d.getFontRenderContext();
//		final LineMetrics lm = font.getLineMetrics(text, fr);
//		final int height = (int) (lm.getAscent() * .76 + .5); // Compensate for overstating
//		// x,y is bottom left corner of text
//		g2d.drawString(text, x - width / 2, y + height / 2);
//	}

	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
