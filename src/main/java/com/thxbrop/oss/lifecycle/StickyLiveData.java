package com.thxbrop.oss.lifecycle;

public class StickyLiveData<T> extends MutableLiveData<T> {
    private final boolean allowNull;

    public StickyLiveData(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public StickyLiveData(T defValue) {
        super(defValue);
        this.allowNull = false;
    }

    public StickyLiveData(T defValue, boolean allowNull) {
        super(defValue);
        this.allowNull = allowNull;
    }

    public StickyLiveData() {
        this.allowNull = false;
    }

    public StickyLiveData(MutableLiveData<T> liveData, boolean allowNull) {
        this.allowNull = allowNull;
        observers.putAll(liveData.observers);
        observersForever.addAll(liveData.observersForever);
    }

    @Override
    public void setValue(T value) {
        if (value != null || allowNull) super.setValue(value);
    }
}
