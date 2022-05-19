package com.thxbrop.oss.repository;

import com.thxbrop.oss.ConnectionManager;
import com.thxbrop.oss.dao.CommodityDao;
import com.thxbrop.oss.dao.CommodityDaoImpl;
import com.thxbrop.oss.entity.Commodity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommodityRepositoryImpl implements CommodityRepository {
    private final CommodityDao dao;
    private final Connection connection;

    public CommodityRepositoryImpl() {
        connection = ConnectionManager.getInstance().getConnection();
        dao = new CommodityDaoImpl(connection);
    }

    @Override
    public int addCommodity(Commodity... commodities) {
        int res = 0;
        for (Commodity commodity : commodities) {
            boolean flag = dao.insert(commodity);
            if (flag) res++;
        }
        return res;
    }

    @Override
    public boolean deleteCommodity(int commodityId) {
        return dao.delete(commodityId);
    }

    @Override
    public Commodity findById(int commodityId) {
        return dao.find(commodityId);
    }

    @Override
    public List<Commodity> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Commodity> findAll(int limit) {
        return dao.findAll().stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Commodity> search(String s) {
        ArrayList<Commodity> arrayList = new ArrayList<>();
        for (String tag : s.split(" ")) {
            if (tag.startsWith("#")) {
                String substring = tag.substring(1);
                for (Commodity commodity : dao.searchByTag(substring)) {
                    if (!arrayList.contains(commodity)) {
                        arrayList.add(commodity);
                    }
                }

            } else {
                for (Commodity commodity : dao.searchByName(s)) {
                    if (!arrayList.contains(commodity)) {
                        arrayList.add(commodity);
                    }
                }
            }
        }
        return arrayList;
    }
}
