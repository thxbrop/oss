package com.thxbrop.oss.dao;

import com.thxbrop.oss.entity.Commodity;

import java.util.List;

public interface CommodityDao {
    boolean insert(Commodity commodity);

    boolean delete(int commodityId);

    Commodity find(int commodityId);

    List<Commodity> findAll();
}
