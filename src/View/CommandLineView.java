package View;

import java.awt.Point;

import Model.Snake;

public class CommandLineView {
	static final int EMPTY_VIEW_CODE = 0;
	static final int SNAKE_VIEW_CODE = 1;
	static final int MOUSE_VIEW_CODE = 2;
	
	private int width, height;
	private Snake snake;
	
	public CommandLineView(int w, int h, Snake s) {
		this.width = w;
		this.height = h;
		this.snake = s;
	}
	
	public void updateView(){
		int[][] grid = new int[width][height];
		Point[] snakeBody = snake.getBody();
		
		for(Point p : snakeBody)
			grid[p.x][p.y] = SNAKE_VIEW_CODE;
			
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				switch (grid[i][j]) {
				case SNAKE_VIEW_CODE:
					System.out.print("#");
					break;
				case MOUSE_VIEW_CODE:
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
	}
}
