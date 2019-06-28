package org.kondle.httpserver.main;



import org.kondle.httpserver.main.threads.basicthreads.Control;
import org.kondle.httpserver.main.threads.basicthreads.Server;
import org.kondle.httpserver.main.threads.ThreadManager;
import org.kondle.httpserver.tools.console.ConsoleColors;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main
{
    private static long mainThreadId;
    public static long getMainThreadId()
    {
        return mainThreadId;
    }

    public static void main(String[] args)
    {
        mainThreadId = ThreadManager.getMainThreadId();

        File routerFile = new File("SitesRouter.json");
        if ( !routerFile.exists() )
        {
            if (routerFile.canWrite() && routerFile.canRead())
            {
                System.out.print(ConsoleColors.RED);
                System.out.println("Permission denied: no permission to create and read file: \"SitesRouter.json.\"");
                System.out.print(ConsoleColors.RESET);
                System.exit(-1);
            }
            else
            {
                try(InputStreamReader stream = new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("examples/SitesRouter.json")), StandardCharsets.UTF_8);FileOutputStream fileOutputStream = new FileOutputStream(routerFile))
                {
                    routerFile.createNewFile();

                    int b;
                    while ((b = stream.read())!= -1)
                    {
                        fileOutputStream.write((char)b);
                    }
                }
                catch (IOException e)
                {
                    System.out.print(ConsoleColors.RED);
                    System.out.println("Error: error creating file \"SitesRouter.json.\"");
                    System.out.print(ConsoleColors.RESET);
                    System.exit(-1);
                }
            }
        }

        Server server = Server.getInstance();
        Control control = Control.getInstance();

        ThreadManager.setServerThreadId(server.getId());
        ThreadManager.setControlThreadId(control.getId());

        server.start();
        control.start();

    }
}
