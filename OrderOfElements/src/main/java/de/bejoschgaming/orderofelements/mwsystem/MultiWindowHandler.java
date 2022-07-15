package de.bejoschgaming.orderofelements.mwsystem;

import java.awt.Graphics;
import java.util.LinkedList;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MultiWindow;

public class MultiWindowHandler {

	private static LinkedList<MultiWindow> mws = new LinkedList<MultiWindow>();
	
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
	
	public static void drawMWs(Graphics g) {
		for(int i = 0 ; i < mws.size() ; i++) {
			mws.get(i).draw(g);
		}
	}
	
	public static LinkedList<MultiWindow> getMws() {
		return mws;
	}
	
}
