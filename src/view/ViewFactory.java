package view;

import model.Grid;
import controller.Controller;

/**
 * Factory to create a view for the game using some parameters
 */
public class ViewFactory {

	public static View getView(boolean isActiveView, boolean isGui, Grid g, Controller c){
		if (!isActiveView)
			return new NullView(g, c);
		return isGui ? new JavaFxView(g, c) : new CommandLineView(g, c);
	}
}
