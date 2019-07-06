package org.kondle.httpserver.http.implementations;

import org.kondle.httpserver.http.components.header.HttpHeadersMap;

import java.io.InputStream;

public class HttpResponse extends HttpHeadersMap
{
    private int status = 200;
    private HttpHeadersMap headers = new HttpHeadersMap();
    private boolean hasSize;
    private long size;
    private byte[] responseBody;
    private InputStream responseStream;

    public void setResponseBody(byte[] array)
    {
        this.responseBody = array;
        this.hasSize = true;
        this.size = array.length;
        this.responseStream = null;
    }

    public void setResponseStream(InputStream stream)
    {
        this.responseStream = stream;
        this.hasSize = false;
        this.size = 0;
        this.responseBody = null;
    }


    public boolean isHasSize()
    {
        return hasSize;
    }


    public long getSize()
    {
        return size;
    }

    public HttpHeadersMap getHeaders()
    {
        return headers;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }
}
