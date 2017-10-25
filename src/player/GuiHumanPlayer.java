package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import direction.Direction;


public class GuiHumanPlayer extends Player implements KeyListener {

	@Override
	public int getInputDirectionCode() {
		return gameController.getCurDirectionCode();
	}

	@Override
	protected void addListenersToView() {
		gameController.addKeyListenerToView(this);
	}

	@Override
	protected boolean getIsGuiPlayer() {
		return true;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		if (gameController == null){
			System.err.println("No controller found to process input");
			return;
		}

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			gameController.setCurDirectionCode(Direction.Constants.UP_DIRECTION);
			break;
		case KeyEvent.VK_DOWN:
			gameController.setCurDirectionCode(Direction.Constants.DOWN_DIRECTION);
			break;
		case KeyEvent.VK_LEFT:
			gameController.setCurDirectionCode(Direction.Constants.LEFT_DIRECTION);
			break;
		case KeyEvent.VK_RIGHT:
			gameController.setCurDirectionCode(Direction.Constants.RIGHT_DIRECTION);
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
	}
}
