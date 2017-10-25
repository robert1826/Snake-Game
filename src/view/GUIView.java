package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Grid;
import controller.Controller;

public class GUIView extends View {

	private final JFrame gameFrame;
	private static final String FRAME_TITLE = "Snake Game !";
	private static final int LABEL_SIDE = 100;

	private final JPanel contentPanel;
	private final JLabel[][] labels;

	private static final Color SNAKE_COLOR = Color.blue;
	private static final Color BACKGROUND_COLOR = Color.gray;
	private static final Color MOUSE_COLOR = Color.red;
	private static final Color BORDER_COLOR = Color.black;

	public GUIView(Grid g, Controller c) {
		super(g, c);
		gameFrame = new JFrame();
		labels = new JLabel[grid.getHeight()][grid.getWidth()];

		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(grid.getWidth(), grid.getHeight()));

		createAndAddLabels();
		setupAndShowFrame();
	}


	@Override
	public void updateView() {
		int[][] g = grid.getGrid();

		for(int i = 0; i < grid.getHeight(); i++){
			for(int j = 0; j < grid.getWidth(); j++){
				switch (g[i][j]) {
				case Grid.Constants.SNAKE_VIEW_CODE:
					labels[i][j].setBackground(SNAKE_COLOR);
					break;
				case Grid.Constants.MOUSE_VIEW_CODE:
					labels[i][j].setBackground(MOUSE_COLOR);
					break;
				default:
					labels[i][j].setBackground(BACKGROUND_COLOR);
					break;
				}
			}
		}
	}

	@Override
	protected void displayGameEndingMessage() {
		JOptionPane.showMessageDialog(null, View.Constants.GAME_EXITING_MSG);
	}

	private void createAndAddLabels() {
		for (int i = 0; i < grid.getHeight(); i++) {
			for (int j = 0; j < grid.getWidth(); j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setOpaque(true);
				labels[i][j].setPreferredSize(new Dimension(LABEL_SIDE, LABEL_SIDE));
				labels[i][j].setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

				this.contentPanel.add(labels[i][j]);
			}
		}

	}

	private void setupAndShowFrame() {
		gameFrame.setContentPane(contentPanel);
		gameFrame.setTitle(FRAME_TITLE);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		gameFrame.requestFocus();
	}
}
