package main;

import player.CommandLineHumanPlayer;
import controller.Controller;

/**
 * Starting point of the whole game
 */
public class Main {
	public static void main(String[] args){
		int game_width = 10;
		int game_height = 10;

		Controller controller = null;
		try{
			controller = new Controller(new CommandLineHumanPlayer(), game_width, game_height, false);
		}catch(IllegalArgumentException ex){
			System.err.println("Couldn't create game controller : " + ex.getMessage());
			System.err.println("Ending game.");
			System.exit(0);
		}

		controller.startGame();
	}
}
