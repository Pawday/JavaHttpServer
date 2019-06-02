package per.pawday.httpserver.main;


import per.pawday.httpserver.tools.console.ConsoleColors;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Main
{
    public static void main(String[] args)
    {
        File routerFile = new File("router.json");
        if ( !routerFile.exists() )
        {
            if (routerFile.canWrite() && routerFile.canRead())
            {
                System.out.print(ConsoleColors.RED);
                System.out.println("Permission denied: no permission to create and read file: \"router.json.\"");
                System.out.print(ConsoleColors.RESET);
                System.exit(-1);
            }
            else
            {
                try(InputStreamReader stream = new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("examples/router.json")), StandardCharsets.UTF_8);FileOutputStream fileOutputStream = new FileOutputStream(routerFile))
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
                    System.out.println("Error: error creating file \"router.json.\"");
                    System.out.print(ConsoleColors.RESET);
                    System.exit(-1);
                }
            }
        }
    }
}
