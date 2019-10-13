package org.kondle.httpserver.main.threads;

import java.util.Iterator;
import java.util.Set;

public class ThreadManager
{
    private static long mainThreadId;
    private static long controlThreadId;
    private static long serverThreadId;

    private static boolean serverInited = false;
    private static boolean controlInited = false;


    static
    {
        mainThreadId = Thread.currentThread().getId();
    }

    public static long getMainThreadId() { return mainThreadId; }
    public static void setServerThreadId(long id)
    {
        if (Thread.currentThread().getId() == mainThreadId && !serverInited)
        {
            serverThreadId = id;
            serverInited = true;
        }
    }

    public static void setControlThreadId(long id)
    {
        if (Thread.currentThread().getId() == mainThreadId && !controlInited)
        {
            controlThreadId = id;
            controlInited = true;
        }
    }

    public static String[] getThreadNamesList()
    {
        String[] ret = new String[0];
        if (Thread.currentThread().getId() == controlThreadId)
        {
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            ret = new String[threadSet.size()];

            Iterator<Thread> iterator = threadSet.iterator();

            int i = 0;
            while (iterator.hasNext())
            {
                Thread t = iterator.next();
                ret[i] = t.getName() + " (id:" + t.getId() + ")";
                i++;
            }
        }
        return ret;
    }

    public static long getServerThreadId() { return serverThreadId; }
    public static long getControlThreadId() { return controlThreadId; }

    public static void stopById(long id)
    {
        if (Thread.currentThread().getId() == controlThreadId)
        {
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            for (Thread nt : threadSet) if (nt.getId() == id) nt.stop();
        }
    }
}
