package org.kondle.httpserver.http.components.body;

import java.io.InputStream;

public class HttpBody
{



    private boolean hasSize;
    private boolean isStream()
    {
        return this.stream == null;
    }

    private long size;

    private InputStream stream;


}
