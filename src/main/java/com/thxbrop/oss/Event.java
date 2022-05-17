package com.thxbrop.oss;

public class Event<T> {
    public String status;
    public T value = null;

    public Event(String errorMsg) {
        status = errorMsg;
    }

    public Event(T value) {
        status = "success";
        this.value = value;
    }
}
