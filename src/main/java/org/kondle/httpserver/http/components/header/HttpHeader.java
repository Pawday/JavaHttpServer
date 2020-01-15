package org.kondle.httpserver.http.components.header;


import java.io.InputStream;

/**
 * The {@code HttpHeader} class is simple string format "key": "value"
 */

public class HttpHeader
{
    /**
     * @param key
     * @param value
     */
    public HttpHeader(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    /**
     * @return String of header key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @return String of header value 
     */
    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return this.key + ": " + value;
    }

    /**
     * @param value new value for this header
     */
    public void setValue(String value)
    {
        this.value = value;
    }
}