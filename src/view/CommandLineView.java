package view;

import java.util.Scanner;

import miscellaneous.GridConstants;
import model.Grid;
import controller.Controller;

public class CommandLineView {		

	private Scanner sc;
	private Controller con;

	public CommandLineView(Controller controller) {
		sc = new Scanner(System.in);
		con = controller;
	}

	public void updateView(Grid grid){
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
		con.moveSnake(getInput());
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
