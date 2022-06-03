package com.scando.learning.common.service;

public interface WebFluxService<T> {

    void post(T payload, String uri);
}
