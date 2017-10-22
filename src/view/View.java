package view;

import model.Grid;
import controller.Controller;

public abstract class View {
	protected Grid grid;
	protected boolean autoUpdated;

	public View(Grid g) {
		this.grid = g;
		autoUpdated = false;
	}

	public abstract void updateView();
	public abstract void setController(Controller controller);

	public void endGame() {
		System.exit(0);
	}
}
