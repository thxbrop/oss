package com.thxbrop.oss.controller;

import com.thxbrop.oss.entity.Commodity;

import java.util.List;

public interface CommodityController {
    int addCommodity(Commodity... commodities);

    boolean deleteCommodity(int commodityId);

    List<Commodity> findAll();

    Commodity findById(int commodityId);

    List<Commodity> findAll(int limit);
}
