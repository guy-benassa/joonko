package com.joonko.greenhouseinterview.web;

/**
 * A REST client interface
 */
public interface RestClient {
    <T> T getQuery(RestQuery query) throws Exception;
}
