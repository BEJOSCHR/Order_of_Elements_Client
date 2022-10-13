package de.bejoschgaming.orderofelements.graphics.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import de.bejoschgaming.orderofelements.deckbuildersystem.DeckBuilder_Data;
import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaHandler;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MultiWindow;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

	private static int mouseX = 0, mouseY = 0;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		int turns = e.getWheelRotation();
		
		MultiWindow clickedMW = null;
		for(int i = 0 ; i < MultiWindowHandler.getMws().size() ; i++) {
			MultiWindow mw = MultiWindowHandler.getMws().get(i);
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
			if(clickedMAAs.isEmpty() == false) {
				//MAAs
				for (MouseActionArea clickedMAA : clickedMAAs) {
					clickedMAA.performAction_MOUSEWHEEL_TURN(turns);
				}
			}else {
				//NO MAAs
				if(GraphicsHandler.getDrawState() == DrawState.DECKBUILDER) {
					
					
					
				}else if(GraphicsHandler.getDrawState() == DrawState.INGAME) {
					
					
					
				}
			}
		}
		
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		if(GraphicsHandler.getDrawState() == DrawState.DECKBUILDER) {
			
			if(DeckBuilder_Data.layoutMap != null) {
				DeckBuilder_Data.layoutMap.updateHoverField();
			}
			
		}else if(GraphicsHandler.getDrawState() == DrawState.INGAME) {
			
			if(MapData.map != null) {
				MapData.map.updateHoverField();
			}
			
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		if(GraphicsHandler.getDrawState() == DrawState.DECKBUILDER) {
			
			if(DeckBuilder_Data.layoutMap != null) {
				DeckBuilder_Data.layoutMap.updateHoverField();
			}
			
		}else if(GraphicsHandler.getDrawState() == DrawState.INGAME) {
			
			if(MapData.map != null) {
				MapData.map.updateHoverField();
			}
			
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
		for(int i = 0 ; i < MultiWindowHandler.getMws().size() ; i++) {
			MultiWindow mw = MultiWindowHandler.getMws().get(i);
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
			if(clickedMAAs.isEmpty() == false) {
				//MAAs
				for (MouseActionArea clickedMAA : clickedMAAs) {
					if (buttonCode == MouseEvent.BUTTON1) {
						//LEFT
						clickedMAA.performAction_LEFT_PRESS();
					} else if (buttonCode == MouseEvent.BUTTON3) {
						//RIGHT
						clickedMAA.performAction_RIGHT_PRESS();
					}
				}
			}else {
				//NO MAAs
				if(GraphicsHandler.getDrawState() == DrawState.DECKBUILDER) {
					
					if(DeckBuilder_Data.layoutMap != null) {
						if (buttonCode == MouseEvent.BUTTON1) {
							//LEFT
							DeckBuilder_Data.layoutMap.handleLeftPress();
						} else if (buttonCode == MouseEvent.BUTTON3) {
							//RIGHT
							DeckBuilder_Data.layoutMap.handleRightPress();
						}
					}
					
				}else if(GraphicsHandler.getDrawState() == DrawState.INGAME) {
					
					
					
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int buttonCode = e.getButton();
		
		if (buttonCode == MouseEvent.BUTTON1) {
			//LEFT
			MultiWindowHandler.stopDraggingMW(e.getX(), e.getY());
		}else if (buttonCode == MouseEvent.BUTTON3) {
			//RIGHT
			MultiWindowHandler.stopDraggingMW();
		}
		
		MultiWindow clickedMW = null;
		for(int i = 0 ; i < MultiWindowHandler.getMws().size() ; i++) {
			MultiWindow mw = MultiWindowHandler.getMws().get(i);
			if (mw.isHovered()) {
				clickedMW = mw;
				break;
			}
		}
		
		if(clickedMW != null) {
			//MW CLICKED
			if (buttonCode == MouseEvent.BUTTON1) {
				//LEFT
				clickedMW.performAction_LEFT_RELEASE();
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
			if(clickedMAAs.isEmpty() == false) {
				//MAAs
				for (MouseActionArea clickedMAA : clickedMAAs) {
					if (buttonCode == MouseEvent.BUTTON1) {
						//LEFT
						clickedMAA.performAction_LEFT_RELEASE();
					} else if (buttonCode == MouseEvent.BUTTON3) {
						//RIGHT
						clickedMAA.performAction_RIGHT_RELEASE();
					}
				}
			}else {
				//NO MAAs
				if(GraphicsHandler.getDrawState() == DrawState.DECKBUILDER) {
					
					if(DeckBuilder_Data.layoutMap != null) {
						if (buttonCode == MouseEvent.BUTTON1) {
							//LEFT
							DeckBuilder_Data.layoutMap.handleLeftRelease();;
						} else if (buttonCode == MouseEvent.BUTTON3) {
							//RIGHT
							DeckBuilder_Data.layoutMap.handleRightRelease();;
						}
					}
					
				}else if(GraphicsHandler.getDrawState() == DrawState.INGAME) {
					
					
					
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
