package direction;

import constants.DirectionConstants;

public class DirectionFactory {
	private static final Direction up_direction = new UpDirection();
	private static final Direction down_direction = new DownDirection();
	private static final Direction left_direction = new LeftDirection();
	private static final Direction right_direction = new RightDirection();
	
	public static Direction getDirection(int direction) {
		
		switch (direction) {
		
		case DirectionConstants.UP_DIRECTION:
			return up_direction;
			
		case DirectionConstants.DOWN_DIRECTION:
			return down_direction;
		
		case DirectionConstants.LEFT_DIRECTION:
			return left_direction;
		
		case DirectionConstants.RIGHT_DIRECTION:
			return right_direction;
		
		default:
			throw new IllegalArgumentException();
		}
	}
}
