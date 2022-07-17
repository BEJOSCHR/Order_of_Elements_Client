package de.bejoschgaming.orderofelements.mwsystem.mws;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowType;

public class MultiWindow {

	protected int x = 1920/2-200, y = 1080/2-100, width = 400, height = 200;
	protected MultiWindowType type;
	protected Color backgroundColor, foregroundColor;
	protected boolean moveable = true, closeable = true;
	protected List<MouseActionArea> maas = new ArrayList<MouseActionArea>();
	
	public MultiWindow(MultiWindowType type, Color foregroundColor, Color backgroundColor, boolean moveable, boolean closeable) {
		
		this.type = type;
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
		this.moveable = moveable;
		this.closeable = closeable;
		this.initMAAs();
		this.performOpenAction();
		
	}

	protected void setFirstPos(int relX, int relY, int relWidth, int relHeight) {
		
		this.x = (int) ((((double) relX / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		this.y = (int) ((((double) relY / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		this.width = (int) ((((double) relWidth / 1920.0) * (double) GraphicsHandler.getWidth()) + 0.5);
		this.height = (int) ((((double) relHeight / 1080.0) * (double) GraphicsHandler.getHeight()) + 0.5);
		
	}

	/**
	 * To be overwritten
	 */
	protected void initMAAs() {
		if(this.closeable) {
			this.maas.add(new MouseActionArea(this.x+this.width-10-20, this.y+10, 20, 20, MouseActionAreaType.MW_Close_, "", -1, this.foregroundColor, Color.ORANGE, false, this) {
				@Override
				public void drawCustomParts(Graphics g) {
					if(this.isHovered()) {
						g.setColor(this.getHoverColor());
					}else {
						g.setColor(this.getStandardColor());
					}
					g.drawLine(this.getX(), this.getY(), this.getX()+this.getWidth(), this.getY()+this.getHeight());
					g.drawLine(this.getX(), this.getY()+this.getHeight(), this.getX()+this.getWidth(), this.getY());
				}
				@Override
				public void performAction_LEFT_RELEASE() {
					MultiWindowHandler.closeMW(this.getMW());
				}
			});
		}
	}
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
		int borderSize = MultiWindowHandler.MW_RESIZEBORDER;
		g.setColor(this.backgroundColor);
		g.fillRoundRect(this.x, this.y, this.width, this.height, MultiWindowHandler.MW_CORNERRADIUS, MultiWindowHandler.MW_CORNERRADIUS);
		g.setColor(this.foregroundColor);
		g.drawRoundRect(this.x+borderSize, this.y+borderSize, this.width-borderSize*2, this.height-borderSize*2, MultiWindowHandler.MW_CORNERRADIUS, MultiWindowHandler.MW_CORNERRADIUS);
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
	
	/**
	 * DON'T overwritte normaly
	 */
	public void drawDraggingPreview(Graphics g, int startX, int startY) {
		int xDiff = MouseHandler.mouseX-startX;
		int yDiff = MouseHandler.mouseY-startY;
		if(Math.abs(xDiff) <= 3 && Math.abs(yDiff) <= 3) { return; }
		g.setColor(new Color(100, 100, 100, 130));
		g.fillRect(this.x+xDiff, this.y+yDiff, this.width, this.height);
		g.setColor(Color.BLACK);
		g.drawRect(this.x+xDiff, this.y+yDiff, this.width, this.height);
	}
	public void startDragging() {
		MultiWindowHandler.startDraggingMW(this);
	}
	public void stopDragging(int startX, int startY, int endX, int endY) {
		int xDiff = endX-startX;
		int yDiff = endY-startY;
		if(Math.abs(xDiff) <= 3 && Math.abs(yDiff) <= 3) { return; }
		this.updatePos(xDiff, yDiff);
	}
	
	public void updatePos(int addToX, int addToY) {
		this.x += addToX;
		this.y += addToY;
		for(MouseActionArea maa : this.maas) {
			maa.updatePos(addToX, addToY);
		}
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
		boolean hitMAA = false;
		for(MouseActionArea maa : this.maas) {
			if(maa.isActiv() && maa.isHovered()) {
				maa.performAction_LEFT_PRESS();
				hitMAA = true;
			}
		}
		if(hitMAA == false && this.moveable == true) {
			//NO MAA HIT AND MOVEABLE
			this.startDragging();
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
	
	public boolean isMoveable() {
		return moveable;
	}
	
}
