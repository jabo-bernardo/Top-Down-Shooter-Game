package dev.jabo.game.util;

public class Utility {
	
	public static int Random(int min, int max) {		
		int randomNumber = (int) Math.floor(Math.random() * max);
		
		while(randomNumber < min) { randomNumber = (int) Math.floor(Math.random() * max); }
		
		return randomNumber;
	}
	
	public static float Random(float min, float max) {		
		float randomNumber = (float) (Math.random() * max);
		
		while(randomNumber < min) { randomNumber = (float) (Math.random() * max); }
		
		return randomNumber;
	}

}
