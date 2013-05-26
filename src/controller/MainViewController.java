package controller;

import model.Coordinate;
import model.Line;
import model.Vertex;
import utils.ActionUtils;
import view.MainView;

public class MainViewController {

	protected final MainView view;

	/**
	 * @param view
	 */
	public MainViewController(MainView view) {
		this.view = view;

		ActionUtils.addListener(this.view.getNewVertexButton(), this,
				"addRandomVertex");
		ActionUtils.addListener(this.view.getNewEdgeButton(), this,
				"addRandomLine");
		ActionUtils.addListener(this.view.getClearButton(), this.view, "clear");
	}

	public void addRandomVertex() {
		int x = (int) (Math.random() * MainView.WINDOW_WIDTH);
		int y = (int) (Math.random() * MainView.WINDOW_HEIGHT);
		// Color randomColor = new Color((float) Math.random(),
		// (float) Math.random(), (float) Math.random());
		this.view.getCanvas().addVertex(new Vertex("A", new Coordinate(x, y)));
	}

	public void addRandomLine() {
		Coordinate from = new Coordinate((int) (Math.random() * MainView.WINDOW_WIDTH), 
				(int) (Math.random() * MainView.WINDOW_HEIGHT));
		Coordinate to = new Coordinate((int) (Math.random() * MainView.WINDOW_WIDTH), 
				(int) (Math.random() * MainView.WINDOW_HEIGHT));
		// Color randomColor = new Color((float) Math.random(),
		// (float) Math.random(), (float) Math.random());
		this.view.getCanvas().addLine(new Line(from, to));
	}

}
