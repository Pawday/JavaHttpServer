package org.kondle.httpserver.main.threads.basic;

/**
 * The {@code: Control} class is the main stream of the http server.
 * In here server modules are loaded and started according the file SitesRouter.json
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

    }

}
