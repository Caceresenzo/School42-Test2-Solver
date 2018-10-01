package caceresenzo.app.solver.school42.test2.commands;

import java.util.ArrayList;
import java.util.List;

import caceresenzo.app.solver.school42.test2.logic.LogicController;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.tiles.Tile;
import caceresenzo.app.solver.school42.test2.world.tiles.TileColor;

public abstract class CommandContainer {
	
	private TileColor colorFilter;
	
	protected static final List<CommandContainer> NO_STACK = new ArrayList<>();
	
	public abstract List<CommandContainer> handle(LogicController logicController);
	
	public boolean matchColor(World world, Player player) {
		Tile actualTile = world.getActualTile();
		
		if ((actualTile == null || actualTile.getColor() == null) && colorFilter == null) {
			return true;
		}
		
		if (colorFilter == null) {
			return true;
		}
		
		if (actualTile == null || actualTile.getColor() == null) {
			return false;
		}
		
		return actualTile.getColor().equals(colorFilter);
	}
	
	protected abstract String getReadableInformation();
	
	public CommandContainer filter(TileColor color) {
		this.colorFilter = color;
		
		return this;
	}
	
	public String info() {
		return getReadableInformation() + "" + (colorFilter == null ? "" : " with color filter: " + colorFilter);
	}
	
}