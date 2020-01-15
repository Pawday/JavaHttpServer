package org.kondle.httpserver.containers;

import org.kondle.httpserver.main.threads.secondary.ServerSocketListenerThread;

import java.util.ArrayList;
import java.util.Iterator;

public class ServerSocketListenerThreadsContainer
{
    public static ArrayList<ServerSocketListenerThread> serverSocketListenerThreads = new ArrayList<>();
    public static void remove(ServerSocketListenerThread t)
    {
        if (serverSocketListenerThreads.contains(t))
        {
            t.interrupt();
            serverSocketListenerThreads.remove(t);
        }
    }

    public static ServerSocketListenerThread getByPort(int port)
    {
        Iterator<ServerSocketListenerThread> i = serverSocketListenerThreads.iterator();
        ServerSocketListenerThread ret;
        while (i.hasNext())
        {
            ret = i.next();
            if (ret.getPort() == port)
            {
                return ret;
            }
        }

        return null;
    }
}
