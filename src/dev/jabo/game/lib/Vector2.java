package dev.jabo.game.lib;

public class Vector2 {
	
	private int x, y;
	
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2(int xy) {
		this.x = xy;
		this.y = xy;
	}
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
}
