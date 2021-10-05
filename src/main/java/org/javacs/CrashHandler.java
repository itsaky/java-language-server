package org.javacs;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.eclipse.lsp4j.services.LanguageClient;

public class CrashHandler {
	
	private static LanguageClient client;
	
	public static void init(LanguageClient reciever) {
		client = reciever;
		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
			logCrash(throwable);
		});
	}
	
	public static void logCrash (Throwable th) {
	    if(client == null) return;
    	var params = new MessageParams();
    	params.setMessage(String.format("Java language server crashed. Stacktrace:\n%s", createStacktrace(th)));
    	params.setType(MessageType.Error);
    	client.logMessage(params);
    }
    
    public static String createStacktrace(Throwable th) {
    	var r = new StringWriter();
    	th.printStackTrace(new PrintWriter(r));
		return r.toString();
	}
}