package de.bejoschgaming.orderofelements.deckbuildersystem;

import java.awt.Point;

import de.bejoschgaming.orderofelements.decksystem.Deck;
import de.bejoschgaming.orderofelements.decksystem.DeckbuilderType;

public class DeckBuilder_Data {

	public static final String bigSplit = ":", smallSplit = "-";
	
	public static int displayFieldSize = 30;
	public static int map_offset_X = 0, map_offset_Y = 0;
	
	public static DeckbuilderType displayType = DeckbuilderType.OVERVIEW;
	public static Deck selectedDeck = null;
	
	public static DeckBuilder_Map layoutMap = null;
	public static Point startDragPoint = null;
	
	//NOT USED YET:
	public static boolean showFieldCords = false;
	public static boolean showFieldCenterPoints = false;
	public static int deckListScroll = 0;

}
