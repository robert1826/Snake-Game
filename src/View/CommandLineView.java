package View;

import miscellaneous.GridConstants;
import Model.Grid;

public class CommandLineView {		

	public void updateView(Grid grid){
		int[][] g = grid.getGrid();
			
		for(int i = 0; i < grid.getHeight(); i++){
			for(int j = 0; j < grid.getWidth(); j++){
				switch (g[i][j]) {
				case GridConstants.SNAKE_VIEW_CODE:
					System.out.print("#");
					break;
				case GridConstants.MOUSE_VIEW_CODE:
					System.out.print("^");
					break;
				default:
					System.out.print(".");
					break;
				}
			}	
			System.out.println();
		}
		
		System.out.println();
	}
}
