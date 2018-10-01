package caceresenzo.app.solver.school42.test2.logic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import caceresenzo.app.solver.school42.test2.Bootstrap;
import caceresenzo.app.solver.school42.test2.ui.SolverFrame;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.WorldManager;
import caceresenzo.libs.logger.Logger;

public class LogicController {
	
	public static final int STACKOVERFLOW_LIMIT = 100;
	public static final int STEP_LIMIT = 300;
	public static final int FRAME_SKIP = 20;
	
	private LogicProcessor processor;
	private World world;
	
	private WorldManager worldManager;
	private SolverFrame solverFrame;
	
	private boolean running = true;
	
	public LogicController(World world) {
		this.processor = new LogicProcessor(this);
		this.world = world;
		
		this.worldManager = new WorldManager(world);
		this.solverFrame = new SolverFrame();
	}
	
	public void find() {
		BufferStrategy bufferStrategy = null;
		Graphics graphics = null;
		
		int step = 0;
		boolean success = false, lastLoop = false;
		while (running || lastLoop) {
			if (!Bootstrap.DISABLE_DRAW) {
				bufferStrategy = solverFrame.getCanvas().getBufferStrategy();
				graphics = bufferStrategy.getDrawGraphics();
				// if (step % FRAME_SKIP == 0) {
				graphics.clearRect(0, 0, SolverFrame.WIDTH, SolverFrame.HEIGHT);
				// }
			}
			
			if (processor.hasNext()) {
				step++;
				processor.next();
			}
			
			if (!Bootstrap.DISABLE_DRAW) {
				// if (step % FRAME_SKIP == 0) {
				worldManager.draw(graphics);
				// }
			}
			
			if (!world.isEverythingOk() || !processor.hasNext() || processor.getStacktrace().size() > STACKOVERFLOW_LIMIT || step > STEP_LIMIT) {
				world.restore();
				
				if (!Bootstrap.DISABLE_DRAW) {
					worldManager.draw(graphics);
				}
				
				Logger.info("Reseting:");
				Logger.info("  -> everything is not ok ? %s", !world.isEverythingOk());
				Logger.info("  -> processor don't has next ? %s", !processor.hasNext());
				Logger.info("  -> overflow limit reached ? %s", processor.getStacktrace().size() > STACKOVERFLOW_LIMIT);
				Logger.info("  -> step limit reached ? %s", step > STEP_LIMIT);
				
				step = 0;
				
				processor.fill();
			}
			
			success = world.isCompleted();
			
			if (success && !lastLoop) {
				worldManager.draw(graphics);
				
				graphics.setColor(Color.GREEN);
				graphics.drawString("SUCCESS!", 5, 15);
				Logger.info("SUCCESS!");
			}
			
			if (!Bootstrap.DISABLE_DRAW) {
				bufferStrategy.show();
				graphics.dispose();
			}
			
			if (lastLoop) {
				break;
			}
			if (success) {
				running = false;
				lastLoop = true;
			}
		}
	}
	
	public World getWorld() {
		return world;
	}
	
}