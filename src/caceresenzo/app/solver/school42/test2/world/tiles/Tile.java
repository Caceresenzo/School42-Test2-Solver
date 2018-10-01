package caceresenzo.app.solver.school42.test2.world.tiles;

import java.awt.Color;

public class Tile {
	
	public static final Color EMPTY_COLOR = new Color(0, 0, 0);
	
	protected TileColor color;
	
	public Tile() {
		this(null);
	}
	
	public Tile(TileColor color) {
		this.color = color;
	}
	
	public TileColor getColor() {
		return color;
	}
	
	public Tile setColor(TileColor color) {
		this.color = color;
		
		return this;
	}
	
	@Override
	public String toString() {
		return "Tile[color=" + color + "]";
	}
	
}