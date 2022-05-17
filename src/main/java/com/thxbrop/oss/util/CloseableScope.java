package com.thxbrop.oss.util;

import java.io.Closeable;
import java.io.IOException;

public interface CloseableScope<T extends Closeable> {
    void receive(T t) throws IOException;
}