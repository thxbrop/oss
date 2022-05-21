package com.thxbrop.oss.dao;

import com.google.gson.Gson;
import com.thxbrop.oss.entity.Order;
import com.thxbrop.oss.util.GsonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private final Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean commit(Order order) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `order` (userId,createdAt,commodities) VALUES (?,?,?)")) {
            statement.setInt(1, order.getUserId());
            statement.setLong(2, order.getCreatedAt());
            statement.setString(3, new Gson().toJson(order.getCommodities()));
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Order getById(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `order` WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getLong("createdAt"),
                        new Gson().fromJson(resultSet.getString("commodities"), GsonUtil.getTypeToken().getType())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findByUserId(int userId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `order` WHERE userId = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Order> list = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getLong("createdAt"),
                        new Gson().fromJson(resultSet.getString("commodities"), GsonUtil.getTypeToken().getType())
                );
                list.add(order);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
