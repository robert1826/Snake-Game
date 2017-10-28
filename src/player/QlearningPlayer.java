package player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Random;

import controller.Controller;

public class QlearningPlayer extends Player {

	private static final double learningRate = 0.1;
	private static final double discountFactor = 0.9;
	private static final Random rand = new Random();
	private static final double exploreProbability = 0.1;

	private HashMap<QlearningMapKey, Double> qTable;
	private State curState;
	private BitSet exploringBitset;

	@Override
	protected void createGame() {
		super.createGame();
		curState = new State(gameController);

		if (qTable == null) {
			qTable = new HashMap<QlearningPlayer.QlearningMapKey, Double>();
			exploringBitset = new BitSet();
			exploringBitset.set(0, (int) (exploreProbability * 100), true);
		}
	}

	@Override
	protected boolean getIsGuiPlayer() {
		return true;
	}

	@Override
	protected boolean getIsActiveView() {
		return false;
	}

	@Override
	public int getInputDirectionCode() {

		// explore
		Integer exploreOutputIfAllowed = getResultIfAllowedExploreOrNull();
		if (exploreOutputIfAllowed != null)
			return exploreOutputIfAllowed;

		Double bestValue = null;
		ArrayList<Integer> bestActions = new ArrayList<Integer>();
		for (QlearningMapKey key : qTable.keySet())
			if (key.state.equals(curState))
				if (bestValue == null || (bestValue < qTable.get(key))){
					bestValue = qTable.get(key);
					bestActions.clear();
					bestActions.add(key.action);
				}else if (Math.abs(bestValue - qTable.get(key)) < 0.1)
					bestActions.add(key.action);

		if (! bestActions.isEmpty())
			return bestActions.get(rand.nextInt(bestActions.size()));
		return rand.nextInt(4);
	}

	private Integer getResultIfAllowedExploreOrNull() {
		boolean allowedToExplore = exploringBitset.get(rand.nextInt(100));
		if (! allowedToExplore)
			return null;

		return rand.nextInt(4);
	}

	public void learn(int event, State nextState){
		double reward = (event == Controller.GameEventsConstants.SNAKE_CRASHING_EVENT) ? -100 : event;

		int curDirectionCode = gameController.getCurDirectionCode();

		QlearningMapKey curKey = new QlearningMapKey(curState, curDirectionCode);
		double curValue = qTable.getOrDefault(curKey, 0.0);

		double maxNextValue = 0;
		for (int i = 0; i < 4; i++){
			QlearningMapKey loopKey = new QlearningMapKey(nextState, i);
			maxNextValue = Math.max(maxNextValue, qTable.getOrDefault(loopKey, 0.0));
		}

		double nextQValue = (1 - learningRate) * curValue
				+ learningRate * (reward + discountFactor * maxNextValue);

		qTable.put(curKey, nextQValue);
	}

	public void updateCurState() {
		curState = new State(gameController);
	}

	@Override
	public void endGame() {
		createGame();
		startGame();
	}

	private class QlearningMapKey {

		private final State state;
		private final int action;

		QlearningMapKey(State state, int action){
			this.state = state;
			this.action = action;
		}

		@Override
		public int hashCode() {
			return state.hashCode() + 97 * (action + 1);
		}

		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof QlearningMapKey))
				return false;

			QlearningMapKey other = (QlearningMapKey) obj;
			return state.equals(other.state)
					&& action == other.action;
		}
	}

	public static class State {

		private final Point mousePos;
		private final Point snakeHead;

		public State(Controller con) {
			this.mousePos = con.getMousePos();
			this.snakeHead = con.getSnakeBody().get(0);
		}

		@Override
		public int hashCode() {
			return mousePos.hashCode() + 31 * snakeHead.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof State))
				return false;

			State other = (State) obj;
			return mousePos.equals(other.mousePos)
					&& snakeHead.equals(other.snakeHead);
		}
	}
}