package controller;

import java.awt.Point;

import constants.DirectionConstants;

import model.Grid;
import view.View;
import direction.Direction;
import direction.DirectionFactory;

public class Controller {
	private final View view;
	private final Grid grid;
	private int curDirectionCode;

	public Controller(int w, int h, Grid g, View v) {
		grid = g;
		view = v;
		view.setController(this);
		curDirectionCode = DirectionConstants.RIGHT_DIRECTION;
	}

	public void gameLoop(){
		while(true){
			view.updateView();
		}
	}

	public void setCurDirectionCode(int curDirCode){
		curDirectionCode = curDirCode;
	}

	public void moveSnake() {
		moveSnake(curDirectionCode);
	}

	private void moveSnake(int directionCode) {
		Direction dir = DirectionFactory.getDirection(directionCode);

		Point newHead = grid.getSnakeBody().get(0);
		newHead.translate(dir.getDx(), dir.getDy());

		newHead.x = (newHead.x + grid.getHeight()) % grid.getHeight();
		newHead.y = (newHead.y + grid.getWidth()) % grid.getWidth();

		if ( newHead.equals(grid.getMousePos()) ){
			grid.setSnakeHeadPos(newHead, false);
			grid.generateNewMouse();

		} else if (grid.setSnakeHeadPos(newHead, true))
				view.endGame();
	}
}
