package per.pawday.httpserver.main.threads.tools;

public class ThreadManager
{
    private static long mainThreadId;


    static
    {
        mainThreadId = Thread.currentThread().getId();
    }

    public static long getMainThreadId()
    {
        return mainThreadId;
    }


}
