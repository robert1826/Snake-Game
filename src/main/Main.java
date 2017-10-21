package main;

import controller.Controller;

public class Main {
	public static void main(String[] args){
		Controller controller = new Controller(10, 10);
		controller.gameLoop();
	}
}
