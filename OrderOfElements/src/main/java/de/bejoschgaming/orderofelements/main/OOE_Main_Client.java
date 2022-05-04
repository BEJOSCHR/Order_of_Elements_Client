package de.bejoschgaming.orderofelements.main;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaHandler;
import de.bejoschgaming.orderofelements.objects.map.Map;
import de.bejoschgaming.orderofelements.objects.map.MapData;

public class OOE_Main_Client {
	
	//BENNI KANN COMMITEN !!!
	//CHRIS KANN COMMITEN !!!
	
	public static void main(String[] args) {
		
		System.out.println("Starting Order of Elements - OOE");
		
		GraphicsHandler.initVisuals();
		MouseActionAreaHandler.initMAAs();
		
		System.out.println("FieldSize: "+MapData.FIELD_SIZE);
//		MapData.map = new Map();
		
	}

}
