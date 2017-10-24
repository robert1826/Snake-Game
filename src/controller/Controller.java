package controller;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import model.Grid;
import view.View;
import view.ViewFactory;
import direction.Direction;

/**
 * This is the game controller, it should act as a facade for the whole game
 * so when we need to instruct the model to update we should use the controller methods
 */
public class Controller {

	private static final long GAME_UPDATING_INTERVAL_MSEC = 200;

	private final View view;
	private final Grid grid;
	private int curDirectionCode;
	private int prevDirectionCode;
	private Timer gameLoopTimer;

	public Controller(int gameWidth, int gameHeight, boolean isGuiView) {
		grid = new Grid(gameWidth, gameHeight);
		view = ViewFactory.getView(isGuiView, grid, this);

		if (view == null)
			throw new IllegalArgumentException("Arguments passed couldn't be used to create a view");

		curDirectionCode = Direction.Constants.RIGHT_DIRECTION;
	}

	public void startGame(){

		TimerTask gameLoopTask = new TimerTask() {
			@Override
			public void run() {
				moveSnake();
				view.updateView();
			}
		};

		// ensure scheduling once
		if (gameLoopTimer == null) {
			gameLoopTimer = new Timer();
			gameLoopTimer.schedule(gameLoopTask, 0, GAME_UPDATING_INTERVAL_MSEC);
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
		Direction dir = Direction.getDirectionForCode(directionCode);

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
