package dev.jabo.game.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.fx.ParticleSystem;
import dev.jabo.game.lib.Vector2;

public class DevState extends State {
	
	private ParticleSystem ps;
	
	public DevState(Game game) {
		super(game);	
		
		Initialize();
	}
	
	public void Initialize() {
		
		ps = new ParticleSystem(
				// Particle Type
				ParticleSystem.TYPE_CIRCULAR,
				// Position
				new Vector2(360, 360),
				// Minimum and maximum size
				1, 16, 
				// Minimum and maximum speed
				1, 6,
				// Count & Range
				50, 60, 
				new Color(255, 0, 0)); 
		
	}

	@Override
	public void update() {
		ps.Update();
	}

	@Override
	public void render(Graphics g) {
		ps.Render(g);
	}

	
	
}
