package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;
import de.bejoschgaming.orderofelements.profilesystem.ClientData;

public class Draw_5Friendlist {

	public static void initMAAs() {
		
		new MouseActionArea(540, 448, 150, 50, MouseActionAreaType.FRIENDLIST_Back, "Back", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				TextFieldHandler.hideTextField(TextFieldHandler.FRIENDLIST_RequestName);
				AnimationHandler.startAnimation(new MenuBookAnimation(false) {
					@Override
					protected void halfTimeAction() {
						GraphicsHandler.switchTo(DrawState.MENU);
					}
				});
			}
		};
		new MouseActionArea(365, 800, 300, 50, MouseActionAreaType.FRIENDLIST_RequestDisplay, "", 22, Color.BLACK, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST && ClientData.getFriendRequests().isEmpty() == false;
			}
			@Override
			public void drawCustomParts(Graphics g) {
				
				int firstID = ClientData.getFriendRequests().keySet().toArray(new Integer[0])[0];
				String referedName = ClientData.getFriendRequests().get(firstID);
				g.setFont(FontHandler.getFont(FontHandler.medievalSharp_regular, this.getTextSize()));
				g.drawString("1/"+ClientData.getFriendRequests().size()+"   "+referedName, this.getX()+8, this.getY()+this.getHeight()-15);
				
			}
		};
		new MouseActionArea(760, 745, 125, 40, MouseActionAreaType.FRIENDLIST_RequestSend, "Send", 22, Color.BLACK, null, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST;
			}
			@Override
			public void draw(Graphics g) {
				String inputName = TextFieldHandler.FRIENDLIST_RequestName.getText().trim();
				if(this.isHovered()) {
					if(this.validInput(inputName)) {
						this.setHoverColor(Color.GREEN.darker());
					}else {
						this.setHoverColor(Color.RED);
					}
				}
				super.draw(g);
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//SEND FRIENDREQUEST
				String inputName = TextFieldHandler.FRIENDLIST_RequestName.getText().trim();
				if(this.validInput(inputName)) {
					ServerConnection.sendPacket(241, ""+inputName);
					TextFieldHandler.FRIENDLIST_RequestName.setText("");
				}
			}
			private boolean validInput(String input) {
				//IS STH THERE, VALID FOR SENDING AND DB, NOT THIS USERSNAME ;)
				if(input.length() > 0 && Draw_2Login.checkForValidUsername(input) == null) {
					return true;
				}else {
					return false;
				}
			}
		};
		new MouseActionArea(675, 800, 100, 50, MouseActionAreaType.FRIENDLIST_RequestAccept, "Accept", 22, Color.BLACK, Color.GREEN.darker(), true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST && ClientData.getFriendRequests().isEmpty() == false;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//ACCEPT FRIEND
				int firstID = ClientData.getFriendRequests().keySet().toArray(new Integer[0])[0];
				ServerConnection.sendPacket(242, ""+firstID);
				ClientData.removeFriendRequest(firstID);
			}
		};
		new MouseActionArea(785, 800, 100, 50, MouseActionAreaType.FRIENDLIST_RequestDecline, "Decline", 22, Color.BLACK, Color.RED, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST && ClientData.getFriendRequests().isEmpty() == false;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//DECLINE FRIEND
				int firstID = ClientData.getFriendRequests().keySet().toArray(new Integer[0])[0];
				ServerConnection.sendPacket(243, ""+firstID);
				ClientData.removeFriendRequest(firstID);
			}
		};
		
	}
	
	public static void draw(Graphics g) {
		
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
		//DRAW LEFT PAGE
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Friendlist", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*30)/100);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You have "+ClientData.getOnlineFriendList().size()+"/"+(ClientData.getOnlineFriendList().size()+ClientData.getOfflineFriendList().size())+" friends online", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*37)/100);
		
		int y = (GraphicsHandler.getHeight()*66)/100;
		g.setColor(Color.BLACK);
		g.drawLine((GraphicsHandler.getWidth()*17)/100, y, (GraphicsHandler.getWidth()*26)/100, y);
		g.drawLine((GraphicsHandler.getWidth()*38)/100, y, (GraphicsHandler.getWidth()*48)/100, y);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 25), "Friendrequests", (GraphicsHandler.getWidth()*32)/100, y);
		if(ClientData.getFriendRequests().isEmpty()) {
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 22), "You have no friendrequests!", (GraphicsHandler.getWidth()*33)/100, (GraphicsHandler.getHeight()*77)/100);
		}
		
		//DRAW RIGHT PAGE
		
		
	}
	
}
