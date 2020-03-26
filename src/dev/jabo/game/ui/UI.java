package dev.jabo.game.ui;

import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.input.Mouse;

public abstract class UI {

	protected Game game;
	protected Mouse mouse;
	protected int x, y;
	
	public UI(Game game, int x, int y) {
		this.x = x;
		this.y = y;
		this.game = game;
		mouse = game.getMouse();
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}
