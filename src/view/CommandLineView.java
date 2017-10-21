package view;

import java.util.Scanner;

import miscellaneous.GridConstants;
import model.Grid;
import controller.Controller;

public class CommandLineView extends View {

	private final Scanner sc;
	private Controller con;

	public CommandLineView(Grid g) {
		super(g);
		sc = new Scanner(System.in);
	}

	@Override
	public void setController(Controller controller) {
		con = controller;
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
				case GridConstants.SNAKE_VIEW_CODE:
					System.out.print("#");
					break;
				case GridConstants.MOUSE_VIEW_CODE:
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

		if (con != null)
			con.moveSnake(getInput());
		else System.err.println("No controller found to process input");
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
