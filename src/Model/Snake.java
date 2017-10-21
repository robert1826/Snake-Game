package Model;

import java.awt.Point;

import direction.Direction;
import direction.DirectionFactory;

public class Snake {
	private Point body;
	
	public Snake(int startx, int starty){
		body = new Point(startx, starty);
	}
	
	public Point move(int direction){
		Direction dir = DirectionFactory.getDirection(direction);
		body.translate(dir.getDx(), dir.getDy());
		return getBody();
	}
	
	public Point getBody(){
		return body.getLocation();
	}
	
	@Override
	public String toString() {
		return String.format("Snake@(%d, %d)", body.x, body.y);
	}
}
