package com.thxbrop.oss.repository;

import com.thxbrop.oss.ConnectionManager;
import com.thxbrop.oss.Result;
import com.thxbrop.oss.dao.OrderDao;
import com.thxbrop.oss.dao.OrderDaoImpl;
import com.thxbrop.oss.entity.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderDao dao;
    private final Connection connection;

    public OrderRepositoryImpl() {
        connection = ConnectionManager.getInstance().getConnection();
        dao = new OrderDaoImpl(connection);
    }

    @Override
    public Result<Order> commit(int userId, List<Integer> commodities) {
        Order order = new Order(userId, System.currentTimeMillis(), commodities);
        boolean commit = dao.commit(order);
        if (commit) {
            return new Result<>(order);
        } else {
            return new Result<>("Commit Failed.");
        }
    }

    @Override
    public List<Order> findByUserId(int userId) {
        return dao.findByUserId(userId);
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
