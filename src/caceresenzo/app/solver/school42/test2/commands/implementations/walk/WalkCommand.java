package caceresenzo.app.solver.school42.test2.commands.implementations.walk;

import caceresenzo.app.solver.school42.test2.commands.AbstractCommand;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.tiles.StarTile;
import caceresenzo.app.solver.school42.test2.world.tiles.Tile;
import caceresenzo.libs.logger.Logger;

public class WalkCommand extends AbstractCommand {
	
	@Override
	public void useCommand(World world, Player player) {
		int x = player.getX(), y = player.getY();
		
		switch (player.getOrientation()) {
			case UP: {
				x--;
				break;
			}
			
			case RIGHT: {
				y++;
				break;
			}
			
			case DOWN: {
				x++;
				break;
			}
			
			case LEFT: {
				y--;
				break;
			}
			
			default: {
				throw new IllegalStateException("Invalid orientation: " + player.getOrientation());
			}
		}
		
		Logger.info("Walking into position x:%s / y:%s", x, y);
		
		player.setPosition(x, y);
		// Logger.info(":: " + world.isEverythingOk() + " -- " + world.getActualTile());
		
		Tile actualTile = world.getActualTile();
		if (actualTile instanceof StarTile) {
			world.getTiles()[y][x] = new Tile(actualTile.getColor());
		}
	}
	
}