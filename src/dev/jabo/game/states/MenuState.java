package dev.jabo.game.states;

import java.awt.Font;
import java.awt.Graphics;

import dev.jabo.game.Game;
import dev.jabo.game.ui.Button;

public class MenuState extends State {
	
	private Button play_btn;
	private Button do_nothing_btn;

	public MenuState(Game game) {
		super(game);
		
		initialize();
	}
	
	public void initialize() {
		play_btn = new Button(game, 0, 0, 128, 32);
		play_btn.setText("PLAY GAME");
		play_btn.setFont(new Font("Roboto", Font.BOLD, 16));
		
		do_nothing_btn = new Button(game, 0, 40, 128, 64);
		do_nothing_btn.setText("QUIT GAME");
	}
	
	@Override
	public void update() {
		if(play_btn.isClicked()) {
			State.setState(game.getGameState());
		}
		
		if(do_nothing_btn.isClicked()) {
			game.stop();
		}
	}

	@Override
	public void render(Graphics g) {
		play_btn.render(g);
		do_nothing_btn.render(g);
	}

}
