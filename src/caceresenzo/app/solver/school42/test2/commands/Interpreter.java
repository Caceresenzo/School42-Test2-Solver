package caceresenzo.app.solver.school42.test2.commands;

import caceresenzo.app.solver.school42.test2.commands.implementations.paint.PaintBlueCommand;
import caceresenzo.app.solver.school42.test2.commands.implementations.paint.PaintGreenCommand;
import caceresenzo.app.solver.school42.test2.commands.implementations.paint.PaintOrangeCommand;
import caceresenzo.app.solver.school42.test2.commands.implementations.rotate.LeftRotationCommand;
import caceresenzo.app.solver.school42.test2.commands.implementations.rotate.RightRotationCommand;
import caceresenzo.app.solver.school42.test2.commands.implementations.walk.WalkCommand;
import caceresenzo.app.solver.school42.test2.player.Player;
import caceresenzo.app.solver.school42.test2.world.World;

public enum Interpreter {
	
	/* Walk */
	WALK(WalkCommand.class), //
	
	/* Rotation */
	ROTATION_RIGHT(RightRotationCommand.class), //
	ROTATION_LEFT(LeftRotationCommand.class), //
	
	/* Paint */
	PAINT_BLUE(PaintBlueCommand.class), //
	PAINT_GREEN(PaintGreenCommand.class), //
	PAINT_ORNAGE(PaintOrangeCommand.class), //
	
	;
	
	private final Class<? extends AbstractCommand> clazz;
	
	private Interpreter(Class<? extends AbstractCommand> clazz) {
		this.clazz = clazz;
	}
	
	public AbstractCommand create() throws InstantiationException, IllegalAccessException {
		return clazz.newInstance();
	}
	
	public AbstractCommand silentCreate() {
		try {
			return create();
		} catch (Exception exception) {
			return null;
		}
	}
	
	public void use(World world, Player player) {
		try {
			create().useCommand(world, player);
		} catch (Exception exception) {
			throw new IllegalStateException("Error when executing command.", exception);
		}
	}

	public static Interpreter fromString(String commandString) {
		for (Interpreter interpreter : Interpreter.values()) {
			if (interpreter.toString().equalsIgnoreCase(commandString)) {
				return interpreter;
			}
		}
		
		throw new IllegalStateException("Unknown interpreter: " + commandString);
	}
	
}