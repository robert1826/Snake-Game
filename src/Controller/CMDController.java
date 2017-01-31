package Controller;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

import Model.Snake;
import View.CommandLineView;

public class CMDController {
	Snake snake;
	CommandLineView view;
	int gridWidth, gridHeight;
	Random random;
	Scanner sc;
	
	public CMDController(int w, int h) {
		snake = new Snake(0, 0);
		gridWidth = w;
		gridHeight = h;
		view = new CommandLineView(gridWidth, gridHeight, snake);
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

	Point getMousePos() {
		int x = random.nextInt(gridWidth + 1);
		int y = random.nextInt(gridHeight + 1);
		return new Point(x, y);
	}
}
