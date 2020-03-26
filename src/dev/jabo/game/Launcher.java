package dev.jabo.game;

public class Launcher {
	
	public static void main(String[] args) {
		
		// This is where everything starts
		Game game = new Game(Global.WINDOW_TITLE, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
		game.start();
		
	}

}
