package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Snake {
	private final Deque<Point> body;

	public Snake(int startx, int starty){
		body = new LinkedList<Point>();
		body.addFirst(new Point(startx, starty));
	}

	public synchronized void moveHeadToPos(Point newHead, boolean removeTail) {
		body.addFirst(newHead.getLocation());
		if (removeTail)
			body.removeLast();
	}

	public synchronized List<Point> getBody(){
		ArrayList<Point> res = new ArrayList<Point>(body.size());
		for (Point p : body)
			res.add(p.getLocation());
		return res;
	}
}
