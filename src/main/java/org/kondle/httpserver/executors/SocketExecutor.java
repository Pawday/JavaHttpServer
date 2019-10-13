package org.kondle.httpserver.executors;

import org.kondle.httpserver.containers.SitesRoutersContainer;
import org.kondle.httpserver.http.exceptions.HttpException;
import org.kondle.httpserver.http.implementations.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketExecutor
{
    public static void execute(Socket s) throws IOException, HttpException {
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        HttpRequest request = new HttpRequest(is);

    }
}
