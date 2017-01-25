package Model;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;

public class Snake {
	private Deque<Point> body;
	
	public Snake(Point startPos){
		body = new LinkedList<>();
		body.addLast(startPos);
	}
	
	/*
	 * move the snake in one of 4 directions (up, down, ...etc)
	 * up=0, right=1, down=2, left=3
	 * 
	 * if the move results in eating a mouse we increase 
	 * the length of the snake
	 */
	public void move(int direction, boolean eatenMouse){
		int[] dx = new int[]{0, 1, 0, -1};
		int[] dy = new int[]{-1, 0, 1, 0};
		
		Point newPoint = body.getFirst().getLocation();
		newPoint.translate(dx[direction], dy[direction]);
		body.addFirst(newPoint);
		
		if(!eatenMouse)
			body.pollLast();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(Point p : body){
			builder.append("(" + p.getX() + ", " + p.getY() + "), ");
		}
		return "[" + builder.substring(0, builder.length() - 2) +"]";
	}
}
