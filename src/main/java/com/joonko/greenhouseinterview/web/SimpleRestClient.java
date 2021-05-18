package com.joonko.greenhouseinterview.web;

import okhttp3.*;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * A basic JAX-RS client
 */
public class SimpleRestClient implements RestClient {
    private final int OK = 200;

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    @Override
    public Response getQuery(RestQuery query) throws Exception {
        String url = getUrlFromQuery(query.getUrl(), query.getQueryStringParameters());
        Request request = new Request.Builder()
                .url(url)
                .headers(Headers.of(query.getHeaders()))
                .get()
                .build();

        Response response = client.newCall(request).execute();

        switch (response.code()) {
            case OK:
                return response;
            default:
                response.close();
                String message = String.format("Message: %s; Code: %d", response.message(), response.code());
                throw new Exception(message);
        }
    }

    private String getUrlFromQuery(String url, Map<String, String> queryStringParameters) {

        String queryParams = "";

        if (MapUtils.isNotEmpty(queryStringParameters)) {
            queryParams = queryStringParameters.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining("&"));
            queryParams = "?" + queryParams;
        }

        return url + queryParams;
    }
}