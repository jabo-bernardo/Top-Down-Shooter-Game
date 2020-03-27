package dev.jabo.game.obj;

import java.awt.Graphics;

import dev.jabo.game.Game;

public class Pistol extends Gun {
	
	public Pistol(Game game) {
		
		super(game, 5, 20);
		setReloadInterval(0);
		setMaxAmmo(1000);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
