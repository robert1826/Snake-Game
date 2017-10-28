package view;

import java.awt.event.KeyListener;

import model.Grid;
import controller.Controller;

/**
 * Parent for any view used in this game
 */
public abstract class View {
	protected Grid grid;
	protected Controller con;

	public View(Grid g, Controller c) {
		this.grid = g;
		this.con = c;
	}

	public abstract void updateView();

	public abstract void addKeyListener(KeyListener listener);

	protected abstract void displayGameEndingMessage();

	public final void endGame() {
		displayGameEndingMessage();
		con.endGame();
	}


	interface Constants {
		public static final String GAME_EXITING_MSG = "Snake has crashed into itself, exiting...";
	}
}
