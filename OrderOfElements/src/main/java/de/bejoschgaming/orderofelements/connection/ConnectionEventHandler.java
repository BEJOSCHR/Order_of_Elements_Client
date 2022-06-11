package de.bejoschgaming.orderofelements.connection;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import de.bejoschgaming.orderofelements.animationsystem.AnimationHandler;
import de.bejoschgaming.orderofelements.debug.ConsoleHandler;
import de.bejoschgaming.orderofelements.graphics.DrawState;
import de.bejoschgaming.orderofelements.graphics.GraphicsHandler;

public class ConnectionEventHandler extends IoHandlerAdapter {

	@Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        
//		cause.printStackTrace();
		
    }
	
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		
		ConsoleHandler.printMessageInConsole("Connected to server "+session.getRemoteAddress(), true);
		ServerConnection.connectedToServer = true;
		AnimationHandler.updateLoadingMessage("Successfully connect to server!");
		GraphicsHandler.switchTo(DrawState.LOGIN);
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				AnimationHandler.stopLoadingAnimation();
			}
		}, (int) (1000*2.0));
		
	}
	
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		
		ConsoleHandler.printMessageInConsole("Disconnected from server "+session.getRemoteAddress(), true);
		ServerConnection.connectedToServer = false;
		
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
