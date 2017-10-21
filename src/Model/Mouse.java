package Model;

import java.awt.Point;
import java.util.Random;

public class Mouse {
	private Point body;
	private final Random random;
	
	public Mouse(){
		body = new Point(0, 0);
		random = new Random();
	}
	
	public Point getPos(){
		return body.getLocation();
	}
	
	public void renewPos(int width, int height) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		body = new Point(x, y);
	}	
	
	@Override
	public String toString() {
		return String.format("Mouse@(%d, %d)", body.x, body.y);
	}	
}
