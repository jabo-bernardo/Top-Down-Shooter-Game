package dev.jabo.game.obj;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.jabo.game.Game;
import dev.jabo.game.input.KeyBoard;
import dev.jabo.game.input.Mouse;
import dev.jabo.game.states.GameState;

public abstract class GameObject {

	// To access the main loop
	protected Game game;
	
	// Events
	protected KeyBoard keyBoard;
	protected Mouse mouse;
	
	// Position & Dimension
	protected int x, y;
	protected int width, height;
	
	// Unique ID
	protected int id;
	
	// Collision Detector
	protected Rectangle collider;
	
	// What makes it different from others
	protected String tag;
	
	/**
	 * 
	 * @param game Defines the Game Object
	 * @param x Defines the horizontal position of the enemy
	 * @param y Defines the vertical position of the enemy
	 * @param width Defines the horizontal dimension of the enemy
	 * @param height Defines the vertical dimension of the enemy
	 *  
	 * */
	public GameObject(Game game, int x, int y, int width, int height) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.keyBoard = game.getKeyBoard();
		this.mouse = game.getMouse();
		
		for (int i = 0 ; i < GameState.gameObjects.length; i++) {
			if(GameState.gameObjects[i] == null) {
				GameState.gameObjects[i] = this;
				this.id = i;
				break;
			}
		}
		
		// Set the initial collider
		collider = new Rectangle(x, y, width, height);
		
		initialize();
	}
	
	public abstract void initialize();
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	/**
	 * @return Identity of the GameObject
	 * */
	public int getID() { return id; }
	
	/**
	 * @return The collider
	 * */
	public Rectangle getCollider() { return collider; }
	
	// Remove this GameObject from the scene
	public void Destroy() {
		GameState.gameObjects[id] = null;
	}
	
}
