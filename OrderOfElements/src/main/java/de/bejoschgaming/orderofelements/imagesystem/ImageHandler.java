package de.bejoschgaming.orderofelements.imagesystem;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class ImageHandler {

	//LOADINGSCREEN
	public static Image loadingscreen_ooeLogo;
	public static Image loadingscreen_bgLogo;
	
	public static void loadPreUsedImages() {
		
		loadingscreen_bgLogo = loadImageFromName("loadingscreen/BejoschGamingLogo.png", GraphicsHandler.getWidth(), (GraphicsHandler.getHeight()/3)*2);
		
	}
	
	public static void loadImages() {
		
		
		
	}
	
	//CALLED THEN SCREEN RESOLUTION CHANGES TO RESIZE ALL IMAGES
	public static void reloadImages() {
		
		//TODO CALL EXTERNAL IMAGE FUNCTIONS TO RESIZE AND UPDATE THEM TOO
		
		loadPreUsedImages();
		loadImages();
		
	}
	
	/**
	 * Load an Image from the path
	 * @param name - String - The name of the image with ending and folder name infront of it!
	 * @param width - int - The width the image should be scaled to
	 * @param height - int - The height the image should be scaled to
	 * @return Image - The loaded Image, null - if no image found with this name!
	 */
	private static Image loadImageFromName(String name, int width, int height) { return loadImageFromName(name, width, height, true); }
	/**
	 * Load an Image from the path
	 * @param name - String - The name of the image with ending and folder name infront of it!
	 * @param width - int - The width the image should be scaled to
	 * @param height - int - The height the image should be scaled to
	 * @param errorMessage - boolean - if true it prints a error message if loading goes wrong, false it doesn't
	 * @return Image - The loaded Image, null - if no image found with this name!
	 */
	private static Image loadImageFromName(String name, int width, int height, boolean errorMessage) {
		try {
			Image img = ImageIO.read(OOE_Main_Client.class.getResourceAsStream("../imagesystem/images/"+name));
			img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return img;
		}catch(IOException | IllegalArgumentException error) {
			if(errorMessage) { 
				error.printStackTrace();
				ConsoleHandler.printMessageInConsole("The image '"+name+"' could not be loaded!", true);
			}
		}
		return null;
	}

	
}
