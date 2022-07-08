package de.bejoschgaming.orderofelements.graphics.drawparts;

import java.awt.Color;
import java.awt.Graphics;

import de.bejoschgaming.orderofelements.componentssystem.TextFieldHandler;
import de.bejoschgaming.orderofelements.connection.ServerConnection;
import de.bejoschgaming.orderofelements.fontsystem.FontHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.imagesystem.ImageHandler;
import de.bejoschgaming.orderofelements.maa.MouseActionArea;
import de.bejoschgaming.orderofelements.maa.MouseActionAreaType;
import de.bejoschgaming.orderofelements.main.OOE_Main_Client;

public class Draw_2Login {
	
	public static String loginErrorCause = "";
	
	public static void initMAAs() {
		
		new MouseActionArea(1130, 595, 160, 60, MouseActionAreaType.LOGIN_Login, "Login", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.LOGIN && ServerConnection.connectedToServer;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//EXECUTE LOGIN
				loginErrorCause = "";
				String username = TextFieldHandler.LOGIN_Name.getText();
				String usernameCheck = checkForValidUsername(username);
				if(usernameCheck == null) {
					//VALID
					String password = TextFieldHandler.LOGIN_Password.getText();
					String passwordCheck = checkForValidPassword(password);
					if(passwordCheck == null) {
						//VALID
						ServerConnection.sendPacket(100, username+";"+password); //LOGIN 100
					}else {
						Draw_2Login.loginErrorCause = passwordCheck;
					}
				}else {
					Draw_2Login.loginErrorCause = usernameCheck;
				}
			}
		};
		new MouseActionArea(1320, 595, 160, 60, MouseActionAreaType.LOGIN_Register, "Register", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.LOGIN && ServerConnection.connectedToServer;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				//EXECUTE REGISTER
				loginErrorCause = "";
				String username = TextFieldHandler.LOGIN_Name.getText();
				String usernameCheck = checkForValidUsername(username);
				if(usernameCheck == null) {
					//VALID
					String password = TextFieldHandler.LOGIN_Password.getText();
					String passwordCheck = checkForValidPassword(password);
					if(passwordCheck == null) {
						//VALID
						ServerConnection.sendPacket(101, username+";"+password); //REGISTER 101
					}else {
						Draw_2Login.loginErrorCause = passwordCheck;
					}
				}else {
					Draw_2Login.loginErrorCause = usernameCheck;
				}
			}
		};
		new MouseActionArea(1225, 680, 160, 60, MouseActionAreaType.LOGIN_Cancle, "Exit", 22, Color.DARK_GRAY, Color.BLACK, true, false) {
			@Override
			public boolean isActiv() {
				return GraphicsHandler.getDrawState() == DrawState.LOGIN;
			}
			@Override
			public void performAction_LEFT_RELEASE() {
				OOE_Main_Client.terminateProgramm(true);
			}
		};
		
	}
	
	public static void draw(Graphics g) {
			
		//BACKGROUND
		if(Draw_3Menu.backGroundAnimationFrame != null) {
			int backgroundFrame = Draw_3Menu.backGroundAnimationFrame.getValue();
			g.drawImage(ImageHandler.menu_natur[backgroundFrame], 0, 0, null);
		}
		
		g.drawImage(ImageHandler.menu_book[0], 0, 0, null);
		
		//DRAW LEFT PAGE
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 50), "Order Of Elements", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*3)/7);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_regular, 26), "By BejoschGaming", (GraphicsHandler.getWidth()*32)/100, (GraphicsHandler.getHeight()*4)/7);
		
		//DRAW RIGHT PAGE
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_bold, 42), "Login", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*3)/10);
		GraphicsHandler.drawCentralisedText(g, Color.RED, FontHandler.getFont(FontHandler.medievalSharp_regular, 20), loginErrorCause, (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*35)/99);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_regular, 18), "Name", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*38)/99);
		GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_regular, 18), "Password", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*46)/99);
		if(ServerConnection.connectedToServer == false && ServerConnection.connectionTry == ServerConnection.maxConnectionTries) {
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_regular, 30), "Can't connect to server!", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*4)/7);
		}else if(ServerConnection.connectedToServer == false) {
			GraphicsHandler.drawCentralisedText(g, Color.BLACK, FontHandler.getFont(FontHandler.bridgnorth_regular, 30), "Connecting to server", (GraphicsHandler.getWidth()*68)/100, (GraphicsHandler.getHeight()*4)/7);
		}
		
	}
	
	public static String checkForValidUsername(String username) {
		
		if(username.length() < 4) {
			return "The username is to short!";
		}else if(username.length() > 12) {
			return "The username is to long!";
		}else if(username.contains(" ") || ServerConnection.checkInputForServerUse(username) == false) {
			return "The username is invalid!";
		}
		
		return null;

	}
	
	public static String checkForValidPassword(String password) {
		
		if(password.length() < 6) {
			return "The password is to short!";
		}else if(password.length() > 20) {
			return "The password is to long!";
		}else if(password.contains(" ") || ServerConnection.checkInputForServerUse(password) == false) {
			return "The password is invalid!";
		}
		
		return null;

	}

}
