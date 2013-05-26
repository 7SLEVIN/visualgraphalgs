package view;

import java.util.LinkedList;

import javax.swing.JPanel;

import model.Edge;
import model.Line;
import model.Vertex;


@SuppressWarnings("serial")
public class Canvas extends JPanel {

	private final LinkedList<Edge> edges = new LinkedList<Edge>();
	private final LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	private final LinkedList<Line> lines = new LinkedList<Line>();

//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		
//		for (Edge edge : this.edges) {
//			edge.paintComponent(g);
//		}
//
//		for (Vertex vertex : this.vertices) {
//			vertex.paintComponent(g);
//		}
//	}
	
	public void addVertex(Vertex vertex) {
		this.vertices.add(vertex);
//		vertex.repaint();
		this.add(vertex);
	}
	
	public void addLine(Line line) {
		this.lines.add(line);
		this.add(line);
	}

//	public void addEdge(Edge edge) {
//		this.edges.add(edge);
//		edge.repaint();
//	}

	public void clear() {
		this.lines.clear();
		this.edges.clear();
		this.vertices.clear();
		this.repaint();
	}
	
}