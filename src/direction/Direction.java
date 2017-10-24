package direction;

public abstract class Direction {
//	the axis are like this :
//	-----------------> y
//	|
//	|
//	|
//	|
//
//	x

	public interface Constants{
		public static final int UP_DIRECTION = 0;
		public static final int RIGHT_DIRECTION = 1;
		public static final int DOWN_DIRECTION = 2;
		public static final int LEFT_DIRECTION = 3;
	}

	private static final Direction up_direction = new UpDirection();
	private static final Direction down_direction = new DownDirection();
	private static final Direction left_direction = new LeftDirection();
	private static final Direction right_direction = new RightDirection();

	public static Direction getDirectionForCode(int direction) {

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

	public abstract int getDx();
	public abstract int getDy();

	public static final boolean isOppositeDirection(int dirCode1, int dirCode2){
		Direction dirA = Direction.getDirectionForCode(dirCode1);
		Direction dirB = Direction.getDirectionForCode(dirCode2);
		return isOppositeDirection(dirA, dirB);
	}

	public static final boolean isOppositeDirection(Direction dirA, Direction dirB){
		if (dirA.getDx() == dirB.getDx())
			return dirA.getDy() * dirB.getDy() == -1;

		if (dirA.getDy() == dirB.getDy())
			return dirA.getDx() * dirB.getDx() == -1;

		return false;
	}
}
