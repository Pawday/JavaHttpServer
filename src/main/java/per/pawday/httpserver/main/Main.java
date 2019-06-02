package per.pawday.httpserver.main;


import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        File routerFile = new File("router.json");
        if ( !routerFile.exists() )
        {
            if (! routerFile.canWrite())
            {

            }
        }
    }
}
