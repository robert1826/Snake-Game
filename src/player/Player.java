package player;

import controller.Controller;


/**
 * This class represents a player for the game which can be a human or a computer
 * player, also this player can send input to the controller on demand or the controller could
 * ask the player for the input
 */
public abstract class Player {

	private static final int GAME_WIDTH = 10;
	private static final int GAME_HEIGHT = 10;

	private Controller gameController;

	public Player(){

		try{
			gameController = new Controller(this, GAME_WIDTH, GAME_HEIGHT, getIsGuiPlayer());
		}catch(IllegalArgumentException ex){
			System.err.println("Can't create a controller for the game : " + ex.getMessage());
			System.err.println("Ending game.");
			System.exit(0);
		}

		gameController.startGame();
	}

	protected abstract boolean getIsGuiPlayer();

	public abstract int getInputDirectionCode();
}
