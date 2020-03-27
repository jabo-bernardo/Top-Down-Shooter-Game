package dev.jabo.game.fx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import dev.jabo.game.Game;
import dev.jabo.game.lib.Circle;
import dev.jabo.game.lib.Vector2;
import dev.jabo.game.states.GameState;
import dev.jabo.game.util.Utility;

public class ParticleSystem {
	
	public static final int TYPE_CIRCULAR = 0;
	
	private Game game;
	private int type;
	
	private int particleCount;
	
	protected Particle[] particles;
	
	private int x, y;
	private int minimumSize, maximumSize;
	
	private int minimumVelocity, maximumVelocity;
	
	private Color color;
	
	protected Circle range;
	
	private int id;
	
	/**
	 * @param game Defines the Game Object
	 * @param particleType Type of particle
	 * @param position The position of the particle in the scene
	 * @param minimumSize The possible smallest particle in the ParticleSystem
	 * @param maximumSize The possible largest particle in the ParticleSystem
	 * @param minimumVelocity The possible slowest particle in the ParticleSystem
	 * @param maximumVelocity The possible fastest particle in the ParticleSystem
	 * @param particleCount How many particle
	 * @param particleRange How far can a particle be
	 * @param color What color
	 * */
	public ParticleSystem(int particleType, Vector2 position, int minimumSize, int maximumSize, int minimumVelocity, int maximumVelocity, int particleCount, int particleRange, Color color) {
		this.game = game;
		
		this.type = particleType;
		
		this.particleCount = particleCount;
		
		this.x = position.getX();
		this.y = position.getY();
		
		this.minimumSize = minimumSize;
		this.maximumSize = maximumSize;
		
		this.minimumVelocity = minimumVelocity;
		this.maximumVelocity = maximumVelocity;
		
		this.color = color;
		
		this.range = new Circle(new Point(this.x, this.y), particleRange);
		
		this.id = GameState.getParticleLength();
		
		Initialize();
	}
	
	public void Initialize() {
		
		particles = new Particle[particleCount];		
		
		for(int  i = 0; i < particles.length; i++) {
			particles[i] = new Particle(this, i);
			particles[i].setPosition(x, y);
			particles[i].setVelocity(Utility.Random(minimumVelocity, maximumVelocity + 1));
			particles[i].setScale(Utility.Random(minimumSize, maximumSize + 1));
			
			if(this.type == ParticleSystem.TYPE_CIRCULAR) {
				
				particles[i].setDirection(Utility.Random(0, 8));
				
			}
			
		}
		
	}
	
	public void Update() {
		for (Particle particle : particles) {
			if(particle == null) continue;			
			particle.Update();
		}
		if(isDone()) {
			Destroy();
		}
	}
	
	public void Render(Graphics g) {
		for (Particle particle : particles) {
			if(particle == null) continue;
			g.setColor(color);
			particle.Render(g);
		}
	}
	
	public boolean isDone() {
		int activeParticles = 0;
		for (Particle particle : particles){
			if(particle != null) activeParticles++;
		}
		System.out.println(activeParticles);
		if(activeParticles > 0)
			return false;
		if(activeParticles == 0)
			return true;
		return true;
	}
	
	public void Destroy() {
		GameState.removeParticle(id);
 	 }
	
	public int getID() {
		return id;
	}

}
