package view;

import model.Grid;
import controller.Controller;

public class CommandLineView extends View {

	public CommandLineView(Grid g, Controller c) {
		super(g, c);
	}

	@Override
	public void updateView(){
		if (grid == null){
			System.err.println("No grid found when updating the view");
			return;
		}

		int[][] g = grid.getGrid();

		for(int i = 0; i < grid.getHeight(); i++){
			for(int j = 0; j < grid.getWidth(); j++){
				switch (g[i][j]) {
				case Grid.Constants.SNAKE_VIEW_CODE:
					System.out.print("#");
					break;
				case Grid.Constants.MOUSE_VIEW_CODE:
					System.out.print("^");
					break;
				default:
					System.out.print(".");
					break;
				}
			}
			System.out.println();
		}

		System.out.println();
	}

	@Override
	protected void displayGameEndingMessage(){
		System.out.println(View.Constants.GAME_EXITING_MSG);
	}
}
