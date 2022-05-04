package de.bejoschgaming.orderofelements.connection;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import de.bejoschgaming.orderofelements.debug.ConsoleHandler;

public class ServerConnection {

	private static String hostname = "localhost";
	private static int port = 6776;
	private static int connectionTimeout = 1000*8; //IN MS
	private static int maxConnectionTries = 3;
	
	private static NioSocketConnector socketConnector;
	private static IoSession serverConnection = null;
	public static boolean connectedToServer = false;
	
	public static final String packetDivider = "_:_";
	
//	https://mina.apache.org/mina-project/userguide/ch2-basics/ch2.2-sample-tcp-server.html
	
	public static void connectToServer() {
		
		connectedToServer = false;
		
		socketConnector = new NioSocketConnector();
    	socketConnector.setConnectTimeoutMillis(connectionTimeout);
//    	socketConnector.getFilterChain().addLast("logger", new LoggingFilter());
    	socketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
    	socketConnector.setHandler(new ConnectionEventHandler());
		
		for(int tries = 1 ; tries <= maxConnectionTries ; tries++) {
			try {
	        	ConnectFuture future = socketConnector.connect(new InetSocketAddress(hostname, port));
	            future.awaitUninterruptibly();
	            serverConnection = future.getSession();
//				ConsoleHandler.printMessageInConsole("Connected to server!", true);
				break;
			}catch (RuntimeIoException error) {
//				error.printStackTrace();
				ConsoleHandler.printMessageInConsole("Connecting to server failed! (Try: "+tries+"/"+maxConnectionTries+") [\"+error.getMessage()+\"]", true);
				try {
					Thread.sleep(1000*5);
				} catch (InterruptedException error1) {
					error1.printStackTrace();
					ConsoleHandler.printMessageInConsole("Thread interruped failed!", true);
				}
			}
		}
		
		if(connectedToServer == false) {
			//NOT CONNECTED
			ConsoleHandler.printMessageInConsole("Connecting to server ended with no result! (Try: "+maxConnectionTries+"/"+maxConnectionTries+" failed)", true);
		}
		
        
	}
	
	public static void disconnectFromServer() {
		
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
