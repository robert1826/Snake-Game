package Model;

import java.awt.Point;

public class Mouse {
	private Point pos;
	
	public Mouse(int x, int y){
		pos = new Point(x, y);
	}
	
	public Point getPos(){
		return pos.getLocation();
	}
}
