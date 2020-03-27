package dev.jabo.game.obj;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import dev.jabo.game.Game;
import dev.jabo.game.Global;
import dev.jabo.game.audio.AudioLoader;
import dev.jabo.game.input.KeyBoard;
import dev.jabo.game.states.GameState;

public class Player extends GameObject {
	
	// Speed of the player
	private float velocity = 1.5f;
	
	// In what direction the player is facing?
	private int playerDirection;
	
	// Ready to shoot another bullet?
	private int readyToFire = 0; 
	
	// What gun the player is holding?
	private Gun gunType;
	
	// Timer for the next reload of a bullet
	private int currentReloadInterval;
	
	/**
	 * 
	 * @param game Defines the Game Object
	 * @param x Defines the horizontal position of the enemy
	 * @param y Defines the vertical position of the enemy
	 * @param width Defines the horizontal dimension of the enemy
	 * @param height Defines the vertical dimension of the enemy
	 *  
	 * */
	public Player(Game game, int x, int y, int width, int height) {
		super(game, x, y, width, height);
	}
	
	@Override
	public void initialize() {
		
		playerDirection = Global.DIRECTION_LEFT;
		
		setGun(new Pistol(game));
		
	}
	
	@Override
	public void update() {
		
		handleMovement();
		
		if(gunType.isReloading()) {
			currentReloadInterval++;
			if(currentReloadInterval >= gunType.getReloadInterval()) {
				gunType.reload(1);			
				currentReloadInterval = 0;
			}
			return;
		}
		
		if(readyToFire < gunType.getAttackSpeed()) {
			readyToFire++;
		}		
		
		if(gunType.isMagazineEmpty()) {
			gunType.reload(1);
			AudioLoader.reload.play();
			return;
		}
		
		if(keyBoard.isKeyPressed(32)) {
			if(readyToFire >= gunType.getAttackSpeed()) {
				shootBullet();
				AudioLoader.gunShot.play(false);
				gunType.useBullet(1);
				readyToFire = 0;
			}
		}
		
	}

	@Override
	public void render(Graphics g) {		
		g.fillRect(x, y, width, height);
	}
	
	public void shootBullet() {
		
		Bullet ins = new Bullet(game, x + width / 2, y + height / 2);
		ins.setDirection(playerDirection);
		ins.setDamage(gunType.getDamage());
		
	}
	
	public void handleMovement() {
		if(keyBoard.isKeyPressed(KeyBoard.KEY_LEFT)) 	{ x -= velocity; 	playerDirection = Global.DIRECTION_LEFT; }
		if(keyBoard.isKeyPressed(KeyBoard.KEY_RIGHT))	{ x += velocity; 	playerDirection = Global.DIRECTION_RIGHT; }
		if(keyBoard.isKeyPressed(KeyBoard.KEY_UP))		{ y -= velocity; 	playerDirection = Global.DIRECTION_UP; }
		if(keyBoard.isKeyPressed(KeyBoard.KEY_DOWN))	{ y += velocity; 	playerDirection = Global.DIRECTION_DOWN; }
	}
	
	public void setGun(Gun gun) {
		this.gunType = gun;
	}

}
