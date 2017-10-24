package view;

import model.Grid;
import controller.Controller;

/**
 * Factory to create a view for the game using some parameters
 */
public class ViewFactory {

	public static View getView(boolean isAutoUpdated, Grid g, Controller c){
		return isAutoUpdated ? new TimerGUIView(g, c) : new GUIView(g, c);
	}
}
