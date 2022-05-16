package com.thxbrop.oss.lifecycle;

public interface Observer<T> {
    void onChanged(T t);
}