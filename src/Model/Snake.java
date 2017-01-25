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
	
	public void move(){
		
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
