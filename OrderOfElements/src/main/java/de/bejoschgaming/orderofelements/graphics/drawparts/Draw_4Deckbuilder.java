package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.deckbuildersystem.DeckBuilder_Data;
import de.bejoschgaming.orderofelements.deckbuildersystem.DeckBuilder_Map;
import de.bejoschgaming.orderofelements.decksystem.Deck;
import de.bejoschgaming.orderofelements.decksystem.DeckHandler;
import de.bejoschgaming.orderofelements.decksystem.DeckbuilderType;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.gamesystem.map.MapData;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.handler.MouseHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MW_DesicionWindow;
import de.bejoschgaming.orderofelements.mwsystem.mws.MW_InfoWindow;
import de.bejoschgaming.orderofelements.profilesystem.ClientData;

public class Draw_4Deckbuilder {
	
	//DECK SELECT
	private static int startX_deckSelectButtons = 1030, startY_deckSelectButtons = 200; //FIXED VALUES BACAUSE MAAs SCALE AUTO WITH SCREENSIZE
	private static int heightPerBox = 110, widthPerBox = 536;
	private static int totalAmountOfBoxes = 6;
	public static int textSize = 21;
	public static int iconSize = (heightPerBox*30)/100;
	
	//DECK/EDIT ACTION
	private static int actionButtonX = 330, actionButtonY = 680; //FIXED VALUES BACAUSE MAAs SCALE AUTO WITH SCREENSIZE
	private static int actionButtonSpace = 20;
	private static int actionButtonExtraWidth = 160;
	private static int actionButtonWidth = (widthPerBox-actionButtonExtraWidth-actionButtonSpace*2)/2;
	
	//UNIT SELECT
	private static int unitSelect_width = actionButtonWidth*2+actionButtonSpace*2+actionButtonExtraWidth*1;
	private static int unitSelect_startX = 330, unitSelect_startY = 175; //FIXED VALUES BACAUSE MAAs SCALE AUTO WITH SCREENSIZE
	private static int unitSelect_extraTitelBorder = 50;
	private static int unitSelect_sideBorder = 20;
	private static int unitSelect_buttonSize = 80;
	private static int unitSelect_buttonSpace = 20;
	private static int unitSelect_buttonNumberLR = 5, unitSelect_buttonNumberUD = 5;
	private static int unitSelect_height = unitSelect_extraTitelBorder*1+unitSelect_sideBorder*1+unitSelect_buttonNumberUD*(unitSelect_buttonSize+unitSelect_buttonSpace)-unitSelect_buttonSpace;
	
	public static void initMAAs() {
		
		//INIT 
		
		//Overview DECK MAAs - uses textsize as index identifier
		for(int i = 0 ; i < totalAmountOfBoxes ; i++) {
			new MouseActionArea(startX_deckSelectButtons, startY_deckSelectButtons+i*heightPerBox, widthPerBox, heightPerBox-1, MouseActionAreaType.DECKBUILDER_Overview_, "", i, Color.DARK_GRAY, Color.BLACK, true, false) {
				private int getRepresentedNumber() {
					return this.getRelTextSize()+DeckBuilder_Data.deckListScroll;
				}
				@Override
				public boolean isActiv() {
					return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.OVERVIEW;
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
						if(DeckBuilder_Data.selectedDeck != null && deck.getDeckID() == DeckBuilder_Data.selectedDeck.getDeckID()) {
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
						DeckBuilder_Data.selectedDeck = DeckHandler.getLoadedDecks().get(this.getRepresentedNumber());
					}else {
						//CREATE NEW DECK
						DeckBuilder_Data.selectedDeck = new Deck(-1, ClientData.getThisID(), "New deck", "");
						AnimationHandler.startAnimation(new MenuBookAnimation(true) {
							@Override
							protected void halfTimeAction() {
								changeToEditMode();
							}
							@Override
							protected void finishAction(boolean stepLimitReached) {
								openRenameWindow();
							};
						});
					}
				}
			};
		}
		//Selceted Deck MAA
		new MouseActionArea(actionButtonX, actionButtonY, widthPerBox, heightPerBox, MouseActionAreaType.DECKBUILDER_Selected, "", 1, Color.DARK_GRAY, Color.DARK_GRAY, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void drawCustomParts(Graphics g) {
				if(DeckBuilder_Data.selectedDeck != null) {
					GraphicsHandler.drawCentralisedText(g, Color.DARK_GRAY, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(20)), "SELECTED DECK", this.getX()+this.getWidth()/2, this.getY()-15);
					DeckBuilder_Data.selectedDeck.drawDeckPreview(g, this.getX(), this.getY(), this.getWidth(), this.getHeight(), this.getStandardColor());
				}else {
					GraphicsHandler.drawCentralisedText(g, Color.DARK_GRAY, FontHandler.getFont(FontHandler.medievalSharp_regular, GraphicsHandler.getRelativTextSize(22)), "Select a deck", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
				}
			}
		};
		
		//DECK NAME
		new MouseActionArea(actionButtonX, actionButtonY+heightPerBox-60+10, actionButtonWidth*2+20, 60, MouseActionAreaType.DECKBUILDER_NameDisplay, "", 22, null, null, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.EDIT;
			}
			@Override
			public void draw(Graphics g) {
				if(DeckBuilder_Data.selectedDeck == null) { return; }
				g.setColor(Color.DARK_GRAY);
				g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
				g.setColor(Color.WHITE);
				g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
				int textWidth = g.getFontMetrics(FontHandler.getFont(FontHandler.medievalSharp_regular, 30)).stringWidth(" "+DeckBuilder_Data.deckName);
				GraphicsHandler.drawCentralisedText(g, Color.ORANGE, FontHandler.getFont(FontHandler.medievalSharp_regular, 30), DeckBuilder_Data.deckName, this.getX()+textWidth/2+10, this.getY()+this.getHeight()/2);
			}
		};
		//EDIT
		new MouseActionArea(actionButtonX, actionButtonY+30+heightPerBox, actionButtonWidth, 60, MouseActionAreaType.DECKBUILDER_Edit, "Edit", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void draw(Graphics g) {
				if(DeckBuilder_Data.selectedDeck == null) {
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
				if(DeckBuilder_Data.selectedDeck == null) { return; }
				AnimationHandler.startAnimation(new MenuBookAnimation(true) {
					@Override
					protected void halfTimeAction() {
						changeToEditMode();
					}
				});
			}
		};
		//DELETE
		new MouseActionArea(actionButtonX+actionButtonWidth+actionButtonSpace, actionButtonY+30+heightPerBox, actionButtonWidth, 60, MouseActionAreaType.DECKBUILDER_Delete, "Delete", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void draw(Graphics g) {
				if(DeckBuilder_Data.selectedDeck == null) {
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
				if(DeckBuilder_Data.selectedDeck == null) { return; }
				MultiWindowHandler.openMW(new MW_DesicionWindow(
						"Do you realy want to delete the deck '"+DeckBuilder_Data.selectedDeck.getName()+"'?", 
						FontHandler.getFont(FontHandler.medievalSharp_regular, 22), 
						new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Accept_, "Delete ", 22, Color.WHITE, Color.RED.darker(), true) {
							@Override
							public void performAction_LEFT_RELEASE() {
								DeckHandler.removeDeck(DeckBuilder_Data.selectedDeck.getDeckID());
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
		//SAVE
		new MouseActionArea(actionButtonX, actionButtonY+30+heightPerBox, actionButtonWidth, 60, MouseActionAreaType.DECKBUILDER_Edit, "Save", 22, Color.DARK_GRAY, Color.GREEN.darker(), true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.EDIT;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				if(DeckBuilder_Data.selectedDeck == null) { return; }
				
				int kingqueen = 0, wizardwitch = 0;
				for(Unit unit : DeckBuilder_Data.layoutMap.getUnits()) {
					if(unit.getId() == 1 || unit.getId() == 2) {
						kingqueen++;
					}else if(unit.getId() == 3 || unit.getId() == 4) {
						wizardwitch++;
					}
				}
				if(kingqueen != 1 || wizardwitch != 1) { return; } //INVALID MAIN UNIT AMOUNT
				
				//SAVE MAP INTO DECK AND SEND
				//SYNTAX: 221-DeckID;DeckName;DeckData
				//IF DeckID is -1 its a new created deck else its an update
				int deckID = DeckBuilder_Data.selectedDeck.getDeckID();
				String deckName = DeckBuilder_Data.deckName;
				String deckData = DeckBuilder_Data.layoutMap.getUnitDataAsString();
				ServerConnection.sendPacket(221, deckID+";"+deckName+";"+deckData);
				
				AnimationHandler.startAnimation(new MenuBookAnimation(false) {
					@Override
					protected void halfTimeAction() {
						changeAwayFromEditMode();
					}
				});
			}
		};
		//COST DISPLAY
		new MouseActionArea(actionButtonX+actionButtonWidth+actionButtonSpace, actionButtonY+30+heightPerBox, actionButtonWidth, 60, MouseActionAreaType.DECKBUILDER_CostDisplay, "", 22, null, null, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.EDIT;
			}
			@Override
			public void draw(Graphics g) {
				if(DeckBuilder_Data.selectedDeck == null) { return; }
				GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 26), DeckBuilder_Data.deckCost+"/"+DeckHandler.MAX_DECK_COST+" Points", this.getX()+this.getWidth()/2, this.getY()+this.getHeight()/2);
			}
		};
		//RENAME
		new MouseActionArea(actionButtonX+actionButtonWidth*2+actionButtonSpace*2, actionButtonY+heightPerBox-60+10, actionButtonExtraWidth, 60, MouseActionAreaType.DECKBUILDER_Rename, "Rename", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.EDIT;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				openRenameWindow();
			}
		};
		//BACK
		new MouseActionArea(actionButtonX+actionButtonWidth*2+20*2, actionButtonY+30+heightPerBox, actionButtonExtraWidth, 60, MouseActionAreaType.DECKBUILDER_Back, "Back", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER;// && Draw_4Deckbuilder.displayType == DeckbuilderType.OVERVIEW;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				if(DeckBuilder_Data.displayType == DeckbuilderType.OVERVIEW) {
					//MAIN BACK TO MENU
					AnimationHandler.startAnimation(new MenuBookAnimation(false) {
						@Override
						protected void halfTimeAction() {
							GraphicsHandler.switchTo(DrawState.MENU);
							DeckBuilder_Data.selectedDeck = null;
						}
					});
				}else {
					//OTHER BACK TO MAIN
					MultiWindowHandler.openMW(new MW_DesicionWindow(
							"If you exit, all changes are lost!", 
							FontHandler.getFont(FontHandler.medievalSharp_regular, 22), 
							new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Accept_, "Exit ", 22, Color.WHITE, Color.RED.darker(), true) {
								@Override
								public void performAction_LEFT_RELEASE() {
									MultiWindowHandler.closeMW(this.getMW());
									AnimationHandler.startAnimation(new MenuBookAnimation(false) {
										@Override
										protected void halfTimeAction() {
											changeAwayFromEditMode();
										}
									});
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
			}
		};
		
		new MouseActionArea(unitSelect_startX, unitSelect_startY, unitSelect_width, unitSelect_height, MouseActionAreaType.DECKBUILDER_UnitScroll, "", 1, Color.WHITE, Color.ORANGE, false, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.EDIT;
			}
			@Override
			public void performAction_MOUSEWHEEL_TURN(int turns) {
				
				if(turns > 0) {
					//SCROLL DOWN SO +1
					if(DeckBuilder_Data.unitListScroll < (UnitHandler.getUnitTemplates().size()/unitSelect_buttonNumberLR)) {
						DeckBuilder_Data.unitListScroll++;
					}
				}else if(turns < 0) {
					//SCROLL UP SO -1
					if(DeckBuilder_Data.unitListScroll > 0) {
						DeckBuilder_Data.unitListScroll--;
					}
				}
				
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				DeckBuilder_Data.draggedUnit = null;
			}
			@Override
			public void performAction_RIGHT_PRESS() {
				DeckBuilder_Data.draggedUnit = null;
			}
		};
		
		//UNIT SELECT - x:y via displayText
		int cordX = unitSelect_startX+unitSelect_sideBorder;
		int cordY = unitSelect_startY+unitSelect_extraTitelBorder+unitSelect_sideBorder;
		
		for(int y = 0 ; y < unitSelect_buttonNumberUD ; y++) {
			
			for(int x = 0 ; x < unitSelect_buttonNumberLR ; x++) {
				
				new MouseActionArea(cordX, cordY, unitSelect_buttonSize, unitSelect_buttonSize, MouseActionAreaType.DECKBUILDER_UnitSelect_, x+":"+y, 22, Color.WHITE, Color.ORANGE, true, false) {
					private Unit getRepresentedUnit() {
						String[] cords = this.getDisplayText().split(":");
						int ownX = Integer.parseInt(cords[0]);
						int ownY = Integer.parseInt(cords[1])+DeckBuilder_Data.unitListScroll;
						int representedIndex = ownY*unitSelect_buttonNumberUD+ownX;
						return UnitHandler.getUnitTemplateByIndex(representedIndex);
					}
					@Override
					public boolean isActiv() {
						return GraphicsHandler.getDrawState() == DrawState.DECKBUILDER && DeckBuilder_Data.displayType == DeckbuilderType.EDIT && this.getRepresentedUnit() != null;
					}
					private boolean isSpezialUnitCase(Unit u) {
						if(u.getId() == 1 || u.getId() == 2) {
							//IS KING OR QUEEN
							for(Unit unit : DeckBuilder_Data.layoutMap.getUnits()) {
								if(unit.getId() == 1 || unit.getId() == 2) {
									//HAS ALREADY KING OR QUEEN
									return true;
								}
							}
							return false;
						}else if(u.getId() == 3 || u.getId() == 4) {
							//IS WIZARD OR WITCH
							for(Unit unit : DeckBuilder_Data.layoutMap.getUnits()) {
								if(unit.getId() == 3 || unit.getId() == 4) {
									//HAS ALREADY WIZARD OR WITCH
									return true;
								}
							}
							return false;
						}else {
							return false;
						}
					}
					@Override
					public void draw(Graphics g) {
						Unit u = this.getRepresentedUnit(); //IS != null
						Color c = this.getStandardColor();
						if(DeckBuilder_Data.deckCost+u.getCost() > DeckHandler.MAX_DECK_COST || isSpezialUnitCase(u) == true) {
							c = Color.LIGHT_GRAY;
						}else if(this.isHovered() || (DeckBuilder_Data.draggedUnit != null && getRepresentedUnit().getId() == DeckBuilder_Data.draggedUnit.getId()) ) {
							//HOVERED OR DRAGGED
							c = (this.getHoverColor());
						}
						drawUnitView(g, u, c, this.getX(), this.getY(), this.getWidth(), this.getHeight());
					}
					@Override
					public void performAction_LEFT_PRESS() {
						Unit u = getRepresentedUnit();
						if(DeckBuilder_Data.deckCost+u.getCost() <= DeckHandler.MAX_DECK_COST && isSpezialUnitCase(u) == false) {
							//NOT ENOUGH POINTS
							DeckBuilder_Data.draggedUnit = getRepresentedUnit();
						}
					}
					@Override
					public void performAction_LEFT_RELEASE() {
						DeckBuilder_Data.draggedUnit = null;
					}
					@Override
					public void performAction_RIGHT_PRESS() {
						DeckBuilder_Data.draggedUnit = null;
					}
				};
				
				cordX += unitSelect_buttonSize+unitSelect_buttonSpace;
			}
			
			cordX = unitSelect_startX+unitSelect_sideBorder;
			cordY += unitSelect_buttonSize+unitSelect_buttonSpace;
		}
		
	}
	
	public static void drawUnitView(Graphics g, Unit u, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRoundRect(x, y, width, height, 5, 5);
		g.drawImage(u.getSmall_icon(), x+(width*17)/100, y+(height*20)/100, null);
		if(u.getCost() == -1) {
			//0 COST UNIT
			GraphicsHandler.drawCentralisedText(g, Color.BLUE, FontHandler.getFont(FontHandler.bridgnorth_bold, 24), "0", x+(width*73)/100, y+(height*20)/100);
		}else {
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 24), ""+u.getCost(), x+(width*73)/100, y+(height*20)/100);
		}
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 20), u.getCategory().getCategory().substring(0, 2), x+(width*73)/100, y+(height*55)/100);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 11), u.getName(), x+width/2, y+(height*88)/100);
	}
	
	private static void changeToEditMode() {
		
		UnitHandler.sortUnits();
		
		//RECALC DECKBUILDER FIELD SIZE
		DeckBuilder_Data.displayFieldSize = (int) ( ( ((double) GraphicsHandler.getHeight()) * 3D ) / 100D);
		DeckBuilder_Data.map_offset_X = (int) ( ( ((double) GraphicsHandler.getWidth()) * 50D ) / 100D) + 3*DeckBuilder_Data.displayFieldSize;
		DeckBuilder_Data.map_offset_Y = (int) ( ( ((double) GraphicsHandler.getHeight()) * 20D ) / 100D);
		
		DeckBuilder_Data.layoutMap = new DeckBuilder_Map(DeckBuilder_Data.selectedDeck.getUnits(), MapData.MAP_WIDTH/2, MapData.MAP_HEIGHT);
		DeckBuilder_Data.displayType = DeckbuilderType.EDIT;
		DeckBuilder_Data.unitListScroll = 0;
		
		DeckBuilder_Data.deckName = DeckBuilder_Data.selectedDeck.getName();
		DeckBuilder_Data.deckCost = DeckBuilder_Data.selectedDeck.getTotalCost();
		
	}
	private static void changeAwayFromEditMode() {
		
		DeckBuilder_Data.displayType = DeckbuilderType.OVERVIEW;
		if(DeckBuilder_Data.selectedDeck != null && DeckBuilder_Data.selectedDeck.getDeckID() == -1) {
			DeckBuilder_Data.selectedDeck = null;
		}
		DeckBuilder_Data.layoutMap.unregisterSavedUnits();
		DeckBuilder_Data.layoutMap = null;
		
	}
	
	private static void openRenameWindow() {
		
		MultiWindowHandler.openMW(new MW_DesicionWindow(
				"          Enter a new deck name:          ", 
				FontHandler.getFont(FontHandler.medievalSharp_regular, 22), 
				new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Accept_, "Rename ", 22, Color.WHITE, Color.GREEN.darker(), true) {
					@Override
					public void performAction_LEFT_RELEASE() {
						String newName = TextFieldHandler.DECKBUILDER_Rename.getText().trim();
						if(newName.length() > 15) {
							TextFieldHandler.DECKBUILDER_Rename.setText(newName.substring(0, 15));
						}else if(newName.length() <= 0) {
							//MISSING NAME SO DO NOTHING
						}else if(ServerConnection.checkInputForServerUse(newName) == false) {
							TextFieldHandler.DECKBUILDER_Rename.setText("Invalid");
						}else {
							DeckBuilder_Data.deckName = newName;
							MultiWindowHandler.closeMW(this.getMW());
						}
					}
				}, 
				new MouseActionArea(115, 35, MouseActionAreaType.MW_DesicionWindow_Decline_, "Cancel ", 22, Color.WHITE, Color.LIGHT_GRAY, true) {
					@Override
					public void performAction_LEFT_RELEASE() {
						MultiWindowHandler.closeMW(this.getMW());
					}
				},
				Color.WHITE, Color.DARK_GRAY) {
			
			@Override
			public void performOpenAction() {
				
				this.blocking = true;
				TextFieldHandler.showTextField(TextFieldHandler.DECKBUILDER_Rename);
				TextFieldHandler.DECKBUILDER_Rename.requestFocus();
				
			}
			@Override
			public void performClosingAction() {
				
				TextFieldHandler.DECKBUILDER_Rename.setText("");
				TextFieldHandler.hideTextField(TextFieldHandler.DECKBUILDER_Rename);
				GraphicsHandler.getFrame().requestFocus();
				
			}
			
		});
		
	}
	
	public static void draw(Graphics g) {
		
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
		if(DeckBuilder_Data.displayType == DeckbuilderType.OVERVIEW) {
			//OVERVIEW
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Deckbuilder", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*30)/100);
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You have "+DeckHandler.getLoadedDecks().size()+"/"+ClientData.getThisProfile().getMaxDecks()+" decks", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*37)/100);
			
		}else if(DeckBuilder_Data.displayType == DeckbuilderType.EDIT) {
			//EDIT
			//MAP
			DeckBuilder_Data.layoutMap.draw(g); 
			//UNIT SELECTION
			int realX = GraphicsHandler.getRelativX(unitSelect_startX);
			int realY = GraphicsHandler.getRelativY(unitSelect_startY);
			int realUnitWidth = GraphicsHandler.getRelativX(unitSelect_width);
			int realUnitHeight = GraphicsHandler.getRelativY(unitSelect_height);
			int arcs = 8;
			g.setColor(Color.DARK_GRAY);
			g.fillRoundRect(realX, realY, realUnitWidth, realUnitHeight, arcs, arcs);
			g.setColor(Color.WHITE);
			g.drawRoundRect(realX, realY, realUnitWidth, realUnitHeight, arcs, arcs);
			g.setColor(Color.WHITE);
			Font font = FontHandler.getFont(FontHandler.bridgnorth_bold, GraphicsHandler.getRelativTextSize(24));
			g.setFont(font);
			int titelHeight = g.getFontMetrics(font).getHeight() * 2 / 3;
			int realTitelBorder = GraphicsHandler.getRelativY(unitSelect_extraTitelBorder);
			g.drawString("Available Units", realX+30, realY+titelHeight/2+realTitelBorder/2);
			g.setColor(Color.WHITE);
			g.drawLine(realX+1, realY+realTitelBorder, realX+realUnitWidth-2, realY+realTitelBorder);
			
			if(DeckBuilder_Data.draggedUnit != null) {
				int relSize = GraphicsHandler.getRelativX(unitSelect_buttonSize);
				drawUnitView(g, DeckBuilder_Data.draggedUnit, Color.WHITE, MouseHandler.getMouseX(), MouseHandler.getMouseY(), relSize, relSize);
			}
			
			if(DeckBuilder_Data.layoutMap != null) {
				int kingqueen = 0, wizardwitch = 0;
				for(Unit unit : DeckBuilder_Data.layoutMap.getUnits()) {
					if(unit.getId() == 1 || unit.getId() == 2) {
						kingqueen++;
					}else if(unit.getId() == 3 || unit.getId() == 4) {
						wizardwitch++;
					}
				}
				if(kingqueen == 0) {
					GraphicsHandler.drawCentralisedText(g, Color.RED, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You need a King or a Queen in your deck!", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*79)/100);
				}else if(kingqueen == 2) {
					GraphicsHandler.drawCentralisedText(g, Color.RED, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You can only have a King OR a Queen!", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*79)/100);
				}
				if(wizardwitch == 0) {
					GraphicsHandler.drawCentralisedText(g, Color.RED, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You need a Wizard or a Witch in your deck!", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*82)/100);
				}else if(wizardwitch == 2) {
					GraphicsHandler.drawCentralisedText(g, Color.RED, FontHandler.getFont(FontHandler.medievalSharp_regular, 28), "You can only have a Wizard OR a Witch!", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*82)/100);
				}
			}
			
		}
		
		
	}
	
}
