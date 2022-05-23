/*
 * MouseActionArea
 * 
 * Version: 0.1
 * Autor: Chris, Benni
 * 
 * Diese Klasse dient zur Erstellung von Maus-Buttons,
 * inklusive graphischer Darstellung und Funktionalitaet.
 */
package de.bejoschgaming.orderofelements.maa;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;

public class MouseActionArea {

	// Variablen
	private int x, y, width, height, relX, relY, relWidth, relHeight, textSize, relTextSize;
	private MouseActionAreaType type;
	private String displayText;
	private Color standardColor, hoverColor;
	private boolean showBox;

	/**
	 * Konstruktor fuer MouseActionArea. Erstellt ein MAA Objekt.
	 * 
	 * @param relX          - int - Startpunkt auf der x-Achse relativ zur
	 *                      Bildschirmaufloesung. Nimmt einen Wert zwischen 0 und
	 *                      100 an.
	 * @param relY          - int - Startpunkt auf der y-Achse relativ zur
	 *                      Bildschirmaufloesung. Nimmt einen Wert zwischen 0 und
	 *                      100 an.
	 * @param relWidth      - int - Breite der MAA relativ zur Bildschirmaufloesung.
	 *                      Nimmt einen Wert zwischen 0 und 100 an.
	 * @param relHeight     - int - Hoehe der MAA relativ zur Bildschirmaufloesung.
	 *                      Nimmt einen Wert zwischen 0 und 100 an.
	 * @param type          - MouseActionAreaType - Zuordnung des MAA-Typen dieses
	 *                      Objekts.
	 * @param displayText   - String - Text, welcher in dem MAA angezeigt werden
	 *                      soll.
	 * @param relTextSize   - int - Schriftgroesse des Textes relativ zur
	 *                      Bildschirmaufloesung.
	 * @param standardColor - Color - Farbe der MAA, welche standardmaessig zu sehen
	 *                      ist.
	 * @param hoverColor    - Color - Farbe der MAA, welche zu sehen ist, wenn die
	 *                      Maus ueber das MAA schwebt.
	 * @param showBox       - Boolean - Bestimmt, ob das Standard-Rechteck der MAA
	 *                      angezeigt werden soll.
	 */
	public MouseActionArea(int relX, int relY, int relWidth, int relHeight, MouseActionAreaType type,
			String displayText, int relTextSize, Color standardColor, Color hoverColor, boolean showBox) {

		if (relX < 0)
			this.relX = 0;
		else if (relX > 100)
			this.relX = 100;
		else
			this.relX = relX;

		if (relY < 0)
			this.relY = 0;
		else if (relY > 100)
			this.relY = 100;
		else
			this.relY = relY;

		if (relWidth < 0)
			this.relWidth = 0;
		else if (relWidth > 100)
			this.relWidth = 100;
		else
			this.relWidth = relWidth;

		if (relHeight < 0)
			this.relHeight = 0;
		else if (relHeight > 100)
			this.relHeight = 100;
		else
			this.relHeight = relHeight;

		this.type = type;
		this.displayText = displayText;
		this.relTextSize = relTextSize;
		this.standardColor = standardColor;
		this.hoverColor = hoverColor;
		this.showBox = showBox;

		this.refreshPosition();

		MouseActionAreaHandler.getMAAs().add(this);

		// SICHERSTELLEN, DASS KEINE NEBENLAEUFIGE FEHLER DAS HINZUFUEGEN VERHINDERN
		while (!MouseActionAreaHandler.getMAAs().contains(this)) {
			try {
				MouseActionAreaHandler.getMAAs().add(this);
			} catch (ConcurrentModificationException error) {
			}
		}
	}

	/**
	 * Aktualisiert die Position des MAA mit Hilfe der Daten zur relativen Position
	 * und der Bildschirmaufloesung.
	 */
	public void refreshPosition() {
		x = (int) ((((double) relX / 100.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		y = (int) ((((double) relY / 100.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		width = (int) ((((double) relWidth / 100.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		height = (int) ((((double) relHeight / 100.0) * (double) GraphicsHandler.getHeight()) + 0.5);

		textSize = (int) (((double) relTextSize / 1080.0) * (double) GraphicsHandler.getHeight() + 0.5);
	}

	/**
	 * Ueberprueft, ob sich bestimmte Koordinaten in der Area dieser MAA befinden.
	 * 
	 * @param mouseX - int - X-Koordinate der Mausposition.
	 * @param mouseY - int - Y-Koordinate der Mausposition.
	 * @return - boolean - Gibt true aus, wenn das MAA aktiv und sich die
	 *         Koordinaten in der Area befindet.
	 **/
	public boolean checkArea(int mouseX, int mouseY) {

		if (isActiv() == true) {
			if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Ueberprueft, ob die Maus ueber diesem MAA schwebt.
	 * 
	 * @return - boolean - Ausgabe von checkArea() mit den Mauskoordinaten als
	 *         Input.
	 **/
	public boolean isHovered() {
		return checkArea(MouseHandler.getMouseX(), MouseHandler.getMouseY());
	}

	/**
	 * Ueberprüft ob diese MAA gerade aktiv ist. (Soll ueberschrieben/veraendert
	 * werden, wenn eine neue MAA erzeugt wird. (@Overwrite))
	 * 
	 * @return - boolean - Gibt true aus, wenn die Aktivierungsbedingung erfüllt
	 *         ist. (Wenn nicht ueberschrieben/veraendert, dann immer true.)
	 **/
	public boolean isActiv() {
		return true;
	}

	/**
	 * Wird aufgerufen wenn diese MAA mit der LINKEN Maustaste GEDRUECKT wird - LEFT
	 * PRESS (Soll ueberschrieben/veraendert werden, wenn eine neue MAA erzeugt
	 * wird. (@Overwrite))
	 **/
	public void performAction_LEFT_PRESS() {
	};

	/**
	 * Wird aufgerufen wenn diese MAA mit der RECHTEN Maustaste GEDRUECKT wird -
	 * RIGHT PRESS (Soll ueberschrieben/veraendert werden, wenn eine neue MAA
	 * erzeugt wird. (@Overwrite))
	 **/
	public void performAction_RIGHT_PRESS() {
	}

	/**
	 * Wird aufgerufen wenn diese MAA mit der LINKEN Maustaste LOSGELASSEN wird -
	 * LEFT RELEASE (Soll ueberschrieben/veraendert werden, wenn eine neue MAA
	 * erzeugt wird. (@Overwrite))
	 **/
	public void performAction_LEFT_RELEASE() {
	}

	/**
	 * Wird aufgerufen wenn diese MAA mit der RECHTEN Maustaste LOSGELASSEN wird -
	 * RIGHT RELEASE (Soll ueberschrieben/veraendert werden, wenn eine neue MAA
	 * erzeugt wird. (@Overwrite))
	 **/
	public void performAction_RIGHT_RELEASE() {
	}

	/**
	 * Der graphische Darstellungsteil jeder MAA. Wird nur aufgerufen, wenn die MAA
	 * aktiv ist. Standardmaeßig wird der Rand der MAA in der jeweiligen Farbe
	 * (abhaengig ob gehovered oder nicht) mit dem dispplayText dargestellt. Custom
	 * Veraenderungen koennen in der drawCustomParts Methode
	 * ueberschrieben/veraendert (@Overwrite) werden.
	 * 
	 * @param g - {@link Graphics} - Der Grafik-Parameter
	 **/
	public void draw(Graphics g) {

		if (isHovered()) {
			g.setColor(hoverColor);
			if (showBox)
				g.drawRect(x, y, width, height);
			GraphicsHandler.drawCentralisedText(g, hoverColor, textSize, displayText, x + width / 2, y + height / 2);
		} else {
			g.setColor(standardColor);
			if (showBox)
				g.drawRect(x, y, width, height);
			GraphicsHandler.drawCentralisedText(g, standardColor, textSize, displayText, x + width / 2, y + height / 2);
		}
		drawCustomParts(g);
	}

	/**
	 * Hie koennen zusaetzlich gewuenschte Grafikausgaben hinzugefuegt werden. (Soll
	 * ueberschrieben/veraendert werden, wenn eine neue MAA erzeugt wird.
	 * (@Overwrite))
	 * 
	 * @param g - {@link Graphics} - Der Grafik-Parameter
	 */
	public void drawCustomParts(Graphics g) {
	}

	/**
	 * Loescht diese MAA.
	 **/
	public void remove() {

		MouseActionAreaHandler.getMAAs().remove(this);

		// SICHERSTELLEN, DASS KEINE NEBENLAEUFIGE FEHLER DAS LOESCHEN VERHINDERN
		while (MouseActionAreaHandler.getMAAs().contains(this)) {
			try {
				MouseActionAreaHandler.getMAAs().remove(this);
			} catch (ConcurrentModificationException error) {
			}
		}
	}

	// SETTER

	public void setStandardColor(Color c) {
		standardColor = c;
	}

	// GETTER

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRelX() {
		return relX;
	}

	public int getRelY() {
		return relY;
	}

	public int getRelWidth() {
		return relWidth;
	}

	public int getRelHeight() {
		return relHeight;
	}

	public int getTextSize() {
		return textSize;
	}

	public int getRelTextSize() {
		return relTextSize;
	}

	public MouseActionAreaType getType() {
		return type;
	}

	public String getDisplayText() {
		return displayText;
	}

	public Color getStandardColor() {
		return standardColor;
	}

	public Color getHoverColor() {
		return hoverColor;
	}
}
