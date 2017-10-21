package view;

import model.Grid;
import controller.Controller;

public abstract class View {
	protected Grid grid;

	public View(Grid g) {
		this.grid = g;
	}

	public abstract void updateView();
	public abstract void setController(Controller controller);
}
