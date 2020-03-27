package dev.jabo.game.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	
	// Window
	private JFrame window;
	// Rendering Canvas
	private Canvas canvas;
	// Window Information
	private String windowTitle;
	private int windowWidth, windowHeight;
	
	/**
	 * 
	 * @param windowTitle The title of the window
	 * @param windowWidth Dimension of the window
	 * @param windowHeight Dimension of the window
	 * 
	 * */
	public Display(String windowTitle, int windowWidth, int windowHeight) {
		
		this.windowTitle = windowTitle;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		createWindow();
		
	}
	
	// Handles the window creation
	private void createWindow() {
		
		window = new JFrame(windowTitle);
		window.setResizable(false);
		window.setSize(windowWidth, windowHeight);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
		canvas.setMinimumSize(new Dimension(windowWidth, windowHeight));
		canvas.setMaximumSize(new Dimension(windowWidth, windowHeight));
		canvas.setFocusable(false);
		
		window.add(canvas);
		window.pack();
		
	}
	
	// Getters
	/**
	 * @return A Canvas object
	 * */
	public Canvas getCanvas() 		{ return canvas; }
	
	/**
	 * @return A JFrame object
	 * */
	public JFrame getWindow() 		{ return window; }
	
	/**
	 * @return Window dimension horizontally
	 * */
	public int getWindowWidth() 	{ return windowWidth; }
	
	/**
	 * @return Window dimension vertically
	 * */
	public int getWindowHeight() 	{ return windowHeight; }
	
	/**
	 * @return Window title
	 * */
	public String getWindowTitle() 	{ return windowTitle; }

}
