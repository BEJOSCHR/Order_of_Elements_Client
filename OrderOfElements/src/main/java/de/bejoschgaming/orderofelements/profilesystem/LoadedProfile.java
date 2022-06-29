package de.bejoschgaming.orderofelements.profilesystem;

import java.awt.Color;

import de.bejoschgaming.orderofelements.connection.ServerConnection;

public class LoadedProfile {

	//ALL THE DATA NEEDS DEFAULT VALUES, takes some time until server updates to correct values
	private long loadedTime = 0;
	
	private int playerID = -1;
	private String name = "Loading";
	
	private int level = 1, XP = 0, wins = 0, loses = 0, playedGames = 0;
	private RankingType ranking = RankingType.UNRANKED;
	private int crowns = 0;
	private Color displayColor = Color.WHITE;
	private String titel = "Loading...";
	
	private String status = "Offline";
	
	public LoadedProfile(int playerID) {
		
		this.playerID = playerID;
		this.requestDataUpdate();
		
	}

	public void requestDataUpdate() {
		ServerConnection.sendPacket(201, ""+playerID);
	}
	
	public void updateStatus(String newStatus) { 
		
		this.status = newStatus;
		
	}
	
	public void updateData(String playerStatsSyntaxData) {
		
		//ID-NAME-..;..;..;
		String firstSplit[] = playerStatsSyntaxData.split("-");
		
		playerID = Integer.parseInt(firstSplit[0]);
		name = firstSplit[1];
		String otherData = firstSplit[2];
		String secondSplit[] = otherData.split(";");
		
		level = Integer.parseInt(secondSplit[0]);
		XP = Integer.parseInt(secondSplit[1]);
		wins = Integer.parseInt(secondSplit[2]);
		loses = Integer.parseInt(secondSplit[3]);
		playedGames = Integer.parseInt(secondSplit[4]);
		ranking = RankingType.valueOf(secondSplit[5]);
		crowns = Integer.parseInt(secondSplit[6]);
		displayColor = Color.getColor(secondSplit[7]);
		titel = secondSplit[8];
		status = secondSplit[9];
		
		loadedTime = System.currentTimeMillis();
		
	}

	public boolean isStillUpToDate() {
		return this.loadedTime <= System.currentTimeMillis()+(ProfileHandler.lifeTimeOfProfileData_sek*1000);
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
