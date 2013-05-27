package model;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class GraphComponent extends JComponent {
	
	protected Color color;
	protected boolean visisted;

	abstract public void paintComponent(Graphics g);

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isVisisted() {
		return visisted;
	}

	public void setVisisted(boolean visisted) {
		this.visisted = visisted;
	}

}
