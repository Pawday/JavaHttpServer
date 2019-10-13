package org.kondle.httpserver.http.implementations;

import org.kondle.httpserver.http.components.header.HttpHeadersMap;
import org.kondle.httpserver.http.components.reqline.HttpRequestLine;
import org.kondle.httpserver.http.exceptions.HttpException;

import java.io.IOException;
import java.io.InputStream;


public class HttpRequest
{
    public HttpRequestLine requestLine;
    private HttpHeadersMap headers;
    private boolean hasSize;
    private long size;
    private byte[] body;

    public HttpRequest(InputStream inputStream) throws IOException, HttpException
    {
        this.requestLine = new HttpRequestLine(inputStream);
        this.headers = new HttpHeadersMap(inputStream);

        if (this.headers.containsKey("Content-Length"))
        {
            this.hasSize = true;
            this.size = Long.parseLong(this.headers.get("Content-Length"));
        }
        else
        {
            this.size = 0;
            this.hasSize = false;
        }
    }

    public HttpHeadersMap getHeaders()
    {
        return headers;
    }
}
