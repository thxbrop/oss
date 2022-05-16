package com.thxbrop.oss.controller;

import com.thxbrop.oss.ConnectionManager;
import com.thxbrop.oss.dao.CommodityDao;
import com.thxbrop.oss.dao.CommodityDaoImpl;
import com.thxbrop.oss.entity.Commodity;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class CommodityControllerImpl implements CommodityController {
    private final CommodityDao dao;

    public CommodityControllerImpl() {
        Connection connection = ConnectionManager.getInstance().getConnection();
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
}
