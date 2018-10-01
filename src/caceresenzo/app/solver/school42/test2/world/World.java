package caceresenzo.app.solver.school42.test2.world;

import java.util.ArrayList;
import java.util.List;

import caceresenzo.app.solver.school42.test2.commands.Interpreter;
import caceresenzo.app.solver.school42.test2.function.Function;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.tiles.StarTile;
import caceresenzo.app.solver.school42.test2.world.tiles.Tile;
import caceresenzo.app.solver.school42.test2.world.tiles.TileColor;

public class World {
	
	public static final int WORLD_MAX_SIZE = 32;
	
	private Tile[][] tiles, backupedTiles;
	private Player player, backupedPlayer;
	private List<Function> functions;
	private final List<Interpreter> availableCommands;
	private final List<TileColor> availableWorldColor;
	
	public World(Tile[][] tiles, Player player, List<Function> functions, List<Interpreter> availableCommands) {
		this.tiles = tiles;
		this.player = player;
		this.functions = functions;
		this.availableCommands = availableCommands;
		
		this.availableWorldColor = new ArrayList<>();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				Tile tile = tiles[i][j];
				
				if (tile != null && tile.getColor() != null) {
					if (!availableWorldColor.contains(tile.getColor())) {
						availableWorldColor.add(tile.getColor());
					}
				}
			}
		}
	}
	
	public boolean isEverythingOk() {
		if (getActualTile() == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean isCompleted() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				if (tiles[i][j] instanceof StarTile) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void backup() {
		backupedTiles = new Tile[WORLD_MAX_SIZE][WORLD_MAX_SIZE];
		
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				backupedTiles[i][j] = tiles[i][j];
			}
		}
		
		backupedPlayer = new Player(player);
	}
	
	public void restore() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles.length; j++) {
				tiles[i][j] = backupedTiles[i][j];
			}
		}
		
		player = new Player(backupedPlayer);
		
		for (Function function : functions) {
			function.reset();
		}
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
	public Tile getActualTile() {
		return tiles[player.getY()][player.getX()];
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<Function> getFunctions() {
		return functions;
	}
	
	public List<Interpreter> getAvailableCommands() {
		return availableCommands;
	}
	
	public List<TileColor> getAvailableWorldColor() {
		return availableWorldColor;
	}
	
}