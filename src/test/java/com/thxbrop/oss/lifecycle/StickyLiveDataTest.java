package com.thxbrop.oss.lifecycle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StickyLiveDataTest {
    @Test
    void testCommonLiveData() {
        MutableLiveData<String> _liveData = new MutableLiveData<>();
        LiveData<String> liveData = _liveData.asLiveData();
        liveData.observeForever(System.out::println);
        _liveData.setValue("1");
        liveData.observeForever(System.out::println);
    }

    @Test
    void testStickyLiveData() {
        StickyLiveData<String> _liveData = new StickyLiveData<>();
        LiveData<String> liveData = _liveData.asLiveData();
        liveData.observeForever(System.out::println);
        _liveData.setValue("1");
        liveData.observeForever(System.out::println);
    }

    @Test
    void testNonNullableLiveData() {
        StickyLiveData<String> _liveData = new StickyLiveData<>(false);
        LiveData<String> liveData = _liveData.asLiveData();
        liveData.observeForever(Assertions::assertNotNull);
        _liveData.setValue(null);
        _liveData.setValue("1");
    }
}