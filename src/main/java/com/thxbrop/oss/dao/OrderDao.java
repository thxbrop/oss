package com.thxbrop.oss.dao;

import com.thxbrop.oss.entity.Order;

import java.util.List;

public interface OrderDao {
    boolean commit(Order order);

    Order getById(int id);

    List<Order> findByUserId(int userId);
}
