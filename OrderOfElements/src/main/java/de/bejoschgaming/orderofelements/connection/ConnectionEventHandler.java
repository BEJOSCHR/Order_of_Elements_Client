package de.bejoschgaming.orderofelements.connection;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;
import de.bejoschgaming.orderofelements.graphics.drawparts.Draw_2Login;

public class ConnectionEventHandler extends IoHandlerAdapter {

	@Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        
//		cause.printStackTrace();
		
    }
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		
		ConsoleHandler.printMessageInConsole("Connected to server "+session.getRemoteAddress(), true);
		ServerConnection.connectedToServer = true;
		ServerConnection.disconnecting = false;
		
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		
		ConsoleHandler.printMessageInConsole("Disconnected from server "+session.getRemoteAddress(), true);
		ServerConnection.connectedToServer = false;
		if(ServerConnection.disconnecting == false) {
			//NO PLANNED DISCONNECT
			Draw_2Login.loginErrorCause = "Lost connection to server!";
			GraphicsHandler.switchTo(DrawState.LOGIN);
			ServerConnection.connectToServer();
		}
		
	}
	
    @Override
    public void messageReceived(IoSession session, Object messageReceived) throws Exception {
        
    	String rawMessage = messageReceived.toString();
    	String[] splitMessage = rawMessage.split(ServerConnection.packetDivider);
        int signal = Integer.parseInt(splitMessage[0]);
        String message = splitMessage[1];
        
        ServerConnection.handlePacketFromServer(signal, message);
        
    }
    
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {}
	
}
