package caceresenzo.app.solver.school42.test2.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import caceresenzo.app.solver.school42.test2.commands.CommandContainer;
import caceresenzo.app.solver.school42.test2.commands.Interpreter;
import caceresenzo.app.solver.school42.test2.commands.implementations.container.FunctionCommandContainer;
import caceresenzo.app.solver.school42.test2.commands.implementations.container.WorldCommandContainer;
import caceresenzo.app.solver.school42.test2.function.Function;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.tiles.TileColor;
import caceresenzo.libs.logger.Logger;
import caceresenzo.libs.random.Randomizer;

public class LogicProcessor implements Iterator<CommandContainer> {
	
	private final LogicController controller;
	
	private List<CommandContainer> stacktrace;
	
	public LogicProcessor(LogicController controller) {
		this.controller = controller;
		
		this.stacktrace = new ArrayList<>();
	}
	
	@Override
	public boolean hasNext() {
		return !stacktrace.isEmpty();
	}
	
	@Override
	public CommandContainer next() {
		if (stacktrace.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		CommandContainer commandContainer = stacktrace.get(0);
		stacktrace.remove(0);
		
		if (commandContainer.matchColor(controller.getWorld(), controller.getWorld().getPlayer())) {
			stacktrace.addAll(0, commandContainer.handle(controller));
		}
		
		// ThreadUtils.sleep(150L);
		// dumpTrace();
		
		return commandContainer;
	}
	
	@Override
	public void remove() {
		throw new IllegalStateException("Not supported.");
	}
	
	public void fill() {
		stacktrace.clear();
		
		final World world = controller.getWorld();
		final List<Interpreter> availableCommands = world.getAvailableCommands();
		final List<Function> functions = world.getFunctions();
		final List<TileColor> availableWorldColor = world.getAvailableWorldColor();
		
		stacktrace.add(new FunctionCommandContainer(functions.get(0)));
		
		functions.get(0).addCommand(new FunctionCommandContainer(functions.get(1)).filter(TileColor.BLUE));
		functions.get(0).addCommand(new WorldCommandContainer(Interpreter.ROTATION_RIGHT.silentCreate()));
		functions.get(0).addCommand(new FunctionCommandContainer(functions.get(1)).filter(TileColor.BLUE));
		
		functions.get(1).addCommand(new WorldCommandContainer(Interpreter.WALK.silentCreate()).filter(TileColor.BLUE));
		functions.get(1).addCommand(new WorldCommandContainer(Interpreter.ROTATION_RIGHT.silentCreate()).filter(TileColor.ORANGE));
		functions.get(1).addCommand(new FunctionCommandContainer(functions.get(1)).filter(TileColor.BLUE));
		functions.get(1).addCommand(new WorldCommandContainer(Interpreter.ROTATION_RIGHT.silentCreate()).filter(TileColor.ORANGE));
		functions.get(1).addCommand(new WorldCommandContainer(Interpreter.WALK.silentCreate()));
		
		// if (false) {
		// for (Function function : controller.getWorld().getFunctions()) {
		//
		// Logger.info("TRACER: ");
		// Logger.info(" | Function #%s:", function.getIdentifier());
		//
		// for (int i = 0; i < function.getSize(); i++) {
		// CommandContainer commandContainer = getRandomElement(availableCommands, functions);
		//
		// if (commandContainer != null) {
		// int colorFilterValue = Randomizer.advancedRandomRangeInteger(0, availableWorldColor.size());
		// if (colorFilterValue < availableWorldColor.size()) { /* Using the size (index + 1) as null value */
		// commandContainer.filter(availableWorldColor.get(colorFilterValue));
		// }
		//
		// function.addCommand(commandContainer);
		//
		// Logger.info(" | Action: %s", commandContainer.info());
		// }
		// }
		// }
		// }
		
	}
	
	private CommandContainer getRandomElement(List<Interpreter> availables, List<Function> functions) {
		int randomValue = Randomizer.nextRangeInt(-1, availables.size() + functions.size());
		
		if (randomValue == -1) {
			return null;
		}
		
		if (randomValue >= availables.size()) {
			return new FunctionCommandContainer(functions.get(Math.max(randomValue - availables.size() - 1, functions.size() - 1)));
		} else {
			return new WorldCommandContainer(availables.get(randomValue).silentCreate());
		}
	}
	
	public void dumpTrace() {
		Logger.info("STACK TRACE DUMP -- START");
		for (CommandContainer trace : stacktrace) {
			Logger.info(" -- >> %s", trace.info());
		}
		Logger.info("STACK TRACE DUMP -- STOP");
	}
	
	public List<CommandContainer> getStacktrace() {
		return stacktrace;
	}
	
}