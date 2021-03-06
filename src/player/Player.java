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

	protected Controller gameController;

	public Player(){
		createGame();
		startGame();
	}

	protected void createGame(){

		try{
			gameController = new Controller(this, GAME_WIDTH, GAME_HEIGHT, getIsActiveView(), getIsGuiPlayer());
		}catch(IllegalArgumentException ex){
			System.err.println("Can't create a controller for the game : " + ex.getMessage());
			System.err.println("Ending game.");
			System.exit(0);
		}

		addListenersToView();
	}


	protected void startGame() {
		gameController.startGame();
	}

	public void endGame() {
		System.exit(0);
	}

	protected void addListenersToView() {

	}

	protected abstract boolean getIsGuiPlayer();

	protected boolean getIsActiveView(){
		// players can override and change this behavior but its true by default
		return true;
	}

	public abstract int getInputDirectionCode();
}
