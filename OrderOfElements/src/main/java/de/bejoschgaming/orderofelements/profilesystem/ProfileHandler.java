package de.bejoschgaming.orderofelements.profilesystem;

import java.util.ArrayList;
import java.util.List;

public class ProfileHandler {

	public static final int lifeTimeOfProfileData_sek = 5*60; //THE TIME UNTIL DATA WILL BE RELOADED FROM SERVER UNTIL LAST LOAD
	
	private static List<LoadedProfile> loadedProfiles = new ArrayList<LoadedProfile>();
	
	public static LoadedProfile getProfile(int playerID) {
		
		for(LoadedProfile loadedProfile : loadedProfiles) {
			if(loadedProfile.getPlayerID() == playerID) {
				if(loadedProfile.isStillUpToDate()) {
					return loadedProfile;
				}else {
					loadedProfile.requestDataUpdate();
					return loadedProfile;
				}
			}
		}
		//NOT LOADED YET
		return loadProfile(playerID);
		
	}
	
	public static LoadedProfile loadProfile(int playerID) {
		
		LoadedProfile loadedProfile = new LoadedProfile(playerID);
		loadedProfiles.add(loadedProfile);
		return loadedProfile;
		
	}
	
	public static void updateProfileData(String playerStatsSyntaxData) {
		
		int playerID = Integer.parseInt(playerStatsSyntaxData.split("-")[0]);
		for(LoadedProfile loadedProfile : loadedProfiles) {
			if(loadedProfile.getPlayerID() == playerID) {
				loadedProfile.updateData(playerStatsSyntaxData);
			}
		}
		
	}
	
}
