package org.kondle.httpserver.main.threads.basicthreads;


import java.net.ServerSocket;

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

    private static ServerSocket basicSocket;
    private static ServerSocket sslSocket;



    @Override
    public void run()
    {

    }

}
