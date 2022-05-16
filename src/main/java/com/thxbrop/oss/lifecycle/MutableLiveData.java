package com.thxbrop.oss.lifecycle;

import java.util.Map;

public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData() {
    }

    public MutableLiveData(T defValue) {
        super.value = defValue;
    }

    public void setValue(T value) {
        super.value = value;
        for (Map.Entry<LifecycleOwner, Observer<T>> entry : observers.entrySet()) {
            if (entry.getKey().isActive()) {
                entry.getValue().onChanged(value);
            }
        }
        observersForever.forEach(observer -> observer.onChanged(value));
    }
}