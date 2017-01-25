package Main;

import java.awt.Point;

import Model.Snake;

public class Main {
	public static void main(String[] args){
		Snake s = new Snake(new Point(0, 0));
		System.out.println(s);
	}
}
