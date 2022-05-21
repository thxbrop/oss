package com.thxbrop.oss.repository;

import com.thxbrop.oss.Result;
import com.thxbrop.oss.entity.Order;

import java.io.Closeable;
import java.util.List;

public interface OrderRepository extends Closeable {
    Result<Order> commit(int userId, List<Integer> commodities);

    List<Order> findByUserId(int userId);
}
