package caceresenzo.app.solver.school42.test2.commands.implementations.rotate;

import caceresenzo.app.solver.school42.test2.commands.AbstractCommand;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.libs.logger.Logger;

public abstract class AbstractRotationCommand extends AbstractCommand {
	
	@Override
	public void useCommand(World world, Player player) {
		player.rotate(getRotationValue());
		
		Logger.info("Player has rotated to: %s", player.getOrientation().toString());
	}
	
	public abstract int getRotationValue();
	
}