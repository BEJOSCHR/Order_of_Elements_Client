package de.bejoschgaming.orderofelements.profilesystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;

public class ClientData {

	private static int thisID = -1;
	private static LoadedProfile thisProfile;
	
	private static List<Integer> friendList_online = new ArrayList<Integer>();
	private static List<Integer> friendList_offline = new ArrayList<Integer>();
	
	private static Map<Integer, String> friendRequests = new HashMap<Integer, String>();
	
	public static void setThisProfile(String profileDataString) {
		
		thisProfile = new LoadedProfile(thisID, false);
		thisProfile.updateData(profileDataString);
		
	}
	public static void setThisID(int thisID_) {
		thisID = thisID_;
	}
	
	public static void updateFriend(int friendID, boolean online) {
		
		removeFromFriendList(friendID);
		if(online == true) {
			//ONLINE
			friendList_online.add(friendID);
		}else {
			//OFFLINE
			friendList_offline.add(friendID);
		}
		
	}
	private static boolean removeFromFriendList(int friendID) {
		boolean hit = false;
		if(friendList_online.contains((Object) friendID)) {
			friendList_online.remove((Object) friendID);
			hit = true;
		}
		if(friendList_offline.contains((Object) friendID)) {
			friendList_offline.remove((Object) friendID);
			hit = true;
		}
		return hit;
	}
	public static void removeFriend(int friendID) {
		boolean hit = removeFromFriendList(friendID);
		if(hit == false) {
			ConsoleHandler.printMessageInConsole("Tried to remove friendship which doesnt exist! ("+friendID+")", true);
		}
	}
	
	public static void addFriendRequest(int friendID, String friendName) {
		friendRequests.put(friendID, friendName);
	}
	public static void removeFriendRequest(int friendID) {
		if(friendRequests.containsKey(friendID)) {
			friendRequests.remove(friendID);
		}else {
			ConsoleHandler.printMessageInConsole("Tried to remove friendrequest which doesnt exist! ("+friendID+")", true);
		}
	}
	public static Map<Integer, String> getFriendRequests() {
		return friendRequests;
	}
	
	public static int getThisID() {
		return thisID;
	}
	public static LoadedProfile getThisProfile() {
		return thisProfile;
	}
	public static List<Integer> getOnlineFriendList() {
		return friendList_online;
	}
	public static List<Integer> getOfflineFriendList() {
		return friendList_offline;
	}
	public static int getTotalFriendAmount() {
		return friendList_online.size()+friendList_offline.size();
	}
	public static int getFriendByOverallPos(int pos) {
		
		if(pos < friendList_online.size()) {
			return friendList_online.get(pos);
		}else if(pos < getTotalFriendAmount()) {
			return friendList_offline.get(pos-friendList_online.size());
		}else {
			return -1;
		}
		
	}
}
