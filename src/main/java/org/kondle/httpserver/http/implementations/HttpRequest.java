package org.kondle.httpserver.http.implementations;

import org.kondle.httpserver.http.components.header.HttpHeadersMap;

import java.io.InputStream;


public class HttpRequest
{

    public HttpRequest(InputStream inputStream)
    {

    }
    private HttpHeadersMap headers;
    private boolean hasSize;
    private long size;


}
