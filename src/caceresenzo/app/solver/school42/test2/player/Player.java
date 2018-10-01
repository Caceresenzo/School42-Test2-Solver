package caceresenzo.app.solver.school42.test2.player;

import java.awt.Color;
import java.awt.Graphics;

import caceresenzo.app.solver.school42.test2.ui.Drawable;
import caceresenzo.app.solver.school42.test2.world.WorldManager;

public class Player implements Drawable {
	
	private int x, y;
	private PlayerOrientation orientation;
	
	public Player(Player player) {
		this(player.x, player.y, player.orientation);
	}
	
	public Player() {
		this(0, 0);
	}
	
	public Player(int x, int y) {
		this(x, y, PlayerOrientation.UP);
	}
	
	public Player(int x, int y, PlayerOrientation orientation) {
		this.x = x;
		this.y = y;
		this.orientation = orientation;
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		
		int halfLittleTile = WorldManager.TILE_LITTLE_SIZE / 2;
		int xTile = (y * WorldManager.TILE_SIZE) + halfLittleTile, yTile = (x * WorldManager.TILE_SIZE) + halfLittleTile;
		
		graphics.fillOval(xTile, yTile, WorldManager.TILE_LITTLE_SIZE, WorldManager.TILE_LITTLE_SIZE);
		
		graphics.setColor(Color.WHITE);
		
		int xTarget = 0, yTarget = 0;
		switch (orientation) {
			case UP: {
				yTarget--;
				break;
			}
			
			case RIGHT: {
				xTarget++;
				break;
			}
			
			case DOWN: {
				yTarget++;
				break;
			}
			
			case LEFT: {
				xTarget--;
				break;
			}
		}
		
		xTarget *= WorldManager.TILE_SIZE;
		yTarget *= WorldManager.TILE_SIZE;
		
		graphics.drawLine(xTile + halfLittleTile, yTile + halfLittleTile, xTile + halfLittleTile + xTarget, yTile + halfLittleTile + yTarget);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public PlayerOrientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(PlayerOrientation orientation) {
		this.orientation = orientation;
	}
	
	public void rotate(int rotationValue) {
		this.orientation = orientation.rotate(rotationValue);
	}
	
}