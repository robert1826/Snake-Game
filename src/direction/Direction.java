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
}
