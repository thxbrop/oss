package com.thxbrop.oss.repository;

import com.thxbrop.oss.entity.Commodity;

import java.io.Closeable;
import java.util.List;

public interface CommodityRepository extends Closeable {
    int addCommodity(Commodity... commodities);

    boolean deleteCommodity(int commodityId);

    List<Commodity> findAll();

    Commodity findById(int commodityId);

    List<Commodity> findAll(int limit);

    List<Commodity> search(String s);
}
