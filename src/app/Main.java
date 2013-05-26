package app;

import javax.swing.JFrame;

import view.MainView;
import controller.MainViewController;


@SuppressWarnings("serial")
public class Main extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new MainViewController(new MainView());
	}

}
