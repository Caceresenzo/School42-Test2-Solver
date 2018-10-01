package caceresenzo.app.solver.school42.test2.ui;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;

import caceresenzo.app.solver.school42.test2.Bootstrap;

public class SolverFrame extends JFrame {
	
	public static final String TITLE = "Solution Finder";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = WIDTH / 16 * 9;
	
	private Canvas canvas;
	
	public SolverFrame() {
		this.canvas = new Canvas();
		
		initializeFrame();
		
		if (Bootstrap.DISABLE_DRAW) {
			setVisible(false);
		}
	}
	
	private void initializeFrame() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);
		
		initializeCanvas();
		
		add(canvas);
		canvas.createBufferStrategy(3);
	}
	
	private void initializeCanvas() {
		canvas.setSize(WIDTH, HEIGHT);
		canvas.setBackground(Color.BLACK);
		canvas.setVisible(true);
		canvas.setFocusable(false);
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
}