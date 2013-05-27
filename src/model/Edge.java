package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
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
		super("");
		
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
		
		if (this.attribute != null && !this.attribute.equals("")) {
			Font font = new Font("Verdana", Font.PLAIN, 10);
			g2.setFont(font);
			g2.setColor(Color.black);
			int width = g2.getFontMetrics().stringWidth(this.attribute);
			FontRenderContext fr = g2.getFontRenderContext();
			LineMetrics lm = font.getLineMetrics(this.attribute, fr);
			int height = (int) (lm.getAscent() * .76 + .5); // Compensate for
															// overstating
			// x,y is bottom left corner of text
			g2.drawString(this.attribute, 
					(this.from.getPosition().x + this.to.getPosition().x) / 2 + this.from.getRadius() - width+3, 
					(this.from.getPosition().y + this.to.getPosition().y) / 2 + this.from.getRadius() + height+3);
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
