package view;

import model.Grid;
import controller.Controller;

public abstract class View {
	protected Grid grid;
	protected boolean autoUpdated;
	protected Controller con;

	public View(Grid g) {
		this.grid = g;
		autoUpdated = false;
	}

	public abstract void updateView();

	public final void setController(Controller controller){
		con = controller;
	}

	public void endGame() {
		System.exit(0);
	}

	interface Constants {
		public static final String GAME_EXITING_MSG = "Snake has crashed into itself, exiting...";
	}
}
