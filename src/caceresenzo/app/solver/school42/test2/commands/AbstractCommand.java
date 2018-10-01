package caceresenzo.app.solver.school42.test2.commands;

import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.World;

public abstract class AbstractCommand {
	
	public abstract void useCommand(World world, Player player);
	
}