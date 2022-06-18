package de.bejoschgaming.orderofelements.fontsystem;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class FontHandler {

	public static Font bridgnorth_regular;
	public static Font bridgnorth_bold;
	public static Font medievalSharp_regular;
	
	public static void loadFonts() {
		
		bridgnorth_regular = loadCustomFontFromName("Bridgnorth-Regular.ttf");
		bridgnorth_bold = loadCustomFontFromName("Bridgnorth-Bold.ttf");
		medievalSharp_regular = loadCustomFontFromName("MedievalSharp-Regular.ttf");
		
	}

	/**
	 * WITH .ttf in the name!
	 * @param the name of the file, but with the ending!
	 * @return the loaded found if found, else null is returned
	 */
	private static Font loadCustomFontFromName(String name) {
		
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, OOE_Main_Client.class.getResourceAsStream("../fontsystem/fonts/"+name));
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(customFont);
			return customFont;
		} catch (FontFormatException | IOException error) {
			error.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Automaticaly scales relative to screen resolution, the size is always relativ to the default size specified in the GraphicsHandler function getRelativTextSize 
	 * @param font - has to be registered while loading it
	 * @param size - relativ to the default size specified in the GraphicsHandler function getRelativTextSize
	 * @return The font scaled to the given size
	 */
	public static Font getFont(Font font, int size) {
		
		return font.deriveFont(GraphicsHandler.getRelativTextSize(size)*1.0F);
		
	}
	
}
