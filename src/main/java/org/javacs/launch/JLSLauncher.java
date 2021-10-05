package org.javacs.launch;

import java.io.InputStream;
import java.io.OutputStream;

import com.itsaky.lsp.services.IDELanguageClient;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.javacs.services.JavaLanguageServer;

public class JLSLauncher {
	
	public static Launcher<IDELanguageClient> createServerLauncher (JavaLanguageServer server, InputStream in, OutputStream out) {
		return
			 new Launcher.Builder<IDELanguageClient>()
				.setLocalService(server)
				.setRemoteInterface(IDELanguageClient.class)
				.setInput(in)
				.setOutput(out)
				.create();
	}
	
}