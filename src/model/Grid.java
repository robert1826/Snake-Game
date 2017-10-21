package model;

import java.awt.Point;
import java.util.List;

import miscellaneous.GridConstants;

public class Grid {
	private final Mouse mouse;
	private final Snake snake;
	private final int width, height;

	public Grid(int w, int h){
		snake = new Snake(0, 0);
		width = w;
		height = h;
		mouse = new Mouse();
		mouse.renewPos(w, h);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public List<Point> getSnakeBody() {
		return snake.getBody();
	}

	public void setSnakeHeadPos(Point newHead, boolean removeTail) {
		snake.moveHeadToPos(newHead, removeTail);
	}

	public int[][] getGrid(){
		int[][] grid = new int[width][height];
		List<Point> snakeBody = snake.getBody();

		for (Point p : snakeBody)
			grid[p.x][p.y] = GridConstants.SNAKE_VIEW_CODE;

		Point mousePos = getMousePos();
		grid[mousePos.x][mousePos.y] = GridConstants.MOUSE_VIEW_CODE;

		return grid;
	}

	public Point getMousePos(){
		return mouse.getPos();
	}

	public void generateNewMouse(){
		do {
			mouse.renewPos(width, height);
		} while (snake.getBody().contains(mouse.getPos()));
	}
}
