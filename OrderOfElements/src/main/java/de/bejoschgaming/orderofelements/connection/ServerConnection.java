package de.bejoschgaming.orderofelements.connection;

import java.awt.Color;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.animationsystem.animations.MenuBookAnimation;
import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.filesystem.FileHandler;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_2Login;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_3Menu;
import de.bejoschgaming.orderofelements.maasystem.MouseActionArea;
import de.bejoschgaming.orderofelements.maasystem.MouseActionAreaType;
import de.bejoschgaming.orderofelements.mwsystem.MultiWindowHandler;
import de.bejoschgaming.orderofelements.mwsystem.mws.MW_InfoWindow;
import de.bejoschgaming.orderofelements.profilesystem.ClientData;
import de.bejoschgaming.orderofelements.profilesystem.ProfileHandler;

public class ServerConnection {

	private static String hostname = FileHandler.readOutData(FileHandler.file_Settings, "CONNECTION_IP"); // "ipcwup.no-ip.biz"
	private static int port = Integer.parseInt(FileHandler.readOutData(FileHandler.file_Settings, "CONNECTION_Port"));
	private static int connectionTimeout = Integer.parseInt(FileHandler.readOutData(FileHandler.file_Settings, "CONNECTION_Idletime"))*1000; //IN MS
	public static int maxConnectionTries = 2;
	public static int connectionTry = 0;
	
	private static NioSocketConnector socketConnector;
	private static IoSession serverConnection = null;
	public static boolean connectedToServer = false;
	public static boolean disconnecting = false;
	
	public static List<String> sendPackets = new ArrayList<>();
	
	public static final String packetDivider = FileHandler.readOutData(FileHandler.file_Settings, "CONNECTION_Packetdivider");
	
//	https://mina.apache.org/mina-project/userguide/ch2-basics/ch2.2-sample-tcp-server.html
	
	public static void connectToServer() {
		
		ConsoleHandler.printMessageInConsole("Connecting to server... (Max. tries: "+maxConnectionTries+")", true);
		connectedToServer = false;
		connectionTry = 0;
		
		socketConnector = new NioSocketConnector();
    	socketConnector.setConnectTimeoutMillis(connectionTimeout);
//    	socketConnector.getFilterChain().addLast("logger", new LoggingFilter());
    	socketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
    	socketConnector.setHandler(new ConnectionEventHandler());
		
    	new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				connectionTry++;
				
				try {
		        	ConnectFuture future = socketConnector.connect(new InetSocketAddress(hostname, port));
		            future.awaitUninterruptibly();
		            serverConnection = future.getSession();
					this.cancel();
				}catch (RuntimeIoException error) {
//					error.printStackTrace();
					ConsoleHandler.printMessageInConsole("Connecting to server failed! (Try: "+connectionTry+"/"+maxConnectionTries+") ["+error.getMessage()+"]", true);
				}
				
				if(connectionTry == maxConnectionTries) {
					//NOT CONNECTED
					ConsoleHandler.printMessageInConsole("Connecting to server ended with no result! (Try: "+maxConnectionTries+"/"+maxConnectionTries+" failed)", true);
					TextFieldHandler.hideTextField(TextFieldHandler.LOGIN_Name);
					TextFieldHandler.hideTextField(TextFieldHandler.LOGIN_Password);
					String[] message = {"No serverconnection could be established!", "Please try again later", "", "For more information take a look at the 'BejoschGaming' discord channel"};
					MouseActionArea maa = new MouseActionArea(120, 50, MouseActionAreaType.MW_InfoWindow_, "Discord ", 22, Color.WHITE, Color.ORANGE, true) {
						public void performAction_LEFT_RELEASE() {
							FileHandler.openBrowserLink("https://discord.gg/nBxwBnNGVz");
						};
					};
					MultiWindowHandler.openMW(new MW_InfoWindow(message, FontHandler.getFont(FontHandler.medievalSharp_regular, 22), Color.WHITE, Color.DARK_GRAY, maa));
					this.cancel();
					return;
				}
				
			}
		}, 100, 3000);
        
	}
	
	public static void sendPacket(int signal, String message) {
		
		String messageString = signal+packetDivider+message;
		serverConnection.write(messageString);
		sendPackets.add(messageString);
		
	}
	
	public static void handlePacketFromServer(int signal, String message) {
		
		ConsoleHandler.printMessageInConsole(0, "[Received packet] "+signal+" - "+message, true);
		String[] data = message.split(";");
		
		//CLIENT recieve
		switch (signal) {
		case 100:
			//CORRECT LOGIN
			//Syntax: 100-PlayerID;Successfully logged in!
			ClientData.setThisID(Integer.parseInt(data[0]));
			TextFieldHandler.hideTextField(TextFieldHandler.LOGIN_Name);
			TextFieldHandler.hideTextField(TextFieldHandler.LOGIN_Password);
			AnimationHandler.startAnimation(new MenuBookAnimation(true) {
				
				@Override
				protected void halfTimeAction() {
					
					GraphicsHandler.switchTo(DrawState.MENU);
					
				}
				
			});
			ConsoleHandler.printMessageInConsole("Valid login! ClientID: "+ClientData.getThisID(), true);
			break;
		case 101:
			//INVALID LOGIN
			//Syntax: 101-Cause
			String invalidCause = message;
			Draw_2Login.loginErrorCause = invalidCause;
			ConsoleHandler.printMessageInConsole("Login invalid! Cause: "+invalidCause, true);
			break;
		case 180:
			//PATCHNOTES
			//Syntax: 180-PatchnotesData
			String patchnotesData = message;
			Draw_3Menu.patchnotesData = patchnotesData;
			ConsoleHandler.printMessageInConsole("Set patchnotes: "+patchnotesData, true);
			break;
		case 200:
			//RECEIVE OWN CLIENT DATA
			//Syntax: 200-PlayerStatsSyntax
			ClientData.setThisProfile(message);
			ConsoleHandler.printMessageInConsole("Set clientData: "+message, true);
			break;
		case 201:
			//RECEIVE OTHER CLIENT DATA
			//Syntax: 201-PlayerStatsSyntax
			ProfileHandler.updateProfileData(message);
			break;
		case 205:
			//ONLINE INFO FOR FRIEND 
			//Syntax: 205-PlayerID
			int friendOnlineID = Integer.parseInt(message);
			ClientData.updateFriend(friendOnlineID, true);
			break;
		case 206:
			//OFFLINE INFO FOR FRIEND
			//Syntax: 206-PlayerID
			int friendOfflineID = Integer.parseInt(message);
			ClientData.updateFriend(friendOfflineID, false);
			break;
		case 207:
			//STATUS CHANGE FOR FRIEND
			//Syntax: 206-PlayerID;Status
			int friendStatusID = Integer.parseInt(data[0]);
			String newStatus = data[1];
			ProfileHandler.getProfile(friendStatusID).updateStatus(newStatus);
			break;
		case 241:
			//FRIENDREQUEST ADD
			//SEND: 241-playerTargetName
			//SYNTAX: 241-playerRequestID;playerRequestName
			int playerWhoWantsToBeYourFriend_ID = Integer.parseInt(data[0]);
			String playerWhoWantsToBeYourFriend_Name = data[1];
			ClientData.addFriendRequest(playerWhoWantsToBeYourFriend_ID, playerWhoWantsToBeYourFriend_Name);
			break;
		case 242:
			//ONLY SEND: FRIENDREQUEST ACCEPT
			//SYNTAX: 242-acceptedID
			break;
		case 243:
			//ONLY SEND: FRIENDREQUEST DECLINE
			//SYNTAX: 243-deniedID
			break;
		case 244:
			//FRIEND REMOVE
			//SYNTAX: 244-removeFriendID
			int removedFriendID = Integer.parseInt(message);
			ClientData.removeFriend(removedFriendID);
			break;
		}
		
	}
	
	public static boolean checkInputForServerUse(String input) {
		
		if(input.contains(";") || input.contains(":") || input.contains("-") || input.contains("(") || input.contains(")") || input.contains("[") || input.contains("]")) {
			return false;
		}
		
		return true;
		
	}
	
	public static void disconnectFromServer() {
		
		if(disconnecting == true) {
			ConsoleHandler.printMessageInConsole("Already disconnected from server!", true);
			return;
		}
		disconnecting = true;
		
		if(connectedToServer == true) {
			connectedToServer = false;
			serverConnection.closeNow();
			socketConnector.dispose();
			try {
				Thread.sleep(1000*1);
			} catch (InterruptedException error1) {
				error1.printStackTrace();
				ConsoleHandler.printMessageInConsole("Thread interruped failed!", true);
			}
		}else { 
			ConsoleHandler.printMessageInConsole("Can't disconnect, no server connection was established!", true);
		}
		
	}
	
}
