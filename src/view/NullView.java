package view;

import java.awt.event.KeyListener;

import model.Grid;
import controller.Controller;

/**
 * View that doesn't do anything
 */
public class NullView extends View {

	public NullView(Grid g, Controller c) {
		super(g, c);
	}

	@Override
	public void addKeyListener(KeyListener listener) {

	}

	@Override
	public void updateView(){

	}

	@Override
	protected void displayGameEndingMessage(){

	}
}
