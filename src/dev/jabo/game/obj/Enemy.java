package dev.jabo.game.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import dev.jabo.game.Game;
import dev.jabo.game.states.GameState;
import dev.jabo.game.util.Utility;

public class Enemy extends GameObject {
	
	// TODO: AI Follow the player.
	// TODO: Add graphics
	
	// Health of the enemy
	public float health = 100;
	
	/**
	 * 
	 * @param game Defines the Game Object
	 * @param x Defines the horizontal position of the enemy
	 * @param y Defines the vertical position of the enemy
	 * @param width Defines the horizontal dimension of the enemy
	 * @param height Defines the vertical dimension of the enemy
	 *  
	 * */
	public Enemy(Game game, int x, int y, int width, int height) {
		super(game, x, y, width, height);
	}

	@Override
	public void initialize() {
		
		// To define that this object is  Bullet
		this.tag = "Enemy";
		
	}

	@Override
	public void update() {
		
		// Update the collider in case the enemy moved.
		this.collider.setBounds(x, y, width, height);
		
		// If theres no more health kill the enemy.
		if(health <= 0) {
			Destroy();
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(new Color(255, 0, 0));
		g.fillRect(x, y, width, height);
		
	}
	
	/**
	 * @param damageCount How much the enemy will hurt?
	 * */
	public void damageBy(float damageCount) { this.health -= damageCount; }

}
