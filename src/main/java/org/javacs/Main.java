package org.javacs;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.javacs.lsp.*;
import java.io.*;
import java.net.*;

public class Main {
    private static final Logger LOG = Logger.getLogger("main");
    public static final int PORT = 5443;
    private static Socket client;

    public static void setRootFormat() {
        var root = Logger.getLogger("");

        for (var h : root.getHandlers()) {
            h.setFormatter(new LogFormat());
        }
    }

    public static void main(String[] args) {
        boolean quiet = Arrays.stream(args).anyMatch("--quiet"::equals);

        if (quiet) {
            LOG.setLevel(Level.OFF);
        }

        try {
            // Logger.getLogger("").addHandler(new FileHandler("javacs.%u.log", false));
            setRootFormat();
            ServerSocket server = new ServerSocket(PORT);
            client = server.accept();
//          final DataInputStream in = new DataInputStream(
//                                          new BufferedInputStream(
//                                            client.getInputStream()));
//            final DataOutputStream out = new DataOutputStream(
//                                            new BufferedOutputStream(
//                                              client.getOutputStream()));
          InputStream in = client.getInputStream();
          OutputStream out = client.getOutputStream();
          LSP.connect(JavaLanguageServer::new, in, out);
        } catch (Throwable t) {
            LOG.log(Level.SEVERE, t.getMessage(), t);
            System.exit(1);
        }
    }
}
