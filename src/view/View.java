package view;

import controller.Controller;
import model.Grid;

public interface View {
	
	public void updateView(Grid grid);
	public void setController(Controller controller);
}
