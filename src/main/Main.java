package main;

import model.Grid;
import view.TimerGUIView;
import view.View;
import controller.Controller;

public class Main {
	public static void main(String[] args){
		int game_width = 10;
		int game_height = 10;

		Grid grid = new Grid(game_width, game_height);
		View view = new TimerGUIView(grid);
		Controller controller = new Controller(game_width, game_height, grid, view);
		controller.gameLoop();
	}
}
