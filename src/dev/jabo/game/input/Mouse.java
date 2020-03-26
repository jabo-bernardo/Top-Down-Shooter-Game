package dev.jabo.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	
	// Mouse Positions
	private int mouseX, mouseY;
	
	// Other Mouse Configs
	private boolean mouseOnScreen, mouseClicked;

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		mouseOnScreen = true;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		mouseOnScreen = false;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouseClicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouseClicked = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public boolean isMouseOnScreen() {
		return mouseOnScreen;
	}
	
	public boolean isMouseClicked() {
		return mouseClicked;
	}

}
