package view;

import java.awt.event.KeyListener;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Grid;
import controller.Controller;

public class JavaFxView extends View {

	private boolean launched = false;

	public JavaFxView(Grid g, Controller c) {
		super(g, c);
	}

	@Override
	public void updateView() {
//		int[][] g = grid.getGrid();
//
//		for (int i = 0; i < grid.getHeight(); i++)
//			for (int j = 0; j < grid.getWidth(); j++){
//				switch (g[i][j]) {
//				case Grid.Constants.SNAKE_VIEW_CODE:
//
//					break;
//
//				default:
//					break;
//				}
//			}

		if (! launched){
			new ActualFxView().launch(ActualFxView.class);
			launched = true;
		}
	}

	@Override
	public void addKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void displayGameEndingMessage() {
		System.out.println("Game ends here");
	}


	private class ActualFxView extends Application {

		private final StackPane pane;
		private final Label[][] labels;

		ActualFxView(){
			pane = new StackPane();
			labels = new Label[grid.getHeight()][grid.getWidth()];
			createAndAddLabels();
		}

		@Override
		public void start(Stage primaryStage) {
			Scene scene = new Scene(pane, 300, 250);

	        primaryStage.setTitle("Hello World!");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		}

		private void createAndAddLabels() {
			for (int i = 0; i < grid.getHeight(); i++) {
				for (int j = 0; j < grid.getWidth(); j++) {
					labels[i][j] = new Label();
					labels[i][j].setOpacity(0);
					// labels[i][j].setPreferredSize(new Dimension(LABEL_SIDE,
					// LABEL_SIDE));
					// labels[i][j].setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

					pane.getChildren().add(labels[i][j]);
				}
			}
		}
	}
}
