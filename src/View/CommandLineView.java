package View;

import Model.Grid;

public class CommandLineView {		
	private Grid grid;
	
	public CommandLineView(Grid g) {
		this.grid = g;
	}
	
	public void updateView(){
		int[][] g = grid.getGrid();
			
		for(int i = 0; i < grid.getHeight(); i++){
			for(int j = 0; j < grid.getWidth(); j++){
				switch (g[i][j]) {
				case Grid.SNAKE_VIEW_CODE:
					System.out.print("#");
					break;
				case Grid.MOUSE_VIEW_CODE:
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
