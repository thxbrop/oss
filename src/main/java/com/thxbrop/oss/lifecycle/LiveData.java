package com.thxbrop.oss.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LiveData<T> {
    protected final HashMap<LifecycleOwner, Observer<T>> observers;
    protected final List<Observer<T>> observersForever;
    protected T value;

    public LiveData() {
        observers = new HashMap<>();
        observersForever = new ArrayList<>();
    }

    public Map<LifecycleOwner, Observer<T>> getObservers() {
        return observers;
    }

    public Observer<T> getObserver(LifecycleOwner owner) {
        return observers.get(owner);
    }

    public List<Observer<T>> getObserversForever() {
        return observersForever;
    }

    public void observe(LifecycleOwner owner, Observer<T> observer) {
        observers.put(owner, observer);
    }

    public void observeForever(Observer<T> observer) {
        observersForever.add(observer);
    }

    public boolean removeObserver(Observer<T> observer) {
        for (Map.Entry<LifecycleOwner, Observer<T>> entry : observers.entrySet()) {
            if (entry.getValue() == observer) {
                return removeObserver(entry.getKey());
            }
        }
        return observersForever.remove(observer);
    }

    public boolean removeObserver(LifecycleOwner owner) {
        return observers.remove(owner) != null;
    }

    public T getValue() {
        return value;
    }

    public LiveData<T> asLiveData() {
        return this;
    }
}
