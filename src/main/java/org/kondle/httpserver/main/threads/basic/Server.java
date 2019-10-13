package org.kondle.httpserver.main.threads.basic;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.kondle.httpserver.configs.routs.SitesRoute;
import org.kondle.httpserver.containers.ServerSocketListenerThreadsContainer;
import org.kondle.httpserver.containers.ServerSocketsContainer;
import org.kondle.httpserver.containers.SitesRoutersContainer;
import org.kondle.httpserver.log.Logger;
import org.kondle.httpserver.main.threads.secondary.ServerSocketListenerThread;
import org.kondle.httpserver.tools.console.ConsoleColors;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The {@code: Server} class is the first main stream of the http server.
 * This flow provides communication between the running server and the server administrator.
 */
public class Server extends Thread
{

    private Server(){}
    private static Server server = new Server();

    private static SitesRoute[] getSitesRoutes(JSONArray routersJSONArray) throws FileNotFoundException
    {
        SitesRoute[] routes  = new SitesRoute[routersJSONArray.length()];

        for (int i = 0; i < routersJSONArray.length(); i++)
        {
            routes[i] = new SitesRoute((JSONObject) routersJSONArray.get(i));
        }
        return routes;
    }

    public static Server getInstance()
    {
        return server;
    }






    @Override
    public void run()
    {
        try
        {
            FileInputStream stream = new FileInputStream("SitesRouter.json");
            SitesRoute[] routers = getSitesRoutes(new JSONArray(new JSONTokener(stream)));
            stream.close();

            SitesRoutersContainer.sitesRoutesSet.addAll(Arrays.asList(routers));

            Iterator<SitesRoute> sitesRouteIterator = SitesRoutersContainer.sitesRoutesSet.iterator();

            while (sitesRouteIterator.hasNext())
            {
                SitesRoute route = sitesRouteIterator.next();
                File baseDirectory = route.getBaseDirectory();
                {
                    boolean hasRouterJSONFile = false;
                    File[] files = baseDirectory.listFiles();
                    for (File f : files)
                    {
                        if (f.getName().equals("Router.json"))
                        {
                            hasRouterJSONFile = true;
                            break;
                        }
                    }

                    if (!hasRouterJSONFile)
                    {
                        try
                        (
                            InputStream res = ClassLoader.getSystemResourceAsStream("resources/examples/Router.json");
                            FileOutputStream routerFileStream = new FileOutputStream(new File(baseDirectory.getAbsolutePath() + File.separator + "Router.json"));
                        )
                        {
                            int b;
                            while ((b = res.read()) != -1)
                            {
                                routerFileStream.write(b);
                            }
                            System.out.println(ConsoleColors.GREEN + "Edit the file Router.json in " + baseDirectory.getAbsolutePath() + ConsoleColors.RESET);
                        }
                    }
                }


                {
                    boolean isFreePort = true;
                    for (ServerSocket socket : ServerSocketsContainer.sockets)
                    {
                        if (socket.getLocalPort() == route.getPort())
                        {
                            isFreePort = false;
                            break;
                        }
                    }


                    if (isFreePort) ServerSocketsContainer.sockets.add(new ServerSocket(route.getPort()));
                }


            }

            for (ServerSocket s : ServerSocketsContainer.sockets)
            {
                ServerSocketListenerThread thread = new ServerSocketListenerThread(s);
                ServerSocketListenerThreadsContainer.serverSocketListenerThreads.add(thread);
                thread.setName("SocketListener: Port " + s.getLocalPort());
                thread.start();
            }
        }
        catch (Exception e)
        {
            Logger.log(e);
        }
    }

}
