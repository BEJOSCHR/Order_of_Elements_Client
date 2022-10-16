package de.bejoschgaming.orderofelements.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.Unit;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitCategory;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitHandler;
import de.bejoschgaming.orderofelements.gamesystem.unitsystem.UnitTargetPattern;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;
import de.bejoschgaming.orderofelements.profilesystem.ClientData;
import de.bejoschgaming.orderofelements.profilesystem.LoadedProfile;
import de.bejoschgaming.orderofelements.profilesystem.ProfileHandler;

public class ConsoleHandler {

	private static String prefix = "[OOE_C] ";
	public static int focusDebugID = -1;
	public static Timer consoleInputScanner = new Timer();
	
//==========================================================================================================
	/**
	 * Print simple Message in the console
	 * @param gameID - int - (-1) its non specific debug - (0) means its a package debug - else a gameID related debug
	 * @param text - String - The message to print
	 * @param prefix - boolean - Enable/Disable Prefix
	 */
	public static void printMessageInConsole(String text, boolean prefix) { printMessageInConsole(-1, text, prefix); }
	public static void printMessageInConsole(int gameID, String text, boolean prefix) {
		
		if(gameID == focusDebugID) {
			//RIGHT MODE
			if(prefix == true) {
				System.out.println(ConsoleHandler.prefix+text);
			}else {
				System.out.println(text);
			}
		}
		
	}
//==========================================================================================================
	/**
	 * Print an empty line
	 */
	public static void printBlankLineInConsole() { printBlankLineInConsole(-1); }
	public static void printBlankLineInConsole(int gameID) {
		if(gameID == focusDebugID) {
			System.out.println("");
		}
	}
	
//==========================================================================================================
	/**
	 * Start Scanner which checks for new user console input
	 * @see consoleInputScanner
	 */
	public static void startUserInputScanner() {
		
		consoleInputScanner.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				try {
					@SuppressWarnings("resource") //DARF NICHT GESCHLOSSEN WERDEN!
					Scanner consoleInput = new Scanner(System.in);
					
					if(consoleInput.hasNextLine()) {
						
						if(focusDebugID != -1) {
							//GAME SESSION
							int oldID = focusDebugID;
							focusDebugID = -1; //LEAVE SESSION
							printBlankLineInConsole();
							if(oldID == 0) {
								//PACKETS
								printMessageInConsole("Terminated packets session", true);
							}else {
								//GAME
								printMessageInConsole("Terminated game session for game ["+oldID+"]", true);
							}
							printBlankLineInConsole();
						}else {
							
							String input = consoleInput.nextLine();
							List<String> inputs = ArrayFromPattern(input.split(" "));
							String keyWord = inputs.get(0);
							
							switch(keyWord) { 
							case "/help":
								sendCommand_help(inputs);
								break;
							case "/packets":
								sendCommand_packets(inputs);
								break;
							case "/profiles":
								sendCommand_profiles(inputs);
								break;
							case "/units":
								sendCommand_units(inputs);
								break;
							case "/overview":
								sendCommand_overview(inputs);
								break;
							case "/exit":
								sendCommand_exit(inputs);
								break;
							default:
								printMessageInConsole("Unknown input! Use '/help' for details...", true);
								break;
							}
							
						}
						
					}
				}catch(Exception error) {
					error.printStackTrace();
					printMessageInConsole("Can't handle this input! [Error]", true);
				}
				
			}
		}, 0, 60);
		
		printMessageInConsole("ConsoleInputScanner started!", true);
		
	}

	/**
	 * Convert an String Pattern into an String ArrayList
	 * @param Input - String[] - The String Pattern shold be converted
	 * @return Output - ArrayList(String) - The converted ArrayList
	 */
	public static List<String> ArrayFromPattern(String[] Input) {
		List<String> Output = new ArrayList<String>();
		for(String Inhalt : Input) {
			Output.add(Inhalt);
		}
		return Output;
	}
	
//==========================================================================================================
	/**
	 * Stop Scanner which checks for new user console input
	 * @see consoleInputScanner
	 */
	public static void stopUserInputScanner() {
		
		consoleInputScanner.cancel();
		consoleInputScanner.purge();
		
		printMessageInConsole("ConsoleInputScanner stopped!", true);
		
	}
	
	private static void sendCommand_help(List<String> inputs) {
		
		printMessageInConsole("Choose one of these commands:", true);
		printMessageInConsole("'/packets ' - Join the packet session so you see the traffic of packets", true);
		printMessageInConsole("'/profiles ' - See all loaded profiles and there TTL", true);
		printMessageInConsole("'/units [category|pattern] ' - Infos about units, unitCategories and unitTargetPattern", true);
		printMessageInConsole("'/overview ' - Gives a general overview about everything interesting", true);
		printMessageInConsole("'/exit ' - Stoppes the whole program", true);
		
	}
	
	private static void sendCommand_overview(List<String> inputs) {
		
		printMessageInConsole("Running OrderOfElements-Client", true);
		printMessageInConsole("Logged in as "+ClientData.getThisProfile().getPlayerID()+" - "+ClientData.getThisProfile().getName(), true);
		printMessageInConsole("Connected to server: "+ServerConnection.connectedToServer, true);
		printMessageInConsole("Send packets: "+ServerConnection.sendPackets.size(), true);
		printMessageInConsole("Running game: "+-1, true);
		
	}
	
	private static void sendCommand_units(List<String> inputs) {
		
		if(inputs.size() >= 2) {
			if(inputs.get(1).equalsIgnoreCase("category") || inputs.get(1).equalsIgnoreCase("categories")) {
				List<UnitCategory> categories = UnitHandler.getUnitCategories();
				printMessageInConsole("UnitsCategory overview (Total "+categories.size()+"): ", true);
				for(int i = 0 ; i < categories.size() ; i++) {
					UnitCategory category = categories.get(i);
					printMessageInConsole((i+1)+". Category: "+category.getCategory()+" - Description: "+category.getDescription().substring(0, 70)+"...", true);
				}
			}else if(inputs.get(1).equalsIgnoreCase("pattern")) {
				List<UnitTargetPattern> targetPatterns = UnitHandler.getUnitTargetPattern();
				printMessageInConsole("UnitsTargetPattern overview (Total "+targetPatterns.size()+"): ", true);
				for(int i = 0 ; i < targetPatterns.size() ; i++) {
					UnitTargetPattern targetPattern = targetPatterns.get(i);
					printMessageInConsole((i+1)+". Pattern: "+targetPattern.getPattern()+" - RelativeTargetPoints: "+targetPattern.getTargetRelatives().size()+" - Icon: "+(targetPattern.getIcon() != null ? "loaded" : "null"), true);
				}
			}else {
				printMessageInConsole("'/units' or '/units [category|pattern]' ", true);
			}
		}else {
			printMessageInConsole("For more infos: /units [category|pattern] ", true);
			List<Unit> units = UnitHandler.getUnitTemplates();
			printMessageInConsole("Units overview (Total "+units.size()+"): ", true);
			for(int i = 0 ; i < units.size() ; i++) {
				Unit unit = units.get(i);
				printMessageInConsole((i+1)+". ID: "+unit.getId()+" - Name: "+unit.getName()+" - Category: "+unit.getCategory().getCategory()+" ("+unit.getType_attack().getPattern()+"|"+unit.getType_move().getPattern()+"|"+unit.getType_aura().getPattern()+")", true);
			}
		}
		
	}
	
	private static void sendCommand_packets(List<String> inputs) {
		
		printBlankLineInConsole();
		printMessageInConsole("Joined packets session", true);
		printBlankLineInConsole();
		focusDebugID = 0;
		
	}
	
	private static void sendCommand_profiles(List<String> inputs) {
		
		if(ClientData.getThisProfile() != null) {
			LoadedProfile profile = ClientData.getThisProfile();
			ConsoleHandler.printMessageInConsole("This clientData: "+profile.getPlayerID()+"-"+profile.getName()+"-"+profile.getStatus()+" ("+profile.isUpToDate()+")", true);
		}else {
			ConsoleHandler.printMessageInConsole("This Client has not received his data yet!", true);
		}
		
		if(ProfileHandler.getLoadedProfileCount() == 0) {
			ConsoleHandler.printMessageInConsole("There are no other profiles loaded yet!", true);
		}else {
			for(int i = 0 ; i < ProfileHandler.getLoadedProfileCount() ; i++) {
				LoadedProfile profile = ProfileHandler.getProfileByListPos(i);
				ConsoleHandler.printMessageInConsole((i+1)+". "+profile.getPlayerID()+"-"+profile.getName()+"-"+profile.getStatus()+" ("+profile.isUpToDate()+"-"+profile.getOutdatedMillis()+"<"+ProfileHandler.lifeTimeOfProfileData_sek*1000+")", true);
			}
		}
		
	}
	
	private static void sendCommand_exit(List<String> inputs) {
		
		OOE_Main_Client.terminateProgramm(false);
		
	}
	
}
