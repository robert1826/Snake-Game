package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Grid;
import constants.DirectionConstants;
import constants.GlobalGameConstants;
import constants.GridConstants;
import controller.Controller;

public class GUIView extends View implements KeyListener {

	private final JFrame gameFrame;
	private static final String FRAME_TITLE = "Snake Game !";
	private static final int LABEL_SIDE = 100;

	protected Controller con;
	private final JPanel contentPanel;
	private final JLabel[][] labels;

	private static final Color SNAKE_COLOR = Color.blue;
	private static final Color BACKGROUND_COLOR = Color.gray;
	private static final Color MOUSE_COLOR = Color.red;
	private static final Color BORDER_COLOR = Color.black;

	public GUIView(Grid g) {
		super(g);
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
				case GridConstants.SNAKE_VIEW_CODE:
					labels[i][j].setBackground(SNAKE_COLOR);
					break;
				case GridConstants.MOUSE_VIEW_CODE:
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
	public void setController(Controller controller) {
		con = controller;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (this.con == null){
			System.err.println("No controller found to process input");
			return;
		}

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			con.setCurDirectionCode(DirectionConstants.UP_DIRECTION);
			break;
		case KeyEvent.VK_DOWN:
			con.setCurDirectionCode(DirectionConstants.DOWN_DIRECTION);
			break;
		case KeyEvent.VK_LEFT:
			con.setCurDirectionCode(DirectionConstants.LEFT_DIRECTION);
			break;
		case KeyEvent.VK_RIGHT:
			con.setCurDirectionCode(DirectionConstants.RIGHT_DIRECTION);
			break;
		default:
			break;
		}

		if (! autoUpdated)
			con.moveSnake();
	}

	public void keyReleased(KeyEvent e) {}

	@Override
	public void endGame() {
		JOptionPane.showMessageDialog(null, GlobalGameConstants.GAME_EXITING_MSG);
		super.endGame();
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
		gameFrame.addKeyListener(this);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.pack();
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);
		gameFrame.requestFocus();
	}
}
