package com.joonko.greenhouseinterview.web;

import com.joonko.greenhouseinterview.json.JsonHandler;
import java.util.Map;

/**
 * A basic query pojo that depicts a query sent over REST
 */
public class RestQuery {
    private String url;
    private Map<String, String> headers;
    private Map<String, String> queryStringParameters;
    private JsonHandler<?> jsonHandler;
    private String payload;

    public RestQuery(String url, Map<String, String> headers, Map<String, String> queryStringParameters,
                     JsonHandler<?> jsonHandler, String payload) {
        this.url = url;
        this.headers = headers;
        this.queryStringParameters = queryStringParameters;
        this.jsonHandler = jsonHandler;
        this.payload = payload;
    }

    public static RestQueryBuilder builder() {return new RestQueryBuilder();}

    public String getUrl() {return this.url;}

    public Map<String, String> getHeaders() {return this.headers;}

    public Map<String, String> getQueryStringParameters() {return this.queryStringParameters;}

    public JsonHandler<?> getJsonHandler() {return this.jsonHandler;}

    public String getPayload() {return this.payload;}

    public void setUrl(String url) {this.url = url; }

    public void setHeaders(Map<String, String> headers) {this.headers = headers; }

    public void setQueryStringParameters(
            Map<String, String> queryStringParameters) {this.queryStringParameters = queryStringParameters; }

    public void setJsonHandler(JsonHandler<?> jsonHandler) {this.jsonHandler = jsonHandler; }

    public void setPayload(String payload) {this.payload = payload; }

    public static class RestQueryBuilder {
        private String url;
        private Map<String, String> headers;
        private Map<String, String> queryStringParameters;
        private JsonHandler<?> jsonHandler;
        private String payload;

        RestQueryBuilder() {}

        public RestQueryBuilder url(String url) {
            this.url = url;
            return this;
        }

        public RestQueryBuilder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public RestQueryBuilder queryStringParameters(Map<String, String> queryStringParameters) {
            this.queryStringParameters = queryStringParameters;
            return this;
        }

        public RestQueryBuilder jsonHandler(JsonHandler<?> jsonHandler) {
            this.jsonHandler = jsonHandler;
            return this;
        }

        public RestQueryBuilder payload(String payload) {
            this.payload = payload;
            return this;
        }

        public RestQuery build() {
            return new RestQuery(url, headers, queryStringParameters, jsonHandler, payload);
        }

        public String toString() {return "RestQuery.RestQueryBuilder(url=" + this.url + ", headers=" + this.headers + ", queryStringParameters=" + this.queryStringParameters + ", jsonHandler=" + this.jsonHandler + ", payload=" + this.payload + ")";}
    }
}