package dev.jabo.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.jabo.game.display.Display;
import dev.jabo.game.input.KeyBoard;
import dev.jabo.game.input.Mouse;
import dev.jabo.game.states.DevState;
import dev.jabo.game.states.GameState;
import dev.jabo.game.states.MenuState;
import dev.jabo.game.states.State;

public class Game implements Runnable {
	
	// Threading stuffs
	private Thread thread;	
	private boolean gameRunning;
	
	// Rendering stuffs
	private Graphics g;
	private BufferStrategy bs;
	
	// Window related stuffs
	private Display display;
	private String windowTitle;
	private int windowWidth, windowHeight;	
	
	// States
	private GameState gameState;
	private MenuState menuState;
	private DevState devState;
	
	// Events 
	private KeyBoard keyBoard;
	private Mouse mouse;

	/**
	 * 
	 * @param windowTitle Defines the title of the window.
	 * @param windowWidth Defines the dimension of the window horizontally.
	 * @param windowHeight Defines the dimension of the window vertically.
	 * 
	 * 
	 * */
	public Game(String windowTitle, int windowWidth, int windowHeight) {
		
		this.windowTitle = windowTitle;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		
	}
	
	// Initializes variables
	public void Initialize() {
		
		display = new Display(windowTitle, windowWidth, windowHeight);
		
		// Initialize Listeners
		keyBoard = new KeyBoard();
		mouse = new Mouse();
		
		// COnfigure Listeners
		display.getWindow().addKeyListener(keyBoard);
		display.getCanvas().addMouseListener(mouse);
		display.getCanvas().addMouseMotionListener(mouse);
		
		// States
		gameState = new GameState(this);
		menuState = new MenuState(this);
		devState = new DevState(this);
		
		// Set State
		State.setState(gameState);
		
	}
	
	// Updates states.
	public void Update() {
		
		if (State.getState() != null)
			State.getState().update();
		
	}
	
	// Show something on screen!
	public void Render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen for next frame
		g.clearRect(0, 0, windowWidth, windowHeight);
		// Draw things here..
		
		// Renders the current state
		if(State.getState() != null)
			State.getState().render(g);
		
		// Show the rendered scenes
		bs.show();
		g.dispose();
		
	}

	@Override
	public void run() {
		
		// Initialize Variables
		Initialize();
		
		// Handles the FPS
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(gameRunning) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				Update();
				Render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
		
	}
	
	
	public synchronized void start() {
		
		if(gameRunning) return;
		
		gameRunning = true;
		thread = new Thread(this);
		thread.run();
		
	}
	
	public synchronized void stop() {
		
		if(!gameRunning) return;
		
		gameRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.out.println("Error Occurred while stopping the thread: " + e.getLocalizedMessage());
		}
		
		// Good bye player. 
		System.exit(0);
		
	}
	
	// Getters
	/**
	 * 
	 * @return The KeyBoard object
	 * 
	 * */
	public KeyBoard getKeyBoard() {	return keyBoard; }
	
	/**
	 * 
	 * @return The Mouse object
	 * 
	 * */
	public Mouse getMouse() { return mouse; }
	
	// Getters for States.	
	/**
	 * 
	 * @return The GameState object
	 * 
	 * */	
	public GameState getGameState() { return gameState; }
	
	/**
	 * 
	 * @return The MenuState object
	 * 
	 * */
	public MenuState getMenuState() { return menuState;	}
	
}
