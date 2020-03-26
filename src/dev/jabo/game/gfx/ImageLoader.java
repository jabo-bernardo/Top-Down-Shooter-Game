package dev.jabo.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	/**
	 * 
	 * @param path Defines the path of the image
	 * 
	 * @return An image from the path given
	 * 
	 * */
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			System.out.println("Failed to load image: " + path);
		}
		return null;
	}

}
