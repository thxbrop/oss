package com.thxbrop.oss;

import com.thxbrop.oss.entity.Commodity;
import com.thxbrop.oss.lifecycle.LiveData;
import com.thxbrop.oss.lifecycle.MutableLiveData;

import java.util.Collections;
import java.util.List;

public class ViewModel {
    private static final MutableLiveData<List<Commodity>> _allCommoditiesLiveData = new MutableLiveData<>(Collections.emptyList());
    public static LiveData<List<Commodity>> allCommoditiesLiveData = _allCommoditiesLiveData.asLiveData();

    private static final MutableLiveData<Commodity> _commodityLiveData = new MutableLiveData<>();
    public static LiveData<Commodity> commodityLiveData = _commodityLiveData.asLiveData();

    public static void submitAllCommodities(List<Commodity> commodities) {
        _allCommoditiesLiveData.setValue(commodities);
    }

    public static void submitCommodity(Commodity commodity) {
        _commodityLiveData.setValue(commodity);
    }
}
