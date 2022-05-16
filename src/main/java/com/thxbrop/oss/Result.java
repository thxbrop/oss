package com.thxbrop.oss;

public class Result<T> {
    public String message = null;
    public T value = null;

    public Result(String message) {
        this.message = message;
    }

    public Result(T value) {
        this.value = value;
    }
}