package main;

import view.JavaFxView;

/**
 * Starting point of the whole game
 */
public class Main {
	public static void main(String[] args){
//		Player player = new GuiHumanPlayer();

		JavaFxView view = new JavaFxView();
		view.launch(JavaFxView.class);
	}
}
