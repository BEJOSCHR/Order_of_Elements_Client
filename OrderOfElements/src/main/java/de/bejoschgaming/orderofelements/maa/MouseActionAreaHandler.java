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

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class MouseActionAreaHandler {

	private static List<MouseActionArea> mouseActionAreas = new ArrayList<MouseActionArea>();

	/**
	 * Initialisiert alle MAAs und definiert via Overwrite die Funktionalitaet.
	 */
	public static void initMAAs() {

		// Hier entweder einzelne MAA Objekte erstellen oder entsprechende Methoden
		// jeweiliger Module aufrufen.

		MouseActionArea button1 = new MouseActionArea(20, 20, 10, 10, MouseActionAreaType.MAA_TEST, "1080p", 30,
				Color.BLACK, Color.RED) {
			@Override
			public void performAction_LEFT_RELEASE() {
				GraphicsHandler.width = 1920;
				GraphicsHandler.height = 1080;
				GraphicsHandler.frame.setSize(GraphicsHandler.width, GraphicsHandler.height);
				MouseActionAreaHandler.refreshAllPositions();
				GraphicsHandler.label.repaint();
			}
		};
		MouseActionArea button2 = new MouseActionArea(60, 20, 10, 10, MouseActionAreaType.MAA_TEST, "720p", 30,
				Color.BLACK, Color.RED) {
			@Override
			public void performAction_LEFT_RELEASE() {
				GraphicsHandler.width = 1280;
				GraphicsHandler.height = 720;
				GraphicsHandler.frame.setSize(GraphicsHandler.width, GraphicsHandler.height);
				MouseActionAreaHandler.refreshAllPositions();
				GraphicsHandler.label.repaint();
			}
		};
		mouseActionAreas.add(button1);
		mouseActionAreas.add(button2);
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
