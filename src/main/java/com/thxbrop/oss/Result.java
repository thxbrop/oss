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

    public boolean isSuccess() {
        return message == null;
    }

    public Event<T> asEvent() {
        if (isSuccess()) return new Event<>(value);
        else return new Event<>(message);
    }
}