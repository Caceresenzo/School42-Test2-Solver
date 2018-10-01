package caceresenzo.app.solver.school42.test2.player;

public enum PlayerOrientation {
	
	UP(0), //
	RIGHT(1), //
	DOWN(2), //
	LEFT(3); //
	
	private final int orientation;
	
	private PlayerOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	public int getOrientationValue() {
		return orientation;
	}
	
	public PlayerOrientation rotate(int rotation) {
		int newOrientation = getOrientationValue() + rotation;
		
		if (newOrientation > 3) {
			newOrientation = 0;
		}
		
		if (newOrientation < 0) {
			newOrientation = 3;
		}
		
		return getPlayerOrientationByValue(newOrientation);
	}
	
	public static PlayerOrientation getPlayerOrientationByValue(int orientation) {
		for (PlayerOrientation playerOrientation : PlayerOrientation.values()) {
			if (playerOrientation.getOrientationValue() == orientation) {
				return playerOrientation;
			}
		}
		
		throw new IllegalStateException("This value can't exist: " + orientation);
	}
	
}