package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import model.Coordinate;

public class GraphicsUtils {
	
	public static void drawArrow(Graphics g, Color color, int size, 
			Coordinate from, Coordinate to, Coordinate offset) {
        Graphics2D g2 = (Graphics2D) g.create();

		g2.setStroke(new BasicStroke(2));
        g2.setColor(color);
        
        double dx = to.x - from.x, dy = to.y - from.y;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(from.x + offset.x, from.y + offset.y);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g2.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g2.drawLine(0, 0, len, 0);
        g2.fillPolygon(new int[] {len, len-size, len-size, len},
        		new int[] {0, -size, size, 0}, 4);
    }
	
	public static void drawText(Graphics g, Color color, Font font, String text,
			Coordinate position, Coordinate offset) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setFont(font);
		g2.setColor(Color.black);
		
		int width = g2.getFontMetrics().stringWidth(text);
		FontRenderContext fr = g2.getFontRenderContext();
		LineMetrics lm = font.getLineMetrics(text, fr);
		int height = (int) (lm.getAscent() * .76 + .5); // Compensate for overstating
		
		// x,y is bottom left corner of text
		g2.drawString(text, 
				(int) (position.x + offset.x - width/2), 
				(int) (position.y + offset.y + height/2));
	}

	public static void drawLine(Graphics g, Color color, Coordinate from, Coordinate to,
			Coordinate offset) {
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(2));
		g2.setColor(color);
		
		g2.draw(new Line2D.Double(
				from.x + offset.x, from.y + offset.y, 
				to.x + offset.x, to.y + offset.y));
	}
	
	public static void drawCircle(Graphics g, Color color, Coordinate position, double radius) {
    	Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(2));
		
    	Shape circle = new Ellipse2D.Double(position.x, position.y, radius*2, radius*2);
    	g2.draw(circle);
    	
    	g2.setPaint(color);
    	g2.fill(circle);
	}
}
