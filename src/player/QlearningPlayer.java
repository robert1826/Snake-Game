package player;

import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Random;

import controller.Controller;

public class QlearningPlayer extends Player implements Serializable {

	private static final boolean TRAINING_SETUP = true;

	private static final double learningRate = 0.1;
	private static final double discountFactor = 0.9;
	private static final Random rand = new Random();
	private static final double exploreProbability = 0.1;
	private static final int MAX_RUNS = 900;

	private HashMap<QlearningMapKey, Double> qTable;
	private State curState;
	private BitSet exploringBitset;

	private int maxScore;
	private int runs;

	@Override
	protected void createGame() {
		if (TRAINING_SETUP)
			Controller.GAME_UPDATING_INTERVAL_MSEC = 1;

		super.createGame();
		curState = new State(gameController);

		if (! TRAINING_SETUP)
			loadQTable();

		else if (qTable == null) {
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
		return ! TRAINING_SETUP;
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
		if (! TRAINING_SETUP)
			return null;

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

		System.out.println("learned for state : "
				+ curKey.state.snakeHead + " "
				+ curKey.state.mousePos + " "
				+ curKey.action);

		printInfo();
	}

	public void updateCurState() {
		curState = new State(gameController);
	}

	@Override
	public void endGame() {

		runs++;
		if (TRAINING_SETUP) {
			if (runs > MAX_RUNS) {
				saveQTable();
				System.exit(0);
			}

			createGame();
			startGame();
		}else
			System.exit(0);
	}

	private void saveQTable(){
		try {
			FileOutputStream fileOutput = new FileOutputStream("map.save");
			ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);

			objOutput.writeObject(qTable);

			objOutput.close();
			fileOutput.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadQTable() {
		try {
			InputStream in = new FileInputStream("map.save");
			ObjectInputStream objInput = new ObjectInputStream(in);

			qTable = (HashMap<QlearningPlayer.QlearningMapKey, Double>) objInput.readObject();

			objInput.close();
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printInfo() {
		int curScore = gameController.getSnakeBody().size();
		maxScore = Math.max(maxScore, curScore);

		System.out.println("run       : " + runs);
		System.out.println("Score     : " + curScore);
		System.out.println("MAX Score : " + maxScore);
		System.out.println("# States  : " + qTable.size());
		System.out.println("");
	}

	private class QlearningMapKey implements Serializable {

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

	public static class State implements Serializable {

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