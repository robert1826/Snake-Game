package Model;

import java.awt.Point;
import java.util.Deque;
import java.util.LinkedList;

public class Snake {
	private Deque<Point> body;
	private int lastDirection;
	
	public Snake(Point startPos){
		body = new LinkedList<>();
		body.addLast(startPos);
		lastDirection = 1000;
	}
	
	/*
	 * move the snake in one of 4 directions (up, down, ...etc)
	 * up=0, right=1, down=2, left=3
	 * 
	 * if the move results in eating a mouse we increase 
	 * the length of the snake
	 * 
	 * note that : the snake can only crash with itself with any point other than
	 * the second to head one
	 * 
	 * returns the new head after the snake moves
	 */
	public Point move(int direction, boolean eatenMouse){
		int[] dx = new int[]{0, 1, 0, -1};
		int[] dy = new int[]{-1, 0, 1, 0};
		
		Point newHead = body.getFirst().getLocation();;
		
		if(Math.abs(direction - lastDirection) == 2)
			direction = lastDirection;
		
		newHead.translate(dx[direction], dy[direction]);
		body.addFirst(newHead);
			
		if(!eatenMouse)
			body.pollLast();
		lastDirection = direction;
		
		return newHead;
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
