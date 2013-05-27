package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import utils.GraphicsUtils;

@SuppressWarnings("serial")
public class Vertex extends GraphComponent {

	private Coordinate position;
	private int radius;
	
	/**
	 * @param name
	 * @param position
	 */
	public Vertex(String name, Coordinate position) {
		super(name);
		
		this.position = position;
		this.radius = 20;
		this.color = Color.lightGray;
	}
	
    @Override
    public void paintComponent(Graphics g) {
    	GraphicsUtils.drawCircle(g, this.color, this.position, this.radius);
    	
    	GraphicsUtils.drawText(g, this.color, new Font("Verdana", Font.PLAIN, 20),
    			this.name, this.position, new Coordinate(this.radius, this.radius));
    	
		if (this.attribute != null && !this.attribute.equals("")) {
	    	GraphicsUtils.drawText(g, this.color, new Font("Verdana", Font.PLAIN, 10),
	    			this.attribute, this.position, new Coordinate(0, 0));
		}
    }

    public void reset() {
    	this.color = Color.lightGray;
    	this.visisted = false;
    	this.attribute = null;
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
	
}
