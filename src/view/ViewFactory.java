package view;

import model.Grid;
import controller.Controller;

/**
 * Factory to create a view for the game using some parameters
 */
public class ViewFactory {

	public static View getView(boolean isGui, Grid g, Controller c){
		return isGui ? new GUIView(g, c) : new CommandLineView(g, c);
	}
}
