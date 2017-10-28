package player.qlearning;

import java.io.Serializable;

class QlearningMapKey implements Serializable {

		private final QlearningState state;
		private final int action;

		QlearningMapKey(QlearningState state, int action){
			this.state = state;
			this.action = action;
		}


		public QlearningState getState() {
			return state;
		}


		public int getAction() {
			return action;
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