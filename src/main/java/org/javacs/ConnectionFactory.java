package org.javacs;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.Channels;
import java.util.concurrent.ExecutionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Provides connection streams for LSP
 */
public class ConnectionFactory {
	
	public static final String HOST = "jls.client.host";
	public static final String PORT = "jls.client.port";
	
	public static final String DEFAULT_HOST = "localhost";
	
	public static interface ConnectionProvider {
		
		InputStream getInputStream() throws Exception;
		OutputStream getOutputStream() throws Exception;
		void exit() throws Exception;
	}
	
	/**
	 * A standard connection. Using {@code System.in} and {@code System.out}.
	 */
	public static class StandardConnectionProvider implements ConnectionProvider {
		
		@Override
		public InputStream getInputStream() throws Exception{
			return System.in;
		}

		@Override
		public OutputStream getOutputStream() throws Exception{
			return System.out;
		}
		
		@Override
		public void exit() {}
	}
	
	/**
	 * A socket connection. Connects to the provided host and port
	 */
	public static class SocketConnectionProvider implements ConnectionProvider {
		
		private final AsynchronousSocketChannel server;
		
		public SocketConnectionProvider (String host, int port) throws UnknownHostException, IOException, InterruptedException, ExecutionException {
			this.server = AsynchronousSocketChannel.open();
			this.server.connect(new InetSocketAddress(host, port)).get();
		}
		
		@Override
		public InputStream getInputStream() throws IOException {
			return server != null ? Channels.newInputStream( server ) : null;
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			return server != null ? Channels.newOutputStream( server ) : null;
		}
		
		@Override
		public void exit() throws IOException {
			server.close();
		}
	}
	
	
	public static ConnectionProvider getConnectionProvider() throws NumberFormatException, UnknownHostException, IOException, InterruptedException, ExecutionException {
		var host = System.getProperty(HOST, DEFAULT_HOST);
		var port = System.getProperty(PORT);
		
		if(port == null)
			return new StandardConnectionProvider();
			
		return new SocketConnectionProvider(host, Integer.valueOf(port));
	}
}