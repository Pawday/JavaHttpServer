package org.kondle.httpserver.containers;

import org.kondle.httpserver.configs.routs.SitesRoute;

import java.net.ServerSocket;
import java.util.HashSet;

public class ServerSocketsContainer
{
    public static HashSet<ServerSocket> sockets = new HashSet<>();
}
