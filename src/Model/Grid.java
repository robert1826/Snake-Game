package Model;

import java.awt.Point;

public class Grid {
	public static final int EMPTY_VIEW_CODE = 0;
	public static final int SNAKE_VIEW_CODE = 1;
	public static final int MOUSE_VIEW_CODE = 2;
	
	private Point mousePos;
	private Snake snake;
	private int width, height;
	
	public Grid(Snake s, int w, int h){
		snake = s;
		this.width = w;
		this.height = h;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int[][] getGrid(){
		int[][] grid = new int[width][height];
		Point[] snakeBody = snake.getBody();
		
		for(Point p : snakeBody)
			grid[p.x][p.y] = SNAKE_VIEW_CODE;
			
		return grid;
	}
}
