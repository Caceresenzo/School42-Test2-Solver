package caceresenzo.app.solver.school42.test2.commands.implementations.paint;

import caceresenzo.app.solver.school42.test2.commands.AbstractCommand;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.tiles.Tile;
import caceresenzo.app.solver.school42.test2.world.tiles.TileColor;

public abstract class AbstractPaintCommand extends AbstractCommand {
	
	@Override
	public void useCommand(World world, Player player) {
		Tile tile = world.getTiles()[player.getY()][player.getY()];
		
		if (tile != null) {
			tile.setColor(getTargetPaintColor());
		}
	}
	
	public abstract TileColor getTargetPaintColor();
	
}