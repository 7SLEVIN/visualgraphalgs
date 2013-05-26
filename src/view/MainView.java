package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainView extends JFrame {

	public final static int WINDOW_WIDTH = 600;
	public final static int WINDOW_HEIGHT = 400;
	
	protected Canvas canvas;
	protected JButton newVertexButton;
	protected JButton newEdgeButton;
	protected JButton clearButton;
		
	/**
	 * 
	 */
	public MainView() {
		super("Visual Graph Algorithms");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.getContentPane().add(this.canvas, BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		this.newVertexButton = new JButton("New Vertex");
		this.newEdgeButton = new JButton("New Edge");
		this.clearButton = new JButton("Clear");
		buttonsPanel.add(this.newVertexButton);
		buttonsPanel.add(this.newEdgeButton);
		buttonsPanel.add(this.clearButton);
		this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setVisible(true);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public JButton getNewVertexButton() {
		return newVertexButton;
	}

	public JButton getNewEdgeButton() {
		return newEdgeButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	
}
