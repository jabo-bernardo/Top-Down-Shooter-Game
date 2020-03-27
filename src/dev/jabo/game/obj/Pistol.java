package dev.jabo.game.obj;

import java.awt.Graphics;

import dev.jabo.game.Game;

public class Pistol extends Gun {
	
	public Pistol(Game game) {
		
		// GAME, Attack Speed, Damage
		super(game, 30, 20);
		setReloadInterval(10);
		setMaxAmmo(10);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
