package caceresenzo.app.solver.school42.test2.world.tiles;

import java.awt.Color;

public enum TileColor {
	
	BLUE(0, 191, 255), ORANGE(255, 165, 0), GREEN(50, 205, 50);
	
	private final Color color;
	
	private TileColor(int red, int green, int blue) {
		this.color = new Color(red, green, blue);
	}
	
	public Color getValue() {
		return color;
	}
	
	public static TileColor fromString(String source) {
		for (TileColor tileColor : TileColor.values()) {
			if (tileColor.toString().equals(source)) {
				return tileColor;
			}
		}
		
		return null;
	}
	
}