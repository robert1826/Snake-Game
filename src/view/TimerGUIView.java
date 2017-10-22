package view;

import java.util.Timer;
import java.util.TimerTask;

import model.Grid;


public class TimerGUIView extends GUIView {

	private final int FRAME_UPDATE_INTERVAL = 350;
	private final Timer timer;
	private final TimerTask task = new TimerTask() {
		@Override
		public void run() {
			if (con != null)
				con.moveSnake();
			updateView();
		}
	};

	public TimerGUIView(Grid g) {
		super(g);
		autoUpdated = true;
		timer = new Timer();
		timer.schedule(task, 0, FRAME_UPDATE_INTERVAL);
	}
}
