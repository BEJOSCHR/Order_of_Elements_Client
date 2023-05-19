package de.bejoschgaming.orderofelements.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameEvents implements WindowListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	private static int mouseX = 0, mouseY = 0;
	private static boolean showMousePos = false;
	private static boolean showMapDetails = false;
	private static boolean fillMap = true;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int turns = e.getWheelRotation();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();
		if(OOE_Main_Client.map != null) {
			OOE_Main_Client.map.updateHoverField();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();
		if(OOE_Main_Client.map != null) {
			OOE_Main_Client.map.updateHoverField();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		
		int buttonCode = e.getButton();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int buttonCode = e.getButton();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//KEY PRESS
		int keyCode = e.getKeyCode();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//KEY RELEASE
		int keyCode = e.getKeyCode();
		
		if(keyCode == KeyEvent.VK_X) {
			showMousePos = !showMousePos;
		}else if(keyCode == KeyEvent.VK_Y) {
			showMapDetails = !showMapDetails;
		}else if(keyCode == KeyEvent.VK_C) {
			fillMap = !fillMap;
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		//WINDOW CLOSING
		OOE_Main_Client.terminateProgramm(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	
	
	public static boolean isShowMousePos() {
		return showMousePos;
	}
	public static boolean isShowMapDetails() {
		return showMapDetails;
	}
	public static boolean isFillMap() {
		return fillMap;
	}
	public static int getMX() {
		return mouseX;
	}
	public static int getMY() {
		return mouseY;
	}

}
