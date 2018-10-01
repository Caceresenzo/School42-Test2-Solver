package caceresenzo.app.solver.school42.test2.commands.implementations.container;

import java.util.List;

import caceresenzo.app.solver.school42.test2.commands.CommandContainer;
import caceresenzo.app.solver.school42.test2.function.Function;
import caceresenzo.app.solver.school42.test2.logic.LogicController;

public class FunctionCommandContainer extends CommandContainer {
	
	private final Function function;
	
	public FunctionCommandContainer(Function function) {
		this.function = function;
	}
	
	@Override
	public List<CommandContainer> handle(LogicController logicController) {
		return function.commands();
	}
	
	public Function getFunction() {
		return function;
	}
	
	@Override
	protected String getReadableInformation() {
		return function.getClass().getSimpleName() + " #" + function.getIdentifier();
	}
	
}