package player;

import java.util.Random;

public class CommandLineRandomPlayer extends Player {

	private final Random random;

	public CommandLineRandomPlayer(){
		random = new Random();
	}

	@Override
	public int getInputDirectionCode() {
		return random.nextInt(4);
	}
}
