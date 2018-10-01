package caceresenzo.app.solver.school42.test2.world.tiles;

public class StarTile extends Tile {
	
	public StarTile() {
		super(null);
	}
	
	public StarTile(TileColor color) {
		super(color);
	}
	
	@Override
	public String toString() {
		return "StarTile[color=" + color + "]";
	}
	
}