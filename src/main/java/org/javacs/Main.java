package org.javacs;

import java.util.Arrays;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;

public class Main {
	
	private static Future<Void> listening;
	
	private static final Logger LOG = Logger.getLogger("main");
	
	public static void setRootFormat() {
		var root = Logger.getLogger("");

		for (var h : root.getHandlers()) {
			h.setFormatter(new LogFormat());
		}
	}

	public static void main(String[] args) {
		var stream = Arrays.stream(args);
		boolean quiet = stream.anyMatch("--quiet"::equals);

		if (quiet) {
			LOG.setLevel(Level.OFF);
		}

		try {
			setRootFormat();
			LOG.info("Launching server");
			
			var languageServer = new JavaLanguageServer();
			var provider = ConnectionFactory.getConnectionProvider();
			Launcher<LanguageClient> server = LSPLauncher.createServerLauncher(languageServer, provider.getInputStream(), provider.getOutputStream());
			listening = server.startListening();
			var client = server.getRemoteProxy();
			languageServer.connect(client);
			
			LOG.info("Client is now connected to server. Initializing CrashHandler...");
			CrashHandler.init(client);
			LOG.info("Logs will now be sent to client when JLS crashes");
			
			LOG.info("Server is now listening");
			
			listening.get();
			
			provider.exit();
			
			LOG.info("Server disconnected");
			
		} catch (Throwable t) {
			LOG.log(Level.SEVERE, t.getMessage(), t);
			System.exit(1);
		}
	}
	
	public static void exit() {
		if(listening != null) {
			listening.cancel(true);
		}
	}
}
