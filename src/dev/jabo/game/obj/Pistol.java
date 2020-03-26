package dev.jabo.game.obj;

import java.awt.Graphics;

import dev.jabo.game.Game;

public class Pistol extends Gun {
	
	public Pistol(Game game) {
		
		super(game, 5, 5);
		setReloadInterval(5);
		setMaxAmmo(10);
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}
