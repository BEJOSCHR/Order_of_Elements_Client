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

import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_1Loadingscreen;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_2Login;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_3Menu;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_5Friendlist;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_6Matchhistory;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_7Credits;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_8Ingame;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_9Aftergame;

public class MouseActionAreaHandler {

	private static List<MouseActionArea> mouseActionAreas = new ArrayList<MouseActionArea>();

	/**
	 * Initialisiert alle MAAs und definiert via Overwrite die Funktionalitaet.
	 */
	public static void initMAAs() {

		// Hier entweder einzelne MAA Objekte erstellen oder entsprechende Methoden
		// jeweiliger Module aufrufen.

		Draw_1Loadingscreen.initMAAs();
		Draw_2Login.initMAAs();
		Draw_3Menu.initMAAs();
		Draw_4Deckbuilder.initMAAs();
		Draw_5Friendlist.initMAAs();
		Draw_6Matchhistory.initMAAs();
		Draw_7Credits.initMAAs();
		Draw_8Ingame.initMAAs();
		Draw_9Aftergame.initMAAs();
		
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

	/**
	 * Aktualisiert die Position aller MAAs, die sich in der Liste mouseActionAreas
	 * befinden, mit Hilfe der Daten zur relativen Position und der
	 * Bildschirmaufloesung.
	 */
	public static void refreshAllPositions() {

		for (MouseActionArea i : mouseActionAreas) {
			i.refreshPosition();
		}
	}

	// GETTER

	public static List<MouseActionArea> getMAAs() {

		return mouseActionAreas;

	}

}
