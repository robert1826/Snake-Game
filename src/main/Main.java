package main;

import view.CommandLineView;
import view.View;
import controller.Controller;

public class Main {
	public static void main(String[] args){
		View view = new CommandLineView();
		Controller controller = new Controller(10, 10, view);
		controller.gameLoop();
	}
}
