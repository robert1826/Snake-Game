package Controller;

import java.util.Random;
import java.util.Scanner;

import Model.Grid;
import Model.Snake;
import View.CommandLineView;

public class CMDController {
	Snake snake;
	CommandLineView view;
	Random random;
	Scanner sc;
	Grid grid;
	
	public CMDController(int w, int h) {
		snake = new Snake(0, 0);
		grid = new Grid(snake, w, h);
		view = new CommandLineView(grid);
		random = new Random();
		sc = new Scanner(System.in);
		gameLoop();
	}
	
	void gameLoop(){
		while(true){
			view.updateView();
			int direction = getInput();
			snake.move(direction, true);
		}
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
