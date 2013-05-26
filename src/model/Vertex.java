package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Vertex extends JComponent {

	private Coordinate position;
	private int radius;
	private String name;
	private Color color;
	
	/**
	 * @param name
	 * @param position
	 */
	public Vertex(String name, Coordinate position) {
		this.position = position;
		this.radius = 20;
		this.name = name;
		this.color = Color.black;
	}
	
    @Override
    public void paintComponent(Graphics g) {
        BufferedImage buffer = new BufferedImage(this.radius, this.radius, 
        		BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();

        Ellipse2D circle = new Ellipse2D.Float(0, 0, this.radius, this.radius);
        Shape clip = g2d.getClip();
        g2d.setClip(circle);
        g2d.setColor(this.color);

        g2d.setClip(clip);
        g2d.setColor(this.color);
        g2d.draw(circle);
        g2d.dispose();
        g.drawImage(buffer, this.position.x - radius, this.position.y - radius, this);
    }

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
