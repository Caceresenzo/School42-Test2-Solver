package caceresenzo.app.solver.school42.test2.commands.implementations.container;

import java.util.List;

import caceresenzo.app.solver.school42.test2.commands.AbstractCommand;
import caceresenzo.app.solver.school42.test2.commands.CommandContainer;
import caceresenzo.app.solver.school42.test2.logic.LogicController;

public class WorldCommandContainer extends CommandContainer {
	
	private final AbstractCommand command;
	
	public WorldCommandContainer(AbstractCommand command) {
		this.command = command;
	}
	
	@Override
	public List<CommandContainer> handle(LogicController logicController) {
		command.useCommand(logicController.getWorld(), logicController.getWorld().getPlayer());
		
		return NO_STACK;
	}
	
	public AbstractCommand getCommand() {
		return command;
	}
	
	@Override
	protected String getReadableInformation() {
		return command.getClass().getSimpleName();
	}
	
}