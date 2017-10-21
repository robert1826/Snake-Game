package model;

import java.awt.Point;

public class Snake {
	private Point body;
	
	public Snake(int startx, int starty){
		body = new Point(startx, starty);
	}
	
	public void setHeadPos(Point newHead) {
		this.body = newHead.getLocation();
	}
	
	public Point getBody(){
		return body.getLocation();
	}
	
	@Override
	public String toString() {
		return String.format("Snake@(%d, %d)", body.x, body.y);
	}

}
