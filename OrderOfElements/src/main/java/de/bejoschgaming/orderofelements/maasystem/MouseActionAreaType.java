/*
 * MouseActionAreaType
 * 
 * Version 0.1
 * Autor: Chris, Benni
 * 
 * Hier werden die unterschiedlichen MAA-Typen gesammelt und organisiert.
 * Dient der erleichterten Identifikation.
 */

package de.bejoschgaming.orderofelements.maasystem;

public enum MouseActionAreaType {

	// _ at the end means that there will be more then one maa with this type
	
	//MW
	MW_Close_,
	MW_DesicionWindow_Accept_,
	MW_DesicionWindow_Decline_,
	MW_InfoWindow_,
	
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
	DECKBUILDER_Overview_,
	DECKBUILDER_Selected,
	DECKBUILDER_Edit,
	DECKBUILDER_Delete,
	DECKBUILDER_Back,
	
	//FRIENDLIST
	FRIENDLIST_Back,
	FRIENDLIST_RequestSend,
	FRIENDLIST_RequestAccept,
	FRIENDLIST_RequestDecline,
	FRIENDLIST_RequestDisplay,
	FRIENDLIST_Scroll,
	FRIENDLIST_DisplayBox_,
	FRIENDLIST_Challenge_,
	FRIENDLIST_Spectate_,
	FRIENDLIST_Profile_,
	FRIENDLIST_Remove_,
	
	//MATCHHISTORY
	MATCHHISTORY_Back,
	
	//CREDITS
	CREDITS_Back,
	
	MAA_TEST;

}
