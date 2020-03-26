package dev.jabo.game.states;

import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.obj.Enemy;
import dev.jabo.game.obj.GameObject;
import dev.jabo.game.obj.Player;
import dev.jabo.game.util.Utility;

public class GameState extends State {
	
	public static GameObject gameObjects[] = new GameObject[128];
	
	private int currentTimerBetweenEnemySpawn = 0;
	private int timerBetweenEnemySpawn = 60 * 10;
	
	public GameState(Game game) {
		super(game);
		
		initialize();
	}
	
	public void initialize() {
		new Player(game, 128, 128, 32, 32);
		new Enemy(game, 256, 128, 64, 64);
	}
	
	@Override
	public void update() {
		
		if(currentTimerBetweenEnemySpawn < timerBetweenEnemySpawn) {
			currentTimerBetweenEnemySpawn++;
		}
		
		if(currentTimerBetweenEnemySpawn >= timerBetweenEnemySpawn) {
			new Enemy(game, Utility.Random(0, 800), Utility.Random(0,  600), 32, 32);
			currentTimerBetweenEnemySpawn = 0;
		}
		
		for(GameObject gameObject : gameObjects) {
			if(gameObject == null)
				continue;
			gameObject.update();
		}
	}

	@Override
	public void render(Graphics g) {
		for(GameObject gameObject : gameObjects) {
			if(gameObject == null)
				continue;
			gameObject.render(g);
		}
	}
}
