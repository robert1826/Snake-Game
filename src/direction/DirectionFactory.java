package direction;


public class DirectionFactory {
	private static final Direction up_direction = new UpDirection();
	private static final Direction down_direction = new DownDirection();
	private static final Direction left_direction = new LeftDirection();
	private static final Direction right_direction = new RightDirection();

	public static Direction getDirection(int direction) {

		switch (direction) {

		case Direction.Constants.UP_DIRECTION:
			return up_direction;

		case Direction.Constants.DOWN_DIRECTION:
			return down_direction;

		case Direction.Constants.LEFT_DIRECTION:
			return left_direction;

		case Direction.Constants.RIGHT_DIRECTION:
			return right_direction;

		default:
			throw new IllegalArgumentException();
		}
	}
}
