package caceresenzo.app.solver.school42.test2.commands.implementations.paint;

import caceresenzo.app.solver.school42.test2.world.tiles.TileColor;

public class PaintGreenCommand extends AbstractPaintCommand {
	
	@Override
	public TileColor getTargetPaintColor() {
		return TileColor.GREEN;
	}
	
}