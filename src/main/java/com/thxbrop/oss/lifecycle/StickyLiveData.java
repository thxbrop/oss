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
    public void observe(LifecycleOwner owner, Observer<T> observer) {
        super.observe(owner, observer);
        sticky(observer);
    }

    @Override
    public void observeForever(Observer<T> observer) {
        super.observeForever(observer);
        sticky(observer);
    }

    private void sticky(Observer<T> observer) {
        if (value != null || allowNull) observer.onChanged(value);
    }
}
