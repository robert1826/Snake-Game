package controller;

import java.awt.Point;
import java.util.Scanner;

import view.CommandLineView;

import model.Grid;

import direction.Direction;
import direction.DirectionFactory;

public class CMDController {
	private CommandLineView view;
	private Scanner sc;
	private Grid grid;
	
	public CMDController(int w, int h) {
		grid = new Grid(w, h);
		view = new CommandLineView();
		sc = new Scanner(System.in);
	}
	
	public void gameLoop(){
		while(true){
			view.updateView(grid);
			moveSnake(getInput());
		}
	}

	private void moveSnake(int directionCode) {
		Direction dir = DirectionFactory.getDirection(directionCode);

		Point newHead = grid.getSnake().getBody();
		newHead.translate(dir.getDx(), dir.getDy());

		newHead.x = (newHead.x + grid.getHeight()) % grid.getHeight();
		newHead.y = (newHead.y + grid.getWidth()) % grid.getWidth();

		grid.getSnake().setHeadPos(newHead);

		if ( grid.getSnake().getBody().equals(grid.getMousePos()) )
			grid.generateNewMouse();
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
