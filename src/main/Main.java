package main;

import controller.Controller;

public class Main {
	public static void main(String[] args){
		int game_width = 10;
		int game_height = 10;

		Controller controller = new Controller(game_width, game_height, true, true);
		controller.gameLoop();
	}
}
