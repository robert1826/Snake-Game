package player;


/**
 * This class represents a player for the game which can be a human or a computer
 * player, also this player can send input to the controller on demand or the controller could
 * ask the player for the input
 */
public abstract class Player {

	public abstract int getInputDirectionCode();
}
