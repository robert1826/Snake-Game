package player;

import java.util.Random;

public class GuiRandomPlayer extends Player {

	private final Random random;

	public GuiRandomPlayer(){
		random = new Random();
	}

	@Override
	public int getInputDirectionCode() {
		return random.nextInt(4);
	}

	@Override
	protected boolean getIsGuiPlayer() {
		return true;
	}
}
