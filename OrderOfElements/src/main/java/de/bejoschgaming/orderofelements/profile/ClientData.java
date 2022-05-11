package de.bejoschgaming.orderofelements.profile;

public class ClientData {

	private static int clientID = -1;
	private static String name = "";
	
	public static void setClientID(int clientID_) {
		clientID = clientID_;
	}
	public static void setClientName(String name_) {
		name = name_;
	}
	
	public static int getClientID() {
		return clientID;
	}
	public static String getClientName() {
		return name;
	}
}
