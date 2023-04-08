package de.bejoschgaming.orderofelements.imagesystem;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_5Friendlist;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class ImageHandler {

	public static int iconSize = (GraphicsHandler.getWidth()/100)*15;
	
	//LOADINGSCREEN
	public static Image loadingscreen_bgLogo;
	
	//MENU
	public static Image menu_book[] = new Image[7];
	public static Image menu_natur[] = new Image[7];
	public static Image menu_icon_friendChallenge, menu_icon_friendSpectate, menu_icon_friendProfile, menu_icon_friendRemove;
	public static Image menu_ooeIcon_clean_quader, menu_ooeIcon_clean_round, menu_ooeIcon_clean_transparent;
	public static Image menu_ooeIcon_withText_quader, menu_ooeIcon_withText_round, menu_ooeIcon_withText_transparent;
	
	public static void loadPreUsedImages() {
		
		loadingscreen_bgLogo = loadImageFromName("loadingscreen/BejoschGamingLogo.png", GraphicsHandler.getWidth(), (GraphicsHandler.getHeight()/3)*2);
		
	}
	
	public static void loadImages() {
		
		menu_ooeIcon_clean_quader = loadImageFromName("menu/Order_of_Elements_1.png", iconSize, iconSize);
		menu_ooeIcon_clean_round = loadImageFromName("menu/Order_of_Elements_2.png", iconSize, iconSize);
		menu_ooeIcon_clean_transparent = loadImageFromName("menu/Order_of_Elements_3.png", iconSize, iconSize);
		menu_ooeIcon_withText_quader = loadImageFromName("menu/Order_of_Elements_4.png", iconSize, iconSize);
		menu_ooeIcon_withText_round = loadImageFromName("menu/Order_of_Elements_5.png", iconSize, iconSize);
		menu_ooeIcon_withText_transparent = loadImageFromName("menu/Order_of_Elements_6.png", iconSize, iconSize);
		
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
		
		//TODO CALL HERE EXTERNAL IMAGE FUNCTIONS TO RESIZE AND UPDATE THEM TOO
		//UNIT TEMPLATES
		for(Unit unit : UnitHandler.getUnitTemplates()) {
			unit.reloadImage();
		}
		//CREATED UNITS
		for(Unit unit : UnitHandler.getCreatedUnits()) {
			unit.reloadImage();
		}
		
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
	public static Image loadImageFromName(String name, int width, int height) { return loadImageFromName(name, width, height, true); }
	/**
	 * Load an Image from the path
	 * @param name - String - The name of the image with ending and folder name infront of it!
	 * @param width - int - The width the image should be scaled to
	 * @param height - int - The height the image should be scaled to
	 * @param errorMessage - boolean - if true it prints a error message if loading goes wrong, false it doesn't
	 * @return Image - The loaded Image, null - if no image found with this name!
	 */
	public static Image loadImageFromName(String name, int width, int height, boolean errorMessage) {
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
