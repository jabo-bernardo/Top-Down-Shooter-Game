package dev.jabo.game.obj;

import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.audio.AudioLoader;

public abstract class Gun {

	// To access the main loop
	protected Game game;
	
	// The interval between shots
	protected int attackSpeed;
	
	// How much will be the damage on collide
	protected int damageOnCollide;
	
	// How much time will it take to reload a bullet
	protected int reloadInterval;
	
	// Ammunition Variables
	protected int ammo;	
	protected int maxAmmo;
	
	// Trigger when the magazine is reloading
	protected boolean isReloading;
	
	/**
	 * 
	 * @param game Game Object
	 * @param attackSpeed The interval between shots
	 * @param damageOnCollide How much will be the damage on collide
	 * 
	 * */
	public Gun(Game game, int attackSpeed, int damageOnCollide) {
		
		this.game = game;
		this.attackSpeed = attackSpeed;
		this.damageOnCollide = damageOnCollide;
		
		ammo = maxAmmo;
		
	}
	
	public void reload(int numberOfAmmo) {
		if(ammo >= maxAmmo) { ammo = maxAmmo; isReloading = false; AudioLoader.reloaded.play(); return; }
		isReloading = true;
		ammo += numberOfAmmo;
	}
	
	public boolean isMagazineEmpty() {
		if(ammo <= 0) return true;
		return false;
	}
	
	public boolean isReloading() {
		return isReloading;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public int getAttackSpeed() {
		return attackSpeed;
	}
	
	public int getDamage() {
		return damageOnCollide;
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public int getReloadInterval() {
		return reloadInterval;
	}
	
	public void setReloadInterval(int reloadInterval) {
		this.reloadInterval = reloadInterval;
	}
	
	public void setMaxAmmo(int maxAmmo) {
		this.maxAmmo = maxAmmo;
		this.ammo = maxAmmo;
	}
	
	public void useBullet(int bulletCount) {
		this.ammo -= bulletCount;
	}
	
}
