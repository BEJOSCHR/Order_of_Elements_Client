package de.bejoschgaming.orderofelements.main;

public class ConsoleHandler {

	public static final String prefix = "[OOE_C] ";
	
	public static void printMessageInConsole(String message, boolean prefix) {
		
		if(prefix) {
			System.out.println(ConsoleHandler.prefix+message);
		}else {
			System.out.println(message);
		}
		
	}

}
