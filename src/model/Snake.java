package model;

import java.awt.Point;

import direction.Direction;

public class Snake {
	private Point body;
	
	public Snake(int startx, int starty){
		body = new Point(startx, starty);
	}
	
	public Point move(Direction dir){
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
