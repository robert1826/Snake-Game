package view;

import model.Grid;
import controller.Controller;

/**
 * Parent for any view used in this game
 */
public abstract class View {
	protected Grid grid;
	protected boolean autoUpdated;
	protected Controller con;

	public View(Grid g, Controller c) {
		this.grid = g;
		this.con = c;
		autoUpdated = false;
	}

	public abstract void updateView();

	public void endGame() {
		System.exit(0);
	}

	interface Constants {
		public static final String GAME_EXITING_MSG = "Snake has crashed into itself, exiting...";
	}
}
