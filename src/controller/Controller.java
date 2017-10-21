package controller;

import java.awt.Point;

import model.Grid;
import view.CommandLineView;
import view.View;
import direction.Direction;
import direction.DirectionFactory;

public class Controller {
	private View view;
	private Grid grid;
	
	public Controller(int w, int h, View v) {
		grid = new Grid(w, h);
		view = v;
		view.setController(this);
	}
	
	public void gameLoop(){
		while(true){
			view.updateView(grid);
		}
	}

	public void moveSnake(int directionCode) {
		Direction dir = DirectionFactory.getDirection(directionCode);

		Point newHead = grid.getSnake().getBody();
		newHead.translate(dir.getDx(), dir.getDy());

		newHead.x = (newHead.x + grid.getHeight()) % grid.getHeight();
		newHead.y = (newHead.y + grid.getWidth()) % grid.getWidth();

		grid.getSnake().setHeadPos(newHead);

		if ( grid.getSnake().getBody().equals(grid.getMousePos()) )
			grid.generateNewMouse();
	}
}
