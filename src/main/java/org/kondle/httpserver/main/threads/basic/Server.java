package org.kondle.httpserver.main.threads.basic;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.kondle.httpserver.configs.routs.SitesRoute;
import org.kondle.httpserver.tools.console.ConsoleColors;

import java.io.*;
import java.net.ServerSocket;
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

    private static HashSet<SitesRoute> sitesRoutesSet = new HashSet<>();

    private static HashSet<ServerSocket> sockets = new HashSet<>();

    @Override
    public void run()
    {
        try
        {
            FileInputStream stream = new FileInputStream("SitesRouter.json");
            SitesRoute[] routers = getSitesRoutes(new JSONArray(new JSONTokener(stream)));
            stream.close();

            sitesRoutesSet.addAll(Arrays.asList(routers));

            Iterator<SitesRoute> sitesRouteIterator = sitesRoutesSet.iterator();

            while (sitesRouteIterator.hasNext())
            {
                SitesRoute route = sitesRouteIterator.next();
                File baseDirectory = route.getBaseDirectory();
                {
                    boolean hasRouterJSONFile = false;
                    File[] files = baseDirectory.listFiles();
                    for (File f : files)
                    {
                        if (f.getName().equals("Router.JSON"))
                        {
                            hasRouterJSONFile = true;
                            break;
                        }
                    }

                    if (!hasRouterJSONFile)
                    {
                        try
                        (
                            InputStream res = ClassLoader.getSystemResourceAsStream("examples/Router.json");
                            FileOutputStream routerFileStream = new FileOutputStream(new File(baseDirectory.getAbsolutePath() + File.separator + "Router.json"));
                        )
                        {
                            int b;
                            while ((b = res.read()) != -1)
                            {
                                routerFileStream.write(b);
                            }
                            System.out.println(ConsoleColors.GREEN + "Edit the file SitesRouter.json in " + baseDirectory.getAbsolutePath() + ConsoleColors.RESET);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(ConsoleColors.RED + e.getMessage());
        }
    }

}
