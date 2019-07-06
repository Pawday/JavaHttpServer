package org.kondle.httpserver.interfaces;

import org.kondle.httpserver.http.implementations.HttpRequest;
import org.kondle.httpserver.http.implementations.HttpResponse;

public interface HttpModule
{
	HttpResponse run(HttpRequest request, HttpResponse response);
}
