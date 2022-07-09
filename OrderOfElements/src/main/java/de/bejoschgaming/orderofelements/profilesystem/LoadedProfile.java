package de.bejoschgaming.orderofelements.profilesystem;

import java.awt.Color;

import de.bejoschgaming.orderofelements.connection.ServerConnection;

public class LoadedProfile {

	//ALL THE DATA NEEDS DEFAULT VALUES, takes some time until server updates to correct values
	private long loadedTime = 0, loadRequestTime = 0;
	
	private int playerID = -1;
	private String name = "Loading";
	
	private int level = 1, XP = 0, wins = 0, loses = 0, playedGames = 0;
	private int winstreak = 0;
	private RankingType ranking = RankingType.UNRANKED;
	private int crowns = 0;
	private Color displayColor = Color.PINK;
	private String titel = "Loading...";
	
	private String status = "Offline";
	
	public LoadedProfile(int playerID, boolean autoUpdate) {
		
		this.playerID = playerID;
		if(autoUpdate == true) {
			this.requestDataUpdate();
		}
		
	}

	public void requestDataUpdate() {
		
		if(this.loadRequestTime == 0 || System.currentTimeMillis()-this.loadRequestTime > ProfileHandler.delayBetweenDataRequests_sek*1000) {
			//NOT REQUESTED YET OR THRESHOLD TIME BETWEEN PASSED BY
			ServerConnection.sendPacket(201, ""+this.playerID);
			this.loadRequestTime = System.currentTimeMillis();
		}
		
	}
	
	public void updateStatus(String newStatus) { 
		
		this.status = newStatus;
		if(ClientData.getThisID() == this.playerID) {
			//IS LOCAL CLIENT LOADEDPROFILE, SO UPDATE SERVER ABOUT STATUS CHANGE
			ServerConnection.sendPacket(207, newStatus);
		}
		
	}
	
	public void updateData(String playerStatsSyntaxData) {
		
		//ID-NAME-..;..;..; (see for syntax details on the server create functions)
		String firstSplit[] = playerStatsSyntaxData.split("-");
		
		this.playerID = Integer.parseInt(firstSplit[0]);
		this.name = firstSplit[1];
		String otherData = firstSplit[2];
		String secondSplit[] = otherData.split(";");
		
		this.level = Integer.parseInt(secondSplit[0]);
		this.XP = Integer.parseInt(secondSplit[1]);
		this.wins = Integer.parseInt(secondSplit[2]);
		this.loses = Integer.parseInt(secondSplit[3]);
		this.playedGames = Integer.parseInt(secondSplit[4]);
		this.winstreak = Integer.parseInt(secondSplit[5]);
		this.ranking = RankingType.valueOf(secondSplit[6]);
		this.crowns = Integer.parseInt(secondSplit[7]);
		this.displayColor = Color.getColor(secondSplit[8]);
		this.titel = secondSplit[9];
		//ONLY SET IF STILL DEFAULT STATUS... otherwise it was already updated by 207 packet
		if(this.status.equalsIgnoreCase("Offline")) {
			this.status = secondSplit[10];
		}
		
		this.loadedTime = System.currentTimeMillis();
		
	}

	public boolean isUpToDate() {
		return ( this.loadedTime != 0 && this.getOutdatedMillis() <= (ProfileHandler.lifeTimeOfProfileData_sek*1000) );
	}
	public long getOutdatedMillis() {
		return System.currentTimeMillis()-this.loadedTime;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getXP() {
		return XP;
	}

	public int getWins() {
		return wins;
	}

	public int getLoses() {
		return loses;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public int getWinstreak() {
		return winstreak;
	}
	
	public RankingType getRanking() {
		return ranking;
	}

	public int getCrowns() {
		return crowns;
	}

	public Color getDisplayColor() {
		return displayColor;
	}

	public String getTitel() {
		return titel;
	}
	
	
	
}
