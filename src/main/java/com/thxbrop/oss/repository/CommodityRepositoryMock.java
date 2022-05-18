package com.thxbrop.oss.repository;

import com.thxbrop.oss.entity.Commodity;
import com.thxbrop.oss.util.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.thxbrop.oss.util.thread.ThreadUtil.repeat;

public class CommodityRepositoryMock implements CommodityRepository {
    private static ArrayList<Commodity> list;

    public CommodityRepositoryMock() {
        list = new ArrayList<>();
        repeat(5, i -> list.add(new Commodity(i, String.valueOf(i), i * 100, "", Collections.emptyList())));
    }

    @Override
    public int addCommodity(Commodity... commodities) {
        int res = 0;
        for (Commodity commodity : commodities) {
            if (list.add(commodity)) {
                res++;
            }
        }
        return res;
    }

    @Override
    public boolean deleteCommodity(int commodityId) {
        return list.removeIf(commodity -> commodity.getId() == commodityId);
    }

    @Override
    public Commodity findById(int commodityId) {
        for (Commodity c : list) {
            if (c.getId() == commodityId) return c;
        }
        return null;
    }

    @Override
    public List<Commodity> findAll() {
        return list;
    }

    @Override
    public List<Commodity> findAll(int limit) {
        return list.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public void close() {
        Logger.t("CommodityControllerMock", "closed");
    }

    @Override
    public List<Commodity> search(String s) {
        ArrayList<Commodity> arrayList = new ArrayList<>();
        for (String tag : s.split(" ")) {
            for (Commodity commodity : list) {
                if (tag.startsWith("#")) {
                    if (commodity.getTags().contains(s) && !arrayList.contains(commodity)) {
                        arrayList.add(commodity);
                    }
                } else {
                    if (commodity.getName().contains(s) && !arrayList.contains(commodity)) {
                        arrayList.add(commodity);
                    }
                }
            }
        }
        return arrayList;
    }
}
