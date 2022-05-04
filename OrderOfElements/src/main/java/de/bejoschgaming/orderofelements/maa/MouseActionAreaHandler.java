/*
 * MouseActionAreaHandler
 * 
 * Version 0.1
 * Autor: Chris, Benni
 * 
 * Handler-Klasse, die alle MAAs organisiert. Sie werden hier erstellt und in einer Liste
 * gespeichert.
 */
package de.bejoschgaming.orderofelements.maa;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class MouseActionAreaHandler {

	private static List<MouseActionArea> mouseActionAreas = new ArrayList<MouseActionArea>();

	/**
	 * Initialisiert alle MAAs und definiert via Overwrite die Funktionalitaet.
	 */
	public static void initMAAs() {

		// Hier entweder einzelne MAA Objekte erstellen oder entsprechende Methoden
		// jeweiliger Module aufrufen.

		//getMAAs().add(new MouseActionArea(40, 40, 20, 20, MouseActionAreaType.MAA_TEST, "TEST", 20, Color.BLACK,
		//		Color.YELLOW));

	}

	/**
	 * Gibt eine MAA aus, die dem angegebenenen Type entspricht, falls diese
	 * gefunden wurde. (Wenn richtig initialisiert, wird immer eine gefunden!). Bei
	 * doppelter type-Zuordnung wird die zuerst gefundene MAA zurueckgegeben.
	 * 
	 * @param type - Der {@link MouseActionAreaType} nach dem gesucht werden soll.
	 * @return - boolean - Gibt null aus, wenn keine MAA mit dem type gefunden
	 *         wurde, sonst die maa mit dem type.
	 */
	public static MouseActionArea getMAA(MouseActionAreaType type) {
		for (MouseActionArea maa : getMAAs()) {
			if (maa.getType() == type) {
				return maa;
			}
		}
		return null;
	}

	// GETTER

	public static List<MouseActionArea> getMAAs() {

		return mouseActionAreas;

	}

}