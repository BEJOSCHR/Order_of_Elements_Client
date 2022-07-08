/*
 * MouseActionAreaType
 * 
 * Version 0.1
 * Autor: Chris, Benni
 * 
 * Hier werden die unterschiedlichen MAA-Typen gesammelt und organisiert.
 * Dient der erleichterten Identifikation.
 */

package de.bejoschgaming.orderofelements.maa;

public enum MouseActionAreaType {

	//LOGIN
	LOGIN_Login,
	LOGIN_Register,
	LOGIN_Cancle,
	
	//MENU
	MENU_Play,
	MENU_Deckbuilder,
	MENU_Settings,
	MENU_Credits,
	MENU_Cancle,
	MENU_Friendlist,
	MENU_Matchhistory,
	MENU_PatchnotesScroll,
	
	//DECKBUILDER
	DECKBUILDER_Back,
	
	//FRIENDLIST
	FRIENDLIST_Back,
	FRIENDLIST_RequestSend,
	FRIENDLIST_RequestAccept,
	FRIENDLIST_RequestDecline,
	FRIENDLIST_RequestDisplay,
	
	//MATCHHISTORY
	MATCHHISTORY_Back,
	
	//CREDITS
	CREDITS_Back,
	
	MAA_TEST;

}
