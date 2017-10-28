package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Grid;
import controller.Controller;

public class GUIView extends View {

	private final JFrame gameFrame;
	private static final String FRAME_TITLE = "Snake Game !";
	private static final int CELL_SIDE = 100;

	private final GameContentGuiView contentPanel;

	private static final Color SNAKE_COLOR = Color.blue;
	private static final Color BACKGROUND_COLOR = Color.gray;
	private static final Color MOUSE_COLOR = Color.red;
	private static final Color BORDER_COLOR = Color.black;

	public GUIView(Grid g, Controller c) {
		super(g, c);
		gameFrame = new JFrame();

		contentPanel = new GameContentGuiView();
		contentPanel.setPreferredSize
			(new Dimension(CELL_SIDE * grid.getWidth(), CELL_SIDE * grid.getHeight()));

		setupAndShowFrame();
	}

	@Override
	public void addKeyListener(KeyListener listener) {
		gameFrame.addKeyListener(listener);
	}

	@Override
	public void updateView() {
		contentPanel.repaint();
	}

	@Override
	protected void displayGameEndingMessage() {
//		JOptionPane.showMessageDialog(null, View.Constants.GAME_EXITING_MSG);
		gameFrame.dispose();
	}

	private void setupAndShowFrame() {
		gameFrame.setContentPane(contentPanel);
		gameFrame.setTitle(FRAME_TITLE);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		gameFrame.requestFocus();
	}

	private class GameContentGuiView extends JPanel{

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			if (grid == null)
				return;

			g.setColor(BACKGROUND_COLOR);
			g.fillRect(0, 0, getWidth(), getHeight());

			g.setColor(BORDER_COLOR);
			// draw horizontal lines
			for (int y = 0; y <= CELL_SIDE * grid.getHeight(); y += CELL_SIDE)
				g.drawLine(0, y, this.getWidth(), y);

			// draw vertical lines
			for (int x = 0; x <= CELL_SIDE * grid.getWidth(); x += CELL_SIDE)
				g.drawLine(x, 0, x, this.getHeight());


			// draw moving objects
			int[][] intGrid = grid.getGrid();

			for (int i = 0; i < grid.getHeight(); i++) {
				for (int j = 0; j < grid.getWidth(); j++) {
					switch (intGrid[i][j]) {
					case Grid.Constants.SNAKE_VIEW_CODE:
						g.setColor(SNAKE_COLOR);
						g.fillRect(j * CELL_SIDE, i * CELL_SIDE, CELL_SIDE, CELL_SIDE);
						break;
					case Grid.Constants.MOUSE_VIEW_CODE:
						g.setColor(MOUSE_COLOR);
						g.fillRect(j * CELL_SIDE, i * CELL_SIDE, CELL_SIDE, CELL_SIDE);
						break;
					default:
						break;
					}
				}
			}
		}
	}

}
