package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.profilesystem.ClientData;
import de.bejoschgaming.orderofelements.profilesystem.LoadedProfile;
import de.bejoschgaming.orderofelements.profilesystem.ProfileHandler;

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
		
		//CREATE BOX MAAs - use textsize as pos
		for(int i = 0 ; i < totalAmountOfBoxes ; i++) {
			new MouseActionArea(startX, startY+(heightPerBox*i), widthPerBox, heightPerBox-1, MouseActionAreaType.FRIENDLIST_DisplayBox_, "", i, Color.DARK_GRAY, Color.DARK_GRAY, true, false) {
				@Override
				public boolean isActiv() {
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					if(friendID == -1) { return false; }
					return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST;
				}
				@Override
				public void drawCustomParts(Graphics g) {
					
					LoadedProfile displayFriend = ProfileHandler.getProfile(Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize()));
					String displayedName = displayFriend.getName();
					String displayedStatus = displayFriend.getStatus();
					
					int textSize = (int) (((double) Draw_5Friendlist.textSize / 1080.0) * (double) GraphicsHandler.getHeight() + 0.5);
					Font fontName = FontHandler.getFont(FontHandler.medievalSharp_regular, textSize+2);
					GraphicsHandler.drawCentralisedText(g, Color.BLACK, fontName, (this.getRelTextSize()+scrollValue+1)+".", this.getX()+(this.getWidth()*3)/100, this.getY()+(this.getHeight()/2));
					Font fontStatus = FontHandler.getFont(FontHandler.medievalSharp_regular, textSize);
					int nameWidth = g.getFontMetrics(fontName).stringWidth(displayedName);
					int statusWidth = g.getFontMetrics(fontStatus).stringWidth(displayedStatus);
					GraphicsHandler.drawCentralisedText(g, Color.BLACK, fontName, displayedName, this.getX()+(nameWidth/2)+(this.getWidth()*10)/100, this.getY()+(this.getHeight()/2));
					GraphicsHandler.drawCentralisedText(g, Draw_5Friendlist.getStatusColor(displayedStatus), fontStatus, displayedStatus, this.getX()+(this.getWidth()*40)/100+(statusWidth/2), this.getY()+(this.getHeight()/2));
					
				}
			};
			new MouseActionArea(startX+widthPerBox-3*(iconSize+(widthPerBox*3)/100), startY+(heightPerBox*i)+(heightPerBox-iconSize)/2, iconSize+2, iconSize+2, MouseActionAreaType.FRIENDLIST_Challenge_, "", i, Color.DARK_GRAY, Color.WHITE, true, false) {
				@Override
				public boolean isActiv() {
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					if(friendID == -1) { return false; }
					LoadedProfile friendProfile = ProfileHandler.getProfile(friendID);
					//NICHT INGAME UND NICHT OFFLINE
					return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST && friendProfile.getStatus().contains("InGame") == false && friendProfile.getStatus().equalsIgnoreCase("Offline") == false;
				}
				@Override
				public void drawCustomParts(Graphics g) {
					
					g.drawImage(ImageHandler.menu_icon_friendChallenge, this.getX()+1, this.getY()+1, null);
					
				}
				@Override
				public void performAction_LEFT_RELEASE() {
					
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					//TODO
					
				}
			};
			new MouseActionArea(startX+widthPerBox-3*(iconSize+(widthPerBox*3)/100), startY+(heightPerBox*i)+(heightPerBox-iconSize)/2, iconSize+2, iconSize+2, MouseActionAreaType.FRIENDLIST_Spectate_, "", i, Color.DARK_GRAY, Color.WHITE, true, false) {
				@Override
				public boolean isActiv() {
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					if(friendID == -1) { return false; }
					LoadedProfile friendProfile = ProfileHandler.getProfile(friendID);
					//INGAME
					return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST && friendProfile.getStatus().contains("InGame") == true;
				}
				@Override
				public void drawCustomParts(Graphics g) {
					
					g.drawImage(ImageHandler.menu_icon_friendSpectate, this.getX()+1, this.getY()+1, null);
					
				}
				@Override
				public void performAction_LEFT_RELEASE() {
					
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					//TODO
					
				}
			};
			new MouseActionArea(startX+widthPerBox-2*(iconSize+(widthPerBox*3)/100), startY+(heightPerBox*i)+(heightPerBox-iconSize)/2, iconSize+2, iconSize+2, MouseActionAreaType.FRIENDLIST_Profile_, "", i, Color.DARK_GRAY, Color.WHITE, true, false) {
				@Override
				public boolean isActiv() {
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					if(friendID == -1) { return false; }
					return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST;
				}
				@Override
				public void drawCustomParts(Graphics g) {
					
					g.drawImage(ImageHandler.menu_icon_friendProfile, this.getX()+1, this.getY()+1, null);
					
				}
				@Override
				public void performAction_LEFT_RELEASE() {
					
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					//TODO
					
				}
			};
			new MouseActionArea(startX+widthPerBox-1*(iconSize+(widthPerBox*3)/100), startY+(heightPerBox*i)+(heightPerBox-iconSize)/2, iconSize+2, iconSize+2, MouseActionAreaType.FRIENDLIST_Remove_, "", i, Color.DARK_GRAY, Color.WHITE, true, false) {
				@Override
				public boolean isActiv() {
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					if(friendID == -1) { return false; }
					return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST;
				}
				@Override
				public void drawCustomParts(Graphics g) {
					
					g.drawImage(ImageHandler.menu_icon_friendRemove, this.getX()+1, this.getY()+1, null);
					
				}
				@Override
				public void performAction_LEFT_RELEASE() {
					
					int friendID = Draw_5Friendlist.getDisplayedFriendID(this.getRelTextSize());
					//TODO
					
				}
			};	
		}
		
		//CREATE SCROLL OVER BOXES
		new MouseActionArea(startX, startY, widthPerBox, totalAmountOfBoxes*heightPerBox, MouseActionAreaType.FRIENDLIST_Scroll, "", -1, Color.PINK, Color.PINK, false, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.FRIENDLIST;
			}
			@Override
			public void performAction_MOUSEWHEEL_TURN(int turns) {
				
				if(turns > 0) {
					//SCROLL DOWN SO +1
					if(scrollValue+totalAmountOfBoxes < ClientData.getTotalFriendAmount()) {
						scrollValue++;
					}
				}else if(turns < 0) {
					//SCROLL UP SO -1
					if(scrollValue > 0) {
						scrollValue--;
					}
				}
			}
		};
		
	}
	
	//MAA USED FUNCTS:
	public static int getDisplayedFriendID(int pos) {
		return ClientData.getFriendByOverallPos(pos+scrollValue);
	}
	public static Color getStatusColor(String status) {
		if(status.equalsIgnoreCase("Offline")) {
			return Color.DARK_GRAY;
		}else if(status.contains("InGame")) {
			return Color.BLUE;
		}else {
			return Color.GREEN.darker();
		}
	}
	
	//FRIENDLIST PARAMATERS
	private static int startX = 1030, startY = 200; //FIXED VALUES BACAUSE MAAs SCALE AUTO WITH SCREENSIZE
	private static int scrollValue = 0;
	private static int heightPerBox = 50, widthPerBox = 536;
	private static int totalAmountOfBoxes = 13; //13
	public static int textSize = 21;
	public static int iconSize = (heightPerBox*60)/100;
	
	public static void draw(Graphics g) {
		
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
		//DRAW LEFT PAGE
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Friendlist", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*30)/100);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You have "+ClientData.getOnlineFriendList().size()+"/"+ClientData.getTotalFriendAmount()+" friends online", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*37)/100);
		
		int y = (GraphicsHandler.getHeight()*66)/100;
		g.setColor(Color.BLACK);
		g.drawLine((GraphicsHandler.getWidth()*17)/100, y, (GraphicsHandler.getWidth()*26)/100, y);
		g.drawLine((GraphicsHandler.getWidth()*38)/100, y, (GraphicsHandler.getWidth()*48)/100, y);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 25), "Friendrequests", (GraphicsHandler.getWidth()*32)/100, y);
		if(ClientData.getFriendRequests().isEmpty()) {
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 22), "You have no friendrequests!", (GraphicsHandler.getWidth()*33)/100, (GraphicsHandler.getHeight()*77)/100);
		}
		
		//DRAW RIGHT PAGE
		//Boaxes via MAAs
		
	}
	
}
