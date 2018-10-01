package caceresenzo.app.solver.school42.test2.world;

import java.awt.Color;
import java.awt.Graphics;

import caceresenzo.app.solver.school42.test2.Bootstrap;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.ui.Drawable;
import caceresenzo.app.solver.school42.test2.world.tiles.StarTile;
import caceresenzo.app.solver.school42.test2.world.tiles.Tile;

public class WorldManager implements Drawable {
	
	public static final int TILE_SIZE = 40;
	public static final int TILE_LITTLE_SIZE = TILE_SIZE / 2;
	
	private World world;
	
	public WorldManager(World world) {
		this.world = world;
		
		world.backup();
	}
	
	@Override
	public void draw(Graphics graphics) {
		if (Bootstrap.DISABLE_DRAW) {
			return;
		}
		
		Tile[][] tiles = world.getTiles();
		
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				Tile tile = tiles[x][y];
				int xTile = x * TILE_SIZE, yTile = y * TILE_SIZE;
				
				if (tile == null) {
					graphics.setColor(Tile.EMPTY_COLOR);
				} else {
					graphics.setColor(tile.getColor() == null ? Color.RED : tile.getColor().getValue());
				}
				
				graphics.fillRect(xTile, yTile, TILE_SIZE, TILE_SIZE);
				
				if (tile instanceof StarTile) {
					graphics.setColor(Color.RED);
					graphics.fillRect(xTile + (TILE_LITTLE_SIZE / 2), yTile + (TILE_LITTLE_SIZE / 2), TILE_SIZE - (TILE_LITTLE_SIZE), TILE_SIZE - (TILE_LITTLE_SIZE));
				}
			}
		}
		
		graphics.setColor(Color.WHITE);
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				graphics.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
		
		Player player = world.getPlayer();
		player.draw(graphics);
	}
	
}