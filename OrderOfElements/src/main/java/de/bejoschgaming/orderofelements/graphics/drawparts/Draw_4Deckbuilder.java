package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.decksystem.Deck;
import de.bejoschgaming.orderofelements.decksystem.DeckHandler;
import de.bejoschgaming.orderofelements.decksystem.DeckbuilderType;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MW_DesicionWindow;
import de.bejoschgaming.orderofelements.mwsystem.mws.MW_InfoWindow;
import de.bejoschgaming.orderofelements.profilesystem.ClientData;

public class Draw_4Deckbuilder {

	public static final String bigSplit = ":", smallSplit = "-";
	
	public static DeckbuilderType displayType = DeckbuilderType.OVERVIEW;
	public static Deck selectedDeck = null;
	
	public static int deckListScroll = 0;
	
	private static int startX = 1030, startY = 200; //FIXED VALUES BACAUSE MAAs SCALE AUTO WITH SCREENSIZE
	private static int heightPerBox = 110, widthPerBox = 536;
	private static int totalAmountOfBoxes = 6; //
	public static int textSize = 21;
	public static int iconSize = (heightPerBox*30)/100;
	
	public static void initMAAs() {
		
		//INIT 
		
		//Overview MAAs - uses textsize as index identifier
		for(int i = 0 ; i < totalAmountOfBoxes ; i++) {
			new MouseActionArea(startX, startY+i*heightPerBox, widthPerBox, heightPerBox-1, MouseActionAreaType.DECKBUILDER_Overview_, "", i, Color.DARK_GRAY, Color.BLACK, true, false) {
				private int getRepresentedNumber() {
					return this.getRelTextSize()+Draw_4Deckbuilder.deckListScroll;
				}
				@Override
				public boolean isActiv() {
					return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW;
				}
				@Override
				public void drawCustomParts(Graphics g) {
					
					Color c = this.getStandardColor();
//					if(this.isHovered()) { c = this.getHoverColor(); }
					
					if(this.getRepresentedNumber() >= ClientData.getThisProfile().getMaxDecks()) {
						//NOT UNLOCKED YET
						if(this.isHovered()) {
							GraphicsHandler.drawCentralisedText(g, c, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), "Click to unlock", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
						}else {
							GraphicsHandler.drawCentralisedText(g, c, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), "Locked", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
						}
					}else if(this.getRepresentedNumber() < DeckHandler.getLoadedDecks().size()) {
						//SHOW DECK
						Deck deck = DeckHandler.getLoadedDecks().get(this.getRepresentedNumber());
						if(selectedDeck != null && deck.getDeckID() == selectedDeck.getDeckID()) {
							//SELECTED
							c = Color.ORANGE;
							g.setColor(c);
							g.drawRect(this.getX()+1, this.getY()+1, this.getWidth()-2, this.getHeight()-2);
							g.drawRect(this.getX()+2, this.getY()+2, this.getWidth()-4, this.getHeight()-4);
						}
						//DECK DISPLAY
						deck.drawDeckPreview(g, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getStandardColor());
					}else {
						//CREATE NEW DECK
						if(this.isHovered()) {
							GraphicsHandler.drawCentralisedText(g, c, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), "Create new deck", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
						}else {
							GraphicsHandler.drawCentralisedText(g, c, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), "Empty", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
						}
					}
					
				}
				@SuppressWarnings("unused")
				@Override
				public void performAction_LEFT_RELEASE() {
					if(this.getRepresentedNumber() >= ClientData.getThisProfile().getMaxDecks()) {
						//NOT UNLOCKED YET
						if( /*TODO Crownlevel to low*/ true) {
							//NOT ENOUGH CROWNS
							String[] text = {"You don't have enough crowns!", "You have "+100+"/"+300+" crowns, missing "+(300-100)+""};
							MW_InfoWindow mw = new MW_InfoWindow(text, FontHandler.getFont(FontHandler.medievalSharp_regular, 22), Color.WHITE, Color.DARK_GRAY);
							mw.setBlocking(true);
							mw.setMoveable(false);
							MultiWindowHandler.openMW(mw);
						}else {
							//UNLOCK CONFIRMATION
							MultiWindowHandler.openMW(new MW_DesicionWindow(
									"Do you want to unlock a deck slot for XXX crowns?", 
									FontHandler.getFont(FontHandler.medievalSharp_regular, 22), 
									new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Accept_, "Unlock ", 22, Color.WHITE, Color.GREEN.darker(), true) {
										@Override
										public void performAction_LEFT_RELEASE() {
											//TODO increase max decks in playerstats and remove crowns from playerstats
											MultiWindowHandler.closeMW(this.getMW());
										}
									}, 
									new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Decline_, "Cancel ", 22, Color.WHITE, Color.LIGHT_GRAY, true) {
										@Override
										public void performAction_LEFT_RELEASE() {
											MultiWindowHandler.closeMW(this.getMW());
										}
									},
									Color.WHITE, Color.DARK_GRAY));
						}
					}else if(this.getRepresentedNumber() < DeckHandler.getLoadedDecks().size()) {
						//SELECT DECK
						Draw_4Deckbuilder.selectedDeck = DeckHandler.getLoadedDecks().get(this.getRepresentedNumber());
					}else {
						//CREATE NEW DECK
						selectedDeck = new Deck(-1, ClientData.getThisID(), "New deck", "");
						AnimationHandler.startAnimation(new MenuBookAnimation(true) {
							@Override
							protected void halfTimeAction() {
								Draw_4Deckbuilder.displayType = DeckbuilderType.EDIT;
							}
						});
					}
				}
			};
		}
		//Selceted Deck MAA
		new MouseActionArea(330, 660, widthPerBox, heightPerBox, MouseActionAreaType.DECKBUILDER_Selected, "", 1, Color.DARK_GRAY, Color.DARK_GRAY, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void drawCustomParts(Graphics g) {
				
				if(selectedDeck != null) {
					GraphicsHandler.drawCentralisedText(g, Color.DARK_GRAY, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(20)), "SELECTED DECK", this.getX()+this.getWidth()/2, this.getY()-15);
					selectedDeck.drawDeckPreview(g, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getStandardColor());
				}else {
					GraphicsHandler.drawCentralisedText(g, Color.DARK_GRAY, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), "Select a deck", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
				}
				
			}
		};
		//EDIT/DELETE/BACK MAA
		int width = (widthPerBox-160-20-20)/2;
		new MouseActionArea(330, 660+30+heightPerBox, width, 60, MouseActionAreaType.DECKBUILDER_Edit, "Edit", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void draw(Graphics g) {
				if(selectedDeck == null) {
					this.setStandardColor(Color.LIGHT_GRAY);
					this.setHoverColor(Color.LIGHT_GRAY);
				}else {
					this.setStandardColor(Color.DARK_GRAY);
					this.setHoverColor(Color.GREEN.darker());
				}
				super.draw(g);
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				if(selectedDeck == null) { return; }
				AnimationHandler.startAnimation(new MenuBookAnimation(true) {
					@Override
					protected void halfTimeAction() {
						Draw_4Deckbuilder.displayType = DeckbuilderType.EDIT;
					}
				});
			}
		};
		new MouseActionArea(330+width+20, 660+30+heightPerBox, width, 60, MouseActionAreaType.DECKBUILDER_Delete, "Delete", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void draw(Graphics g) {
				if(selectedDeck == null) {
					this.setStandardColor(Color.LIGHT_GRAY);
					this.setHoverColor(Color.LIGHT_GRAY);
				}else {
					this.setStandardColor(Color.DARK_GRAY);
					this.setHoverColor(Color.RED.darker());
				}
				super.draw(g);
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				if(selectedDeck == null) { return; }
				MultiWindowHandler.openMW(new MW_DesicionWindow(
						"Do you realy want to delete the deck '"+selectedDeck.getName()+"'?", 
						FontHandler.getFont(FontHandler.medievalSharp_regular, 22), 
						new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Accept_, "Delete ", 22, Color.WHITE, Color.RED.darker(), true) {
							@Override
							public void performAction_LEFT_RELEASE() {
								DeckHandler.removeDeck(selectedDeck.getDeckID());
								MultiWindowHandler.closeMW(this.getMW());
							}
						}, 
						new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Decline_, "Cancel ", 22, Color.WHITE, Color.LIGHT_GRAY, true) {
							@Override
							public void performAction_LEFT_RELEASE() {
								MultiWindowHandler.closeMW(this.getMW());
							}
						},
						Color.WHITE, Color.DARK_GRAY));
			}
		};
		new MouseActionArea(330+width*2+20*2, 660+30+heightPerBox, 160, 60, MouseActionAreaType.DECKBUILDER_Back, "Back", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER;// && Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				AnimationHandler.startAnimation(new MenuBookAnimation(false) {
					@Override
					protected void halfTimeAction() {
						if(Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW) {
							GraphicsHandler.switchTo(DrawState.MENU);
						}else {
							Draw_4Deckbuilder.displayType = DeckbuilderType.OVERVIEW;
						}
						Draw_4Deckbuilder.selectedDeck = null;
					}
				});
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
		
		if(Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW) {
			//OVERVIEW
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Deckbuilder", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*30)/100);
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You have "+DeckHandler.getLoadedDecks().size()+"/"+ClientData.getThisProfile().getMaxDecks()+" decks", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*37)/100);
			
		}else if(Draw_4Deckbuilder.displayType == DeckbuilderType.EDIT) {
			//EDIT
			
			
		}
		
		
	}
	
}
