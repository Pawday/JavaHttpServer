package org.kondle.httpserver.main.threads.basic;


import org.kondle.httpserver.configs.routs.SitesRoute;

import java.net.ServerSocket;
import java.util.HashSet;

/**
 * The {@code: Server} class is the first main stream of the http server.
 * This flow provides communication between the running server and the server administrator.
 */
public class Server extends Thread
{

    private Server(){}
    private static Server server = new Server();

    public static Server getInstance()
    {
        return server;
    }

    private static HashSet<SitesRoute> sitesRoutesSet = new HashSet<>();

    private static HashSet<ServerSocket> sockets = new HashSet<>();

    @Override
    public void run()
    {

    }

}
