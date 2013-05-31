package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class GraphComponent extends JComponent {
	
	protected String name;
	protected Color color;
	protected boolean visisted;
	protected String attribute;

	/**
	 * @param name
	 */
	public GraphComponent(String name) {
		this.name = name;
	}

	abstract public void paintComponent(Graphics g);

	abstract public void reset();
	
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

	public boolean isVisited() {
		return visisted;
	}
	
	public void visit() {
		if (this.visisted) {
			System.err.println(String.format("WARNING: GraphComponent %s already visited",
					this.name));
		}
		this.visisted = true;
		this.color = Color.red;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

}
