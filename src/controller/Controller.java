package controller;

import java.awt.Point;

import model.Grid;
import view.View;
import direction.Direction;
import direction.DirectionFactory;

public class Controller {
	private final View view;
	private final Grid grid;

	public Controller(int w, int h, Grid g, View v) {
		grid = g;
		view = v;
		view.setController(this);
	}

	public void gameLoop(){
		while(true){
			view.updateView();
		}
	}

	public void moveSnake(int directionCode) {
		Direction dir = DirectionFactory.getDirection(directionCode);

		Point newHead = grid.getSnake().getBody();
		newHead.translate(dir.getDx(), dir.getDy());

		newHead.x = (newHead.x + grid.getHeight()) % grid.getHeight();
		newHead.y = (newHead.y + grid.getWidth()) % grid.getWidth();

		grid.getSnake().setHeadPos(newHead);

		if ( grid.getSnake().getBody().equals(grid.getMousePos()) )
			grid.generateNewMouse();
	}
}
