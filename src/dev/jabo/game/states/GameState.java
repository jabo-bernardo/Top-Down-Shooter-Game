package dev.jabo.game.states;

import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.audio.Audio;
import dev.jabo.game.fx.ParticleSystem;
import dev.jabo.game.obj.Enemy;
import dev.jabo.game.obj.GameObject;
import dev.jabo.game.obj.Player;
import dev.jabo.game.util.Utility;

public class GameState extends State {
	
	public static GameObject gameObjects[] = new GameObject[1000];
	public static ParticleSystem particles[] = new ParticleSystem[1000];
	
	private int currentTimerBetweenEnemySpawn = 0;
	private int timerBetweenEnemySpawn = 60 * 1;
	
	private Audio sampleBG = new Audio("res/audio/gun_shot.wav");
	
	public GameState(Game game) {
		super(game);
		
		initialize();
	}
	
	public void initialize() {
		new Player(game, 128, 128, 32, 32);
		new Enemy(game, 256, 360, 32, 32);
		new Enemy(game, 360, 360, 32, 32);
		
		
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
		for(ParticleSystem particle : particles) {
			if (particle == null) continue;
			particle.Update();
		}
	}

	@Override
	public void render(Graphics g) {
		for(GameObject gameObject : gameObjects) {
			if(gameObject == null)
				continue;
			gameObject.render(g);
		}
		for(ParticleSystem particle : particles) {
			if (particle == null) continue;
			particle.Render(g);
		}
	}
	
	public static void addParticle(ParticleSystem particle) {
		for(int i = 0; i < particles.length; i++) {
			if(particles[i] == null) {
				particles[i] = particle;
				break;
			}
		}
	}
	
	public static void removeParticle(int id) {
		for(int i = 0; i < particles.length; i++) {
			if(particles[i] != null) {
				if(particles[i].getID() == id) {
					particles[i] = null;
				}
				return;
			}
		}
	}
	
	public static int getParticleLength() {
		int num = 0;
		for (ParticleSystem particleSystem : particles) {
			if(particleSystem != null) num++;
 		}
 		return num;
	}
	
}
