package view;

import model.Grid;
import controller.Controller;

/**
 * Factory to create a view for the game using some parameters
 */
public class ViewFactory {

	public static View getView(boolean isGui, boolean isAutoUpdated, Grid g, Controller c){
		if (! isGui){
			if (! isAutoUpdated)
				return new CommandLineView(g, c);
			else return null;
		}

		return isAutoUpdated ? new TimerGUIView(g, c) : new GUIView(g, c);
	}
}