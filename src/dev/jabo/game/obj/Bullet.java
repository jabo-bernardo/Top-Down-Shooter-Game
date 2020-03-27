package dev.jabo.game.obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import dev.jabo.game.Game;
import dev.jabo.game.Global;
import dev.jabo.game.fx.ParticleSystem;
import dev.jabo.game.lib.Vector2;
import dev.jabo.game.states.GameState;
import dev.jabo.game.util.Utility;

public class Bullet extends GameObject {
	
	private static final int DEFAULT_VELOCITY = 10;
	private static final int DEFAULT_RANGE = 50;
	private static final float DEFAULT_DAMAGE = 1f;
	
	// How fast is the bullet
	private int velocity;
	
	// How far the bullet can go.
	private int range;	
	// Counter if it reaches the range value the bullet will be destroyed
	private int rangeCounter;
	
	// Direction
	private int bulletDirection;
	
	// DAMAGE
	private float damage;

	public Bullet(Game game, int x, int y) {
		super(game, x, y, 16, 4);
	}

	@Override
	public void initialize() {
		
		// DEFAULT DECLARATIONS
		velocity = DEFAULT_VELOCITY;
		
		range = DEFAULT_RANGE;
		
		bulletDirection = Global.DIRECTION_LEFT;
		
		damage = DEFAULT_DAMAGE;
		
		// To define that this object is  Bullet
		this.tag = "Bullet";
	}

	@Override
	public void update() {
		// Update the collider in case the bullet moved.
		this.collider.setBounds(x, y, width, height);
		
		checkOnColliders();
		
		// Handles the range of the bullet
		if(rangeCounter < range) {
			
			if(bulletDirection == Global.DIRECTION_UP) 		{ y -= velocity; }
			if(bulletDirection == Global.DIRECTION_DOWN) 	{ y += velocity; }
			if(bulletDirection == Global.DIRECTION_LEFT) 	{ x -= velocity; }
			if(bulletDirection == Global.DIRECTION_RIGHT)	{ x += velocity; }
			
			rangeCounter++;
			
		} else {
			// Delete the object.
			Destroy();
		}
		
	}
	
	// Handles the collisions from objects
	private void checkOnColliders() {
		
		for (GameObject gameObject : GameState.gameObjects) {
			if(gameObject == null) continue;
			
			if(gameObject.tag == "Enemy") {
				if(gameObject.collider.contains(new Point(x, y))) {
					
					// Cast as Enemy to access damageBy()
					Enemy enemy = (Enemy) gameObject;
					
					// Critical?
					float critChance = (float) Math.random();
					
					float damage = this.damage;
					
					// Critical hit?					
					if(critChance > 0.8f) {
						damage *= Utility.Random(1.2f, 2.2f);
						GameState.addParticle(new ParticleSystem(game, 
								// Particle Type
								ParticleSystem.TYPE_CIRCULAR,
								//	Position
								new Vector2(x, y),
								// Minimum and maximum size
								1, 16, 
								// Minimum and maximum speed
								1, 6,
								// Count & Range
								10, 120, 
								new Color(255, 150, 50)));
					} else {
						GameState.addParticle(new ParticleSystem(game, 
								// Particle Type
								ParticleSystem.TYPE_CIRCULAR,
								//	Position
								new Vector2(x, y),
								// Minimum and maximum size
								1, 16, 
								// Minimum and maximum speed
								1, 6,
								// Count & Range
								10, 30, 
								new Color(255, 0, 255)));
					}
					
					enemy.damageBy(damage);
			
					// Boom! Delete the bullet
					Destroy();
					continue;
					
				}
			}
			
		}
		
	}

	@Override
	public void render(Graphics g) {
		// Renders the object
		// TODO: Edit Color
		g.fillRect(x, y, width, height);
	}
	
	/**
	 * @param direction In what direction should the bullet go
	 * */
	public void setDirection(int direction) {
		this.bulletDirection = direction;
		if(direction == Global.DIRECTION_UP || direction == Global.DIRECTION_DOWN) {
			width = 4;
			height = 16;
		}
	}
	
	/**
	 * @param vel Velocity or speed of the bullet
	 * */
	public void setVelocity(int vel) 	{ this.velocity = vel; }
	
	/**
	 * @param damage Damage of the bullet to an object
	 * */
	public void setDamage(int damage) 	{	this.damage = damage; }

}
