/*
 * MouseActionArea
 * 
 * Version: 0.1
 * Autor: Chris, Benni
 * 
 * Diese Klasse dient zur Erstellung von Maus-Buttons,
 * inklusive graphischer Darstellung und Funktionalitaet.
 */
package de.bejoschgaming.orderofelements.maasystem;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;

import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MultiWindow;

public class MouseActionArea {

	// Variablen
	private int x, y, width, height, relX, relY, relWidth, relHeight, textSize, relTextSize;
	private MouseActionAreaType type;
	private String displayText;
	private Color standardColor, hoverColor;
	private boolean showBox, overlapAnimations = false, mwMAA;
	private MultiWindow mw;

	/**
	 * Normal MAA Constructor
	 */
	public MouseActionArea(int relX, int relY, int relWidth, int relHeight, MouseActionAreaType type,
			String displayText, int relTextSize, Color standardColor, Color hoverColor, boolean showBox, boolean overlapAnimations) {

		this.mwMAA = false;
		
		this.relX = relX;
		this.relY = relY;
		this.relWidth = relWidth;
		this.relHeight = relHeight;
		this.type = type;
		this.displayText = displayText;
		this.relTextSize = relTextSize;
		this.standardColor = standardColor;
		this.hoverColor = hoverColor;
		this.showBox = showBox;
		this.overlapAnimations = overlapAnimations;
		
		this.mw = null;
		
		this.refreshPosition();

		// SICHERSTELLEN, DASS KEINE NEBENLAEUFIGE FEHLER DAS HINZUFUEGEN VERHINDERN
		while (!MouseActionAreaHandler.getMAAs().contains(this)) {
			try {
				MouseActionAreaHandler.getMAAs().add(this);
			} catch (ConcurrentModificationException error) {}
		}
		
	}
	/**
	 * MW MAA Constructor
	 */
	public MouseActionArea(int relWidth, int relHeight, MouseActionAreaType type,
			String displayText, int relTextSize, Color standardColor, Color hoverColor, boolean showBox) {

		this.mwMAA = true;
		
		this.relX = -1;
		this.relY = -1;
		this.relWidth = relWidth;
		this.relHeight = relHeight;
		this.type = type;
		this.displayText = displayText;
		this.relTextSize = relTextSize;
		this.standardColor = standardColor;
		this.hoverColor = hoverColor;
		this.showBox = showBox;
		
		this.refreshPosition();

		//DONT BE ADDED TO LIST, IS IN MW LIST
		
	}

	/**
	 * Aktualisiert die Position des MAA mit Hilfe der Daten zur relativen Position
	 * und der Bildschirmaufloesung
	 */
	public void refreshPosition() {
		
		x = (int) ((((double) relX / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		y = (int) ((((double) relY / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		width = (int) ((((double) relWidth / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		height = (int) ((((double) relHeight / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
	
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
		
		if(MultiWindowHandler.isMWBlocking() == true) {
			if(this.mwMAA == false) {
				//MW IS BLOCKING AND THIS IS NOT A MW MAA
				return false;
			}else {
				//BLOCKING BUT THIS IS A MW MAA
				if(this.mw != null) {
					//MW IS SET
					if(this.mw.isBlocking() == false) {
						//THIS MW MAA IS NOT ON A BLOCKING MW
						return false;
					}
				}
			}
		}
		
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
	 * Wird aufgerufen wenn innerhalb dieser MAA das Mausrad gedreht wird
	 * (@Overwrite))
	 **/
	public void performAction_MOUSEWHEEL_TURN(int turns) {
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
			GraphicsHandler.drawCentralisedText(g, hoverColor, FontHandler.getFont(FontHandler.medievalSharp_regular, textSize), displayText, x + width / 2, y + height / 2);
		} else {
			g.setColor(standardColor);
			if (showBox)
				g.drawRect(x, y, width, height);
			GraphicsHandler.drawCentralisedText(g, standardColor, FontHandler.getFont(FontHandler.medievalSharp_regular, textSize), displayText, x + width / 2, y + height / 2);
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

		// SICHERSTELLEN, DASS KEINE NEBENLAEUFIGE FEHLER DAS LOESCHEN VERHINDERN
		while (MouseActionAreaHandler.getMAAs().contains(this)) {
			try {
				MouseActionAreaHandler.getMAAs().remove(this);
			} catch (ConcurrentModificationException error) {}
		}
	}

	// SETTER

	public void setStandardColor(Color c) {
		standardColor = c;
	}
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}

	// MW SETTER / UPDATER
	
//	/**
//	 * Only useable if mw maa
//	 */
//	public void setDim(int width, int height) {
//		
//		if(this.mwMAA == false) { return; }
//		
//		this.width = width;
//		this.height = height;
//		
//	}
	/**
	 * Only useable if mw maa
	 */
	public void setMW(MultiWindow mw) {
		
		if(this.mwMAA == false) { return; }
		
		this.mw = mw;
		
	}
	/**
	 * Only useable if mw maa
	 */
	public void setPos(int x, int y) {
		
		if(this.mwMAA == false) { return; }
		
		this.x = x;
		this.y = y;
		
	}
	/**
	 * Only useable if mw maa
	 */
	public void updatePos(int xAdd, int yAdd) {
		
		if(this.mwMAA == false) { return; }
		
		this.x += xAdd;
		this.y += yAdd;
		
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
	
	public MultiWindow getMW() {
		return mw;
	}
	
	public boolean isShowBox() {
		return showBox;
	}
	
	public boolean isOverlappingAnimations() {
		return overlapAnimations;
	}
	public boolean isMwMAA() {
		return mwMAA;
	}
}
