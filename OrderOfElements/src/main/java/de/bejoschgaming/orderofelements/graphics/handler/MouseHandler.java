package de.bejoschgaming.orderofelements.graphics.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaHandler;
import de.bejoschgaming.orderofelements.objects.map.MapData;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

	public static int mouseX = 0, mouseY = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub

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
		if (buttonCode == MouseEvent.BUTTON1) {
			// LEFT
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				clickedMAA.performAction_LEFT_PRESS();
			}
		} else if (buttonCode == MouseEvent.BUTTON3) {
			// RIGHT
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				clickedMAA.performAction_RIGHT_PRESS();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int buttonCode = e.getButton();
		if (buttonCode == MouseEvent.BUTTON1) {
			// LEFT
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				clickedMAA.performAction_LEFT_RELEASE();
			}
		} else if (buttonCode == MouseEvent.BUTTON3) {
			// RIGHT
			List<MouseActionArea> clickedMAAs = new ArrayList<MouseActionArea>();
			for (MouseActionArea maa : MouseActionAreaHandler.getMAAs()) {
				if (maa.isActiv() && maa.isHovered()) {
					clickedMAAs.add(maa);
				}
			}
			for (MouseActionArea clickedMAA : clickedMAAs) {
				clickedMAA.performAction_RIGHT_RELEASE();
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
