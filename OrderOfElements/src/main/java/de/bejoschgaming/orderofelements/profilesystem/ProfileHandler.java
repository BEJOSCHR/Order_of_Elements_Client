package de.bejoschgaming.orderofelements.profilesystem;

import java.util.ArrayList;
import java.util.List;

public class ProfileHandler {

	public static final int lifeTimeOfProfileData_sek = 5*60; //THE TIME UNTIL DATA WILL BE RELOADED FROM SERVER UNTIL LAST LOAD
	public static final int delayBetweenDataRequests_sek = 5; //THE TIME UNTIL DATA WILL BE REQUESTED AGAIN AFTE THE LAST REQUEST
	
	private static List<LoadedProfile> loadedProfiles = new ArrayList<LoadedProfile>();
	
	public static LoadedProfile getProfileData(int playerID) {
		
		for(LoadedProfile loadedProfile : loadedProfiles) {
			if(loadedProfile.getPlayerID() == playerID) {
				if(loadedProfile.isUpToDate()) {
					return loadedProfile;
				}else {
					loadedProfile.requestDataUpdate();
					return loadedProfile;
				}
			}
		}
		//NOT LOADED YET
		return loadProfileData(playerID);
		
	}
	
	public static LoadedProfile loadProfileData(int playerID) {
		
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
	
	public static LoadedProfile getProfileByListPos(int pos) {
		if(pos < 0 || pos >= getLoadedProfileCount()) {
			return null;
		}else {
			return loadedProfiles.get(pos);
		}
	}
	public static int getLoadedProfileCount() {
		return loadedProfiles.size();
	}
	
}
