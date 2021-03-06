package de.bejoschgaming.orderofelements.imagesystem;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_5Friendlist;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class ImageHandler {

	//LOADINGSCREEN
	public static Image loadingscreen_ooeLogo;
	public static Image loadingscreen_bgLogo;
	
	//MENU
	public static Image menu_book[] = new Image[7];
	public static Image menu_natur[] = new Image[7];
	public static Image menu_icon_friendChallenge, menu_icon_friendSpectate, menu_icon_friendProfile, menu_icon_friendRemove;
	
	public static void loadPreUsedImages() {
		
		loadingscreen_bgLogo = loadImageFromName("loadingscreen/BejoschGamingLogo.png", GraphicsHandler.getWidth(), (GraphicsHandler.getHeight()/3)*2);
		
	}
	
	public static void loadImages() {
		
		//BOOK GIF ANIMATION
		for(int i = 0 ; i < 7 ; i++) {
			menu_book[i] = loadImageFromName("menu/book_"+i+".png", GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		}
		
		//NATUR BACKGROUND ANIMATION
		for(int i = 0 ; i < 7 ; i++) {
			menu_natur[i] = loadImageFromName("menu/natur_"+i+".png", GraphicsHandler.getWidth(), GraphicsHandler.getHeight());
		}
		menu_icon_friendChallenge = loadImageFromName("menu/MenuIcon_friendChallenge.png", Draw_5Friendlist.iconSize, Draw_5Friendlist.iconSize);
		menu_icon_friendSpectate = loadImageFromName("menu/MenuIcon_friendSpectate.png", Draw_5Friendlist.iconSize, Draw_5Friendlist.iconSize);
		menu_icon_friendProfile = loadImageFromName("menu/MenuIcon_friendProfile.png", Draw_5Friendlist.iconSize, Draw_5Friendlist.iconSize);
		menu_icon_friendRemove = loadImageFromName("menu/MenuIcon_friendRemove.png", Draw_5Friendlist.iconSize, Draw_5Friendlist.iconSize);
		
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
