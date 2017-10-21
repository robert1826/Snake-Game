package Model;

import java.awt.Point;
import java.util.Random;

public class Grid {
	public static final int EMPTY_VIEW_CODE = 0;
	public static final int SNAKE_VIEW_CODE = 1;
	public static final int MOUSE_VIEW_CODE = 2;
	
	private Point mousePos;
	private Snake snake;
	private int width, height;
	private Random random;
	
	public Grid(Snake s, int w, int h){
		snake = s;
		this.width = w;
		this.height = h;
		random = new Random();
		mousePos = generateRandomMousePos();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	Point generateRandomMousePos() {
		int x = random.nextInt(getWidth());
		int y = random.nextInt(getHeight());
		return new Point(x, y);
	}
	
	public int[][] getGrid(){
		int[][] grid = new int[width][height];
		Point snakeBody = snake.getBody();
	
		grid[snakeBody.x][snakeBody.y] = SNAKE_VIEW_CODE;
		
		grid[mousePos.x][mousePos.y] = MOUSE_VIEW_CODE;
		
		return grid;
	}
	
	public Point getMousePos(){
		return mousePos.getLocation();
	}
	
	public void generateNewMouse(){
		mousePos = generateRandomMousePos();
	}
}
