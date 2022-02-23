package de.bejoschgaming.orderofelements.graphics.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
		
		if(MapData.map != null) {
			MapData.map.updateHoverField();
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(MapData.map != null) {
			MapData.map.updateHoverField();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
