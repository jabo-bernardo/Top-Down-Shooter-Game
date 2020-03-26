package dev.jabo.game.states;

import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.input.KeyBoard;
import dev.jabo.game.input.Mouse;

public abstract class State {
	
	protected Game game;
	protected KeyBoard keyBoard;
	protected Mouse mouse;
	
	public static State currentState;
	
	public State(Game game) {
		this.game = game;
		keyBoard = game.getKeyBoard();
		mouse = game.getMouse();
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
}
