package Model;

import java.awt.Point;

public class Snake {
	private Point body;
	
	public Snake(int startx, int starty){
		body = new Point(startx, starty);
	}
	
	public Point move(int direction){
		int[] dx = new int[]{-1, 0, 1, 0};
		int[] dy = new int[]{0, 1, 0, -1};
		
		body.translate(dx[direction], dy[direction]);
		
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
