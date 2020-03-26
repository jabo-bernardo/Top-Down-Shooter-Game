package dev.jabo.game.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import dev.jabo.game.Game;

public class Button extends UI {
	
	public static final Color DEFAULT_BTN_COLOR = new Color(155, 155, 155);
	public static final Color DEFAULT_HOVER_BTN_COLOR = new Color(100, 100, 100);
	public static final Color DEFAULT_CLICKED_BTN_COLOR = new Color(45, 45, 45);
	public static final Color DEFAULT_BTN_TEXT_COLOR = new Color(0, 0, 0);
	
	public static final Font DEFAULT_BTN_FONT = new Font("Roboto", Font.PLAIN, 16);

	private int width, height;
	private Rectangle boundingBox;
	
	private Color defaultColor;
	private Color hoverColor;
	private Color clickedColor;
	
	private Color textColor;
	
	private Font textFont;
	
	public boolean isImageButton;
	
	public String content;
	
	public Button(Game game, int x, int y, int width, int height) {
		super(game, x, y);
		this.width = width;
		this.height = height;
		
		boundingBox = new Rectangle(x, y, width, height);
		
		initialize();
	}

	public void initialize() {
		// Set the default values for the ff.
		defaultColor = DEFAULT_BTN_COLOR;
		hoverColor = DEFAULT_HOVER_BTN_COLOR;
		clickedColor = DEFAULT_CLICKED_BTN_COLOR;
		
		textColor = DEFAULT_BTN_TEXT_COLOR;
		
		textFont = DEFAULT_BTN_FONT;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if(isImageButton) {
			
		} else {
			g.setColor(defaultColor);
			if(isHover())
				g.setColor(hoverColor);
			if(isClicked())
				g.setColor(clickedColor);
			g.fillRect(x, y, width, height);
			if(hasText()) {
				g.setFont(textFont);
				g.setColor(textColor);
				// Size of text in pixels
				int textWidthInPixels = g.getFontMetrics(textFont).stringWidth(content);
				int textHeightInPixels = g.getFontMetrics(textFont).getHeight();
				
				g.drawString(
					content,
					/* Horizontally center text */
					x + (width / 2) - (textWidthInPixels / 2), 
					/* + 16 Pixels because text renders less than 16 pixels in vertical */
					/* Vertically center text */
					(y + 16) + (height / 2) - (textHeightInPixels / 2)
				);
			}
		}
	}
	
	private boolean isHover() {
		if(boundingBox.contains(new Point(mouse.getMouseX(), mouse.getMouseY())))
			return true;
		return false;
	}
	
	public boolean isClicked() {
		if(isHover())
			if(mouse.isMouseClicked())
				return true;
		return false;
	}
	
	private boolean hasText() {
		if(content == null)
			return false;
		if(!content.isEmpty())
			return true;
		return false;
	}
	
	// Setters
	public void setColor(Color color) {
		this.defaultColor = color;
	}
	
	public void setHoverColor(Color color) {
		this.hoverColor = color;
	}
	
	public void setClickedColor(Color color) {
		this.clickedColor = color;
	}
	
	public void setTextColor(Color color) {
		this.textColor = color;
	}
	
	public void setText(String text) {
		this.content = text;
	}
	
	public void setFont(Font font) {
		this.textFont = font;
	}

}
