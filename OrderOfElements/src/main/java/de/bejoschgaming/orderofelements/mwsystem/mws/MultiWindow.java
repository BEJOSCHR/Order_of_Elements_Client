package de.bejoschgaming.orderofelements.mwsystem.mws;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowType;

public class MultiWindow {

	protected int x, y, width, height;
	protected MultiWindowType type;
	private Color backgroundColor;
	protected boolean moveable = true, resizeable = false;
	protected List<MouseActionArea> maas = new ArrayList<MouseActionArea>();
	
	public MultiWindow(int relX, int relY, int relWidth, int relHeight, MultiWindowType type, Color backgroundColor, boolean moveable, boolean resizeable) {
		
		setFirstPos(relX, relY, relWidth, relHeight);
		this.type = type;
		this.backgroundColor = backgroundColor;
		this.initMAAs();
		this.performOpenAction();
		
	}

	private void setFirstPos(int relX, int relY, int relWidth, int relHeight) {
		
		this.x = (int) ((((double) relX / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		this.y = (int) ((((double) relY / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		this.width = (int) ((((double) relWidth / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		this.height = (int) ((((double) relHeight / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		
	}

	/**
	 * To be overwritten
	 */
	protected void initMAAs() {}
	/**
	 * DON'T overwritte!!!
	 */
	public void draw(Graphics g) {
		this.drawBackground(g);
		this.drawContent(g);
		this.drawMAAs(g);
	}
	/**
	 * DON'T overwritte normaly
	 */
	private void drawBackground(Graphics g) {
		g.setColor(this.backgroundColor);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		g.drawRect(this.x+2, this.y+2, this.width-4, this.height-4);
	}
	/**
	 * To be overwritten
	 */
	protected void drawContent(Graphics g) {}
	/**
	 * DON'T overwritte normaly
	 */
	private void drawMAAs(Graphics g) {
		for(MouseActionArea maa : this.maas) {
			maa.draw(g);
		}
	}
	
	public void updatePos(int addToX, int addToY) {
		this.x += addToX;
		this.y += addToY;
	}
	public void updateDim(int addToWidth, int addToHeight) {
		this.width += addToWidth;
		this.height += addToHeight;
	}
	
	public boolean checkArea(int mouseX, int mouseY) {
		if(mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isHovered() {
		return checkArea(MouseHandler.getMouseX(), MouseHandler.getMouseY());
	}
	
	//ALL TO BE OVERWRITTEN but the root functions have to be called as well!
	public void performOpenAction() {} //CALLED FROM CONSTRUCTOR
	public void performClosingAction() {} //CALLED FROM MW HANDLER
	public void performAction_LEFT_PRESS() { 
		this.checkForFocus();
		for(MouseActionArea maa : this.maas) {
			if(maa.isActiv() && maa.isHovered()) {
				maa.performAction_LEFT_PRESS();
			}
		}
	}
	public void performAction_RIGHT_PRESS() { 
		this.checkForFocus(); 
		for(MouseActionArea maa : this.maas) {
			if(maa.isActiv() && maa.isHovered()) {
				maa.performAction_RIGHT_PRESS();
			}
		}
	}
	public void performAction_LEFT_RELEASE() { 
		this.checkForFocus();
		for(MouseActionArea maa : this.maas) {
			if(maa.isActiv() && maa.isHovered()) {
				maa.performAction_LEFT_RELEASE();
			}
		}
	}
	public void performAction_RIGHT_RELEASE() {	
		this.checkForFocus();
		for(MouseActionArea maa : this.maas) {
			if(maa.isActiv() && maa.isHovered()) {
				maa.performAction_RIGHT_RELEASE();
			}
		}
	}
	public void performAction_MOUSEWHEEL_TURN(int turns) {
		for(MouseActionArea maa : this.maas) {
			if(maa.isActiv() && maa.isHovered()) {
				maa.performAction_MOUSEWHEEL_TURN(turns);
			}
		}
	}
	
	//ACTION ON THIS MW SO BRING INTO FOCUS IF NOT ALREADY IN FOCUS
	private void checkForFocus() {
		if(MultiWindowHandler.getMws().getFirst() != this) {
			MultiWindowHandler.focusMW(this);
		}
	}
	
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

	public MultiWindowType getType() {
		return type;
	}
	
	
	
}
