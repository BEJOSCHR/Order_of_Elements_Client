package de.bejoschgaming.orderofelements.graphics.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MultiWindow;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

	public static int mouseX = 0, mouseY = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int turns = e.getWheelRotation();
		
		MultiWindow clickedMW = null;
		for (MultiWindow mw : MultiWindowHandler.getMws()) {
			if (mw.isHovered()) {
				clickedMW = mw;
				break;
			}
		}
		
		if(clickedMW != null) {
			//MW CLICKED
			clickedMW.performAction_MOUSEWHEEL_TURN(turns);
		}else {
			//NO MW
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				clickedMAA.performAction_MOUSEWHEEL_TURN(turns);
			}
		}
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		if (MapData.map != null) {
			MapData.map.updateHoverField();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		if (MapData.map != null) {
			MapData.map.updateHoverField();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int buttonCode = e.getButton();
		
		MultiWindow clickedMW = null;
		for (MultiWindow mw : MultiWindowHandler.getMws()) {
			if (mw.isHovered()) {
				clickedMW = mw;
				break;
			}
		}
		
		if(clickedMW != null) {
			//MW CLICKED
			if (buttonCode == MouseEvent.BUTTON1) {
				//LEFT
				clickedMW.performAction_LEFT_PRESS();
			} else if (buttonCode == MouseEvent.BUTTON3) {
				//RIGHT
				clickedMW.performAction_RIGHT_PRESS();
			}
		}else {
			//NO MW
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				if (buttonCode == MouseEvent.BUTTON1) {
					//LEFT
					clickedMAA.performAction_LEFT_PRESS();
				} else if (buttonCode == MouseEvent.BUTTON3) {
					//RIGHT
					clickedMAA.performAction_RIGHT_PRESS();
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int buttonCode = e.getButton();
		
		MultiWindow clickedMW = null;
		for (MultiWindow mw : MultiWindowHandler.getMws()) {
			if (mw.isHovered()) {
				clickedMW = mw;
				break;
			}
		}
		
		if(clickedMW != null) {
			//MW CLICKED
			if (buttonCode == MouseEvent.BUTTON1) {
				//LEFT
				clickedMW.performAction_LEFT_PRESS();
			} else if (buttonCode == MouseEvent.BUTTON3) {
				//RIGHT
				clickedMW.performAction_RIGHT_RELEASE();
			}
		}else {
			//NO MW
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				if (buttonCode == MouseEvent.BUTTON1) {
					//LEFT
					clickedMAA.performAction_LEFT_RELEASE();
				} else if (buttonCode == MouseEvent.BUTTON3) {
					//RIGHT
					clickedMAA.performAction_RIGHT_RELEASE();
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static int getMouseX() {
		return mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

}
