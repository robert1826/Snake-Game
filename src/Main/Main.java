package Main;

import Controller.CMDController;

public class Main {
	public static void main(String[] args){
		CMDController controller = new CMDController(10, 10);
		controller.gameLoop();
	}
}
