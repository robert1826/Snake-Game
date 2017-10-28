package player.qlearning;

import java.awt.Point;
import java.io.Serializable;

import controller.Controller;

public class QlearningState implements Serializable {

	private final Point mousePos;
	private final Point snakeHead;

	public QlearningState(Controller con) {
		this.mousePos = con.getMousePos();
		this.snakeHead = con.getSnakeBody().get(0);
	}


	public Point getMousePos() {
		return mousePos.getLocation();
	}


	public Point getSnakeHead() {
		return snakeHead.getLocation();
	}

	@Override
	public int hashCode() {
		return mousePos.hashCode() + 31 * snakeHead.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (! (obj instanceof QlearningState))
			return false;

		QlearningState other = (QlearningState) obj;
		return mousePos.equals(other.mousePos)
				&& snakeHead.equals(other.snakeHead);
	}
}