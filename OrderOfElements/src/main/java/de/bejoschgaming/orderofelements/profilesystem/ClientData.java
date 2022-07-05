package de.bejoschgaming.orderofelements.profilesystem;

public class ClientData {

	private static int thisID = -1;
	private static LoadedProfile thisProfile;
	
	
	public static void setThisProfile(String profileDataString) {
		
		thisProfile = new LoadedProfile(thisID, false);
		thisProfile.updateData(profileDataString);
		
	}
	public static void setThisID(int thisID_) {
		thisID = thisID_;
	}
	
	public static int getThisID() {
		return thisID;
	}
	public static LoadedProfile getThisProfile() {
		return thisProfile;
	}
}
