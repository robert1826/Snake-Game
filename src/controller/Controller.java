package controller;

import java.awt.Point;

import model.Grid;
import view.View;
import view.ViewFactory;
import direction.Direction;
import direction.DirectionFactory;

/**
 * This is the game controller, it should act as a facade for the whole game
 * so when we need to instruct the model to update we should use the controller methods
 */
public class Controller {
	private final View view;
	private final Grid grid;
	private int curDirectionCode;
	private int prevDirectionCode;

	public Controller(int gameWidth, int gameHeight, boolean isViewAutoUpdated) {
		grid = new Grid(gameWidth, gameHeight);
		view = ViewFactory.getView(isViewAutoUpdated, grid, this);

		if (view == null)
			throw new IllegalArgumentException("Arguments passed couldn't be used to create a view");

		curDirectionCode = Direction.Constants.RIGHT_DIRECTION;
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
		if (Direction.isOppositeDirection(prevDirectionCode, curDirectionCode))
			curDirectionCode = prevDirectionCode;

		moveSnake(curDirectionCode);
		prevDirectionCode = curDirectionCode;
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
