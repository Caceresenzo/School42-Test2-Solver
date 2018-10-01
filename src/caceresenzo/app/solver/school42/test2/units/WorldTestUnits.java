package caceresenzo.app.solver.school42.test2.units;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.JFrame;

import caceresenzo.app.solver.school42.test2.codec.implementations.SimpleWorldCodec;
import caceresenzo.app.solver.school42.test2.world.World;
import caceresenzo.app.solver.school42.test2.world.WorldManager;
import caceresenzo.libs.test.SimpleTestUnits;

public class WorldTestUnits extends SimpleTestUnits {
	
	public static class CanvasTestUnit {
		
		public static final int SIZE = 800;
		
		public static void main(String[] args) {
			initializeUnit(false);
			
			try {
				final String title = "Test Window";
				final int width = 1200;
				final int height = width / 16 * 9;
				
				// Creating the frame.
				JFrame frame = new JFrame(title);
				
				frame.setSize(width, height);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setResizable(true);
				frame.setVisible(true);
				
				// Creating the canvas.
				Canvas canvas = new Canvas();
				
				canvas.setSize(width, height);
				canvas.setBackground(Color.BLACK);
				canvas.setVisible(true);
				canvas.setFocusable(false);
				
				// Putting it all together.
				frame.add(canvas);
				
				canvas.createBufferStrategy(3);
				
				boolean running = true;
				
				BufferStrategy bufferStrategy;
				Graphics graphics;
				
				World world = new SimpleWorldCodec().read(new File("worlds/level_13.txt"));
				
				WorldManager worldPanel = new WorldManager(world);
				while (running) {
					bufferStrategy = canvas.getBufferStrategy();
					graphics = bufferStrategy.getDrawGraphics();
					graphics.clearRect(0, 0, width, height);
					
					worldPanel.draw(graphics);
					
					bufferStrategy.show();
					graphics.dispose();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
	}
	
}
