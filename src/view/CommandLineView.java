package view;

import java.util.Scanner;

import model.Grid;
import controller.Controller;

public class CommandLineView extends View {

	private final Scanner sc;

	public CommandLineView(Grid g, Controller c) {
		super(g, c);
		sc = new Scanner(System.in);
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

		if (con != null){
			con.setCurDirectionCode(getInput());
			con.moveSnake();
		}
		else System.err.println("No controller found to process input");
	}

	@Override
	protected void displayGameEndingMessage(){
		System.out.println(View.Constants.GAME_EXITING_MSG);
	}

	private int getInput() {
		char s = sc.next().charAt(0);
		while(s != 'u' && s != 'r' && s != 'd' && s != 'l'){
			System.out.println("Please enter valid input :");
			s = sc.next().charAt(0);
		}

		return "urdl".indexOf(s);
	}
}
