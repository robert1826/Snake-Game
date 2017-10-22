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

	public abstract int getDx();
	public abstract int getDy();

	public static final boolean isOppositeDirection(int dirCode1, int dirCode2){
		Direction dirA = DirectionFactory.getDirection(dirCode1);
		Direction dirB = DirectionFactory.getDirection(dirCode2);
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
