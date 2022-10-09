package de.bejoschgaming.orderofelements.decksystem;

import java.util.LinkedList;

import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_4Deckbuilder;

public class DeckHandler {

	public static final int MAX_DECK_COST = 15;
	
	private static LinkedList<Deck> loadedDecks = new LinkedList<Deck>();
	
	//DECK LOAD IS ALWAYS SEND AFTER CHANGES ON SERVER HAPPEN
	
	public static void addDeck(Deck newDeck) {
		
		for(Deck deck : loadedDecks) {
			if(deck.getDeckID() == newDeck.getDeckID()) {
				//UPDATE
				deck.setOwnerID(newDeck.getOwnerID());
				deck.setName(newDeck.getName());
				deck.setData(newDeck.getData());
				return;
			}
		}
		//NO UPDATE SO NEW ONE
		loadedDecks.add(newDeck);
		
	}
	public static void removeDeck(int deckID) {
		
		for(Deck deck : loadedDecks) {
			if(deck.getDeckID() == deckID) {
				loadedDecks.remove(deck);
				ServerConnection.sendPacket(222, ""+deck.getDeckID());;
				Draw_4Deckbuilder.selectedDeck = null; //UPDATE SO REMOVED ONE IS NOT SELECTED
				return;
			}
		}
		//NOT HIT A FITTING DECK
		ConsoleHandler.printMessageInConsole("Can't find deck '"+deckID+"' to remove!", true);
		
	}

	public static Deck getDeck(int deckID) {
		for(Deck deck : loadedDecks) {
			if(deck.getDeckID() == deckID) {
				return deck;
			}
		}
		return null;
	}
	public static LinkedList<Deck> getLoadedDecks() {
		return loadedDecks;
	}

}
