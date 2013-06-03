package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import model.elements.Edge;
import model.elements.Graph;
import model.elements.Vertex;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	private Graph graph;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (this.graph == null) return;
		
		for (Edge edge: this.graph.getEdgesAsList()) {
			edge.paintComponent(g);
		}
		
		for (Vertex vertex : this.graph.getVertices().values()) {
			vertex.paintComponent(g);
		}
	}

//	public void addComponent(GraphComponent component) {
//		if (component.getClass() == Edge.class) {
//			this.graph.addEdge((Edge) component);
//			System.out.println(String.format("New edge: (%d,%d)->(%d,%d)",
//					((Edge)component).getFrom().getPosition().x, ((Edge)component).getFrom().getPosition().y, 
//					((Edge)component).getTo().getPosition().x, ((Edge)component).getTo().getPosition().y));
//		} else if (component.getClass() == Vertex.class) {
//			Vertex vertex = (Vertex) component;
//			this.graph.addVertex(vertex);
//			System.out.println(String.format("New vertex: (%d,%d)", 
//					vertex.getPosition().x, vertex.getPosition().y));
//		} else {
//			System.err.println("ERROR: Unsupported class " + component.getClass());
//			return;
//		}
//		this.repaint();
//	}
	
	public Graph getGraph() {
		return this.graph;
	}
	
	public void setGraph(Graph graph) {
		this.graph = graph;
		this.repaint();
	}

}