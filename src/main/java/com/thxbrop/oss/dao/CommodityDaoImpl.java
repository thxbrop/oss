package com.thxbrop.oss.dao;

import com.thxbrop.oss.entity.Commodity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommodityDaoImpl implements CommodityDao {
    private final Connection connection;

    public CommodityDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean insert(Commodity commodity) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO commodity (name,price) VALUES (?,?) ")) {
            statement.setString(1, commodity.getName());
            statement.setFloat(2, commodity.getPrice());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int commodityId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM commodity WHERE id = ?")) {
            statement.setInt(1, commodityId);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Commodity find(int commodityId) {
        Commodity commodity = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM commodity WHERE id = ?")) {
            statement.setInt(1, commodityId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                commodity = new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price")
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodity;
    }

    @Override
    public List<Commodity> findAll() {
        ArrayList<Commodity> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM commodity")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Commodity commodity = new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price")
                );
                list.add(commodity);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
