package com.lonefit.market.core.domain;

public class ValueObject<T> {

    private T value;

    protected ValueObject(T value) {
        this.value = value;
    }
}
