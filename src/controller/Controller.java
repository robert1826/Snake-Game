package controller;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import model.Grid;
import player.Player;
import view.View;
import view.ViewFactory;
import direction.Direction;

/**
 * This is the game controller, it should act as a facade for the whole game
 * so when we need to instruct the model to update we should use the controller methods
 */
public class Controller {

	private static final int EATING_MOUSE_EVENT = 1;
	private static final int SNAKE_CRASHING_EVENT = 2;

	private static final long GAME_UPDATING_INTERVAL_MSEC = 200;

	private final View view;
	private final Grid grid;
	private int curDirectionCode;
	private int prevDirectionCode;
	private Timer gameLoopTimer;
	private final Player gamePlayer;

	public Controller(Player player, int gameWidth, int gameHeight, boolean isActiveView, boolean isGuiView) {
		grid = new Grid(gameWidth, gameHeight);
		view = ViewFactory.getView(isActiveView, isGuiView, grid, this);

		if (view == null)
			throw new IllegalArgumentException("Arguments passed couldn't be used to create a view");

		curDirectionCode = Direction.Constants.RIGHT_DIRECTION;
		gamePlayer = player;
	}

	public void startGame(){

		TimerTask gameLoopTask = new TimerTask() {
			@Override
			public void run() {
				view.updateView();
				setCurDirectionCode(gamePlayer.getInputDirectionCode());
				int event = moveSnake();

				if (event == SNAKE_CRASHING_EVENT)
					endGame();
			}
		};

		// ensure scheduling once
		if (gameLoopTimer == null) {
			gameLoopTimer = new Timer();
			gameLoopTimer.schedule(gameLoopTask, 0, GAME_UPDATING_INTERVAL_MSEC);
		}
	}

	public void addKeyListenerToView(KeyListener guiHumanPlayer) {
		view.addKeyListener(guiHumanPlayer);
	}

	public int getCurDirectionCode(){
		return curDirectionCode;
	}

	public void setCurDirectionCode(int curDirCode){
		curDirectionCode = curDirCode;
	}

	public int moveSnake() {
		if (Direction.isOppositeDirection(prevDirectionCode, curDirectionCode))
			curDirectionCode = prevDirectionCode;

		int event = moveSnake(curDirectionCode);
		prevDirectionCode = curDirectionCode;
		return event;
	}

	private int moveSnake(int directionCode) {
		Direction dir = Direction.getDirectionForCode(directionCode);

		Point newHead = grid.getSnakeBody().get(0);
		newHead.translate(dir.getDx(), dir.getDy());

		newHead.x = (newHead.x + grid.getHeight()) % grid.getHeight();
		newHead.y = (newHead.y + grid.getWidth()) % grid.getWidth();

		int event = 0;
		if ( newHead.equals(grid.getMousePos()) ){
			grid.setSnakeHeadPos(newHead, false);
			grid.generateNewMouse();
			event = EATING_MOUSE_EVENT;

		} else if (grid.setSnakeHeadPos(newHead, true))
			event = SNAKE_CRASHING_EVENT;

		return event;
	}

	public void endGame() {
		gameLoopTimer.cancel();
		view.endGame();
		gamePlayer.endGame();
	}
}
