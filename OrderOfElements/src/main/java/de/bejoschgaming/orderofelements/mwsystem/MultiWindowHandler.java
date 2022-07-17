package de.bejoschgaming.orderofelements.mwsystem;

import java.awt.Graphics;
import java.util.LinkedList;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MultiWindow;

public class MultiWindowHandler {

	public static final int MW_RESIZEBORDER = 3; //In Pixel
	public static final int MW_CORNERRADIUS = 5; //In Pixel
	
	private static LinkedList<MultiWindow> mws = new LinkedList<MultiWindow>();
	
	private static MultiWindow draggedMW = null;
	private static int draggingStartX = 0, draggingStartY = 0;
	
	public static void openMW(MultiWindow mw) {
		
		if(mws.contains(mw)) {
			ConsoleHandler.printMessageInConsole("Try to add mw, that is already registerd in linkedlist! ("+mw.getType()+")", true);
		}else {
			mws.addFirst(mw);
		}
		
	}
	public static void focusMW(MultiWindow mw) {
		
		if(mws.contains(mw)) {
			mws.remove(mw);
			mws.addFirst(mw);
		}else {
			ConsoleHandler.printMessageInConsole("Try to focus mw, that is not registerd in linkedlist! ("+mw.getType()+")", true);
		}
		
	}	
	public static void closeMW(MultiWindow mw) {
		
		if(mws.contains(mw)) {
			mw.performClosingAction();
			mws.remove(mw);
		}else {
			ConsoleHandler.printMessageInConsole("Try to close mw, that is not registerd in linkedlist! ("+mw.getType()+")", true);
		}
		
	}
	
	public static void startDraggingMW(MultiWindow mw) {
		
		if(draggedMW != null) {
			ConsoleHandler.printMessageInConsole("Try to drag mw, but there is already a dragged mw! ("+mw.getType()+")", true);
		}else {
			draggedMW = mw;
			draggingStartX = MouseHandler.mouseX;
			draggingStartY = MouseHandler.mouseY;
		}
		
	}
	//WHEN WINDOW IS DEACTIVATED
	public static void stopDraggingMW() {
		
		if(draggedMW != null) {
			//WITHOUT STOP EVENT CALL
			draggedMW = null;
			draggingStartX = 0;
			draggingStartY = 0;
		}
		
	}
	//WHEN NORMAL CLICK HAPPENS
	public static void stopDraggingMW(int posX, int posY) {
		
		if(draggedMW != null) {
			MultiWindow tempMW = draggedMW;
			int tempX = draggingStartX, tempY = draggingStartY;
			draggedMW = null;
			draggingStartX = 0;
			draggingStartY = 0;
			tempMW.stopDragging(tempX, tempY, posX, posY);
		}
		
	}
	public static void drawDraggedMW(Graphics g) {
		
		if(draggedMW != null) {
			draggedMW.drawDraggingPreview(g, draggingStartX, draggingStartY);
		}
		
	}
	
	public static void drawMWs(Graphics g) {
		//FROM THE END BECAUSE LAST DRAWN IST THE LAST CLICKED (overwrites older ones)
		for(int i = mws.size()-1 ; i >= 0 ; i--) {
			mws.get(i).draw(g);
		}
	}
	
	public static LinkedList<MultiWindow> getMws() {
		return mws;
	}
	
}
