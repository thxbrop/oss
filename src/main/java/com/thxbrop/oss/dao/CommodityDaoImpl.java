package com.thxbrop.oss.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thxbrop.oss.entity.Commodity;
import com.thxbrop.oss.util.GsonUtil;
import org.jetbrains.annotations.NotNull;

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
    public boolean insert(@NotNull Commodity commodity) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO commodity (name,price,img,tags) VALUES (?,?,?,?) ")) {
            statement.setString(1, commodity.getName());
            statement.setFloat(2, commodity.getPrice());
            statement.setString(3, commodity.getImg());
            statement.setString(4, new Gson().toJson(commodity.getTags()));
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
            TypeToken<List<String>> typeToken = GsonUtil.getTypeToken();
            if (resultSet.next()) {
                commodity = new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("img"),
                        new Gson().fromJson(resultSet.getString("tags"), typeToken.getType())
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
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM commodity ORDER BY RAND()")) {
            ResultSet resultSet = statement.executeQuery();
            TypeToken<List<String>> typeToken = GsonUtil.getTypeToken();
            while (resultSet.next()) {
                Commodity commodity = new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("img"),
                        new Gson().fromJson(resultSet.getString("tags"), typeToken.getType())
                );
                list.add(commodity);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Commodity> searchByName(String name) {
        ArrayList<Commodity> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM commodity WHERE name LIKE ?")) {
            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            TypeToken<List<String>> typeToken = GsonUtil.getTypeToken();
            while (resultSet.next()) {
                Commodity commodity = new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("img"),
                        new Gson().fromJson(resultSet.getString("tags"), typeToken.getType())
                );
                list.add(commodity);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Commodity> searchByTag(String tag) {
        ArrayList<Commodity> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM commodity WHERE tags LIKE ?")) {
            statement.setString(1, "%" + tag + "%");
            ResultSet resultSet = statement.executeQuery();
            TypeToken<List<String>> typeToken = GsonUtil.getTypeToken();
            while (resultSet.next()) {
                Commodity commodity = new Commodity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("price"),
                        resultSet.getString("img"),
                        new Gson().fromJson(resultSet.getString("tags"), typeToken.getType())
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
