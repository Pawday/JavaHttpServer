package org.kondle.httpserver.main.threads.basic;

import org.kondle.httpserver.log.Logger;
import org.kondle.httpserver.main.threads.ThreadManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * The {@code: Control} class is the main stream of the http server.
 * This flow provides communication between the running server and the server administrator.
 */
public class Control extends Thread {

    private Control(){}
    private static final Control control = new Control();

    public static Control getInstance()
    {
        return control;
    }

    @Override
    public void run()
    {
        StringBuilder builder = new StringBuilder();
        InputStreamReader stream = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        while (true)
        {
            builder.delete(0,builder.length());
            int b = 0;
            while (true)
            {
                try
                {
                    if ((b = stream.read()) == '\n') break;
                }
                catch (IOException e)
                {
                    Logger.log(e);
                }

                builder.append((char)b);
            }

            runCommand(builder.toString().split(" "));
        }
    }

    private static void runCommand(String[] s)
    {
        if (s.length != 0)
        {
            switch (s[0])
            {
                case "thread":
                    if (s.length != 1) switch (s[1])
                    {
                        case "list":
                            String[] threadList = ThreadManager.getThreadNamesList();
                            for (int i = 0; i < threadList.length; i++)
                            {
                                System.out.println((i + 1) + ": " + threadList[i]);
                            }
                        break;
                    }
                break;
                case "get":
                    if (s.length != 1) switch (s[1])
                    {
                        case "help":
                            String helpOut =
                                    "get\n" +
                                    "  thread list\n";
                            System.out.println(helpOut);
                            break;
                        case "thread":
                            if (s.length != 2) switch (s[2])
                            {
                                case "list":
                                    String[] threadList = ThreadManager.getThreadNamesList();
                                    for (int i = 0; i < threadList.length; i++)
                                    {
                                        System.out.println((i + 1) + ": " + threadList[i]);
                                    }
                                break;
                            }
                    } else System.out.println("use: \"get help\"");
                break;

                case "stop":
                    System.exit(0);
                break;
            }
        }
    }





}
